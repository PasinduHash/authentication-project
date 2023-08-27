package com.codelantic.api;

import com.codelantic.entity.JwtRequest;
import com.codelantic.entity.JwtResponse;
import com.codelantic.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;
    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println("JWTController = Post mapping");
        System.out.println(jwtRequest);
        System.out.println(jwtRequest.getUserName());
        System.out.println(jwtRequest.getUserPassword());
        return jwtService.createJwtToken(jwtRequest);
    }

}
