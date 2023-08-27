package com.codelantic.service;

import com.codelantic.entity.User;
import com.codelantic.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initUser() {
        User user = new User();
        user.setUsername("root");
        user.setPassword(getEncodedPassword("password"));
        userDAO.save(user);

    }
    public User registerNewUser(User user) {
        user.setPassword(getEncodedPassword(user.getPassword()));
        return userDAO.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
