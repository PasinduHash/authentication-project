package com.codelantic.service;

import com.codelantic.entity.JwtRequest;
import com.codelantic.entity.JwtResponse;
import com.codelantic.entity.User;
import com.codelantic.repository.UserDAO;
import com.codelantic.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        System.out.println("create token -- Jwt service");
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        System.out.println(userName);
        System.out.println(userPassword);
        System.out.println("create token - before authe -- Jwt service");
//        authenticate(userName, userPassword);
        System.out.println("create token - after authe -- Jwt service");
        UserDetails userDetails = loadUserByUsername(userName);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        User user = userDAO.findById(userName).get();
        return new JwtResponse(user, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findById(username).get();

        if (user != null) {
            System.out.println("inside the if loop in jWT service");
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            System.out.println(userName);
            System.out.println(userPassword);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
            System.out.println("INSIDE AUTHENTICATION - JWTSERVICE");
        } catch (DisabledException e) {
            System.out.println("error 1 - jwt service");
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            System.out.println("error 2 - jwt service");
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}