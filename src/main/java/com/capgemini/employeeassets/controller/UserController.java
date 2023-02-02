package com.capgemini.employeeassets.controller;

import com.capgemini.employeeassets.entity.User;
import com.capgemini.employeeassets.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/user-login")
    @CrossOrigin
    public User login(@RequestBody @Valid User user)
    {
        //System.out.println(user.getUserId());
        System.out.println(user.getUsername());
        //System.out.println(user.getUserRole());
        System.out.println(user.getPassword());
        return userService.userLogin(user);
    }
}
