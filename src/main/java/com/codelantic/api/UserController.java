package com.codelantic.api;

import com.codelantic.entity.User;
import com.codelantic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
//@RequestMapping("/api/v1/users")
public class UserController {
    @GetMapping
    public String getAllCustomers(){
     return "<h1>Get All Users</h1>";
    }

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
//        userService.initUser();
    }
    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

//    @GetMapping({"/forAdmin"})
//    @PreAuthorize("hasRole('Admin')")
//    public String forAdmin(){
//        return "This URL is only accessible to the admin";
//    }

    @GetMapping({"/forUser"})
    public String forUser(){
        return "This URL is only accessible to the user";
    }
}
