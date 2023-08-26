package com.codelantic.configuration;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String header = httpServletRequest.getHeader("Authorization");

        String jwtToken = null;

        if(header != null && header.startsWith("Bearer ")){
            jwtToken = header.substring(7);
            try{

            }
            catch(IllegalArgumentException e){
                System.out.println("Unable to get JWT Token");
            }
            catch(ExpiredJwtException e){
                System.out.println("JWT Token is Expired");
            }

        }


    }
}
