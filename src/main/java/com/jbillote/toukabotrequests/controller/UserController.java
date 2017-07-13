package com.jbillote.toukabotrequests.controller;

import com.jbillote.toukabotrequests.model.User;
import com.jbillote.toukabotrequests.model.SuccessResponse;
import com.jbillote.toukabotrequests.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/api/user/register", method = RequestMethod.POST)
    @ResponseBody
    public SuccessResponse register(@RequestBody User user) {
        return new SuccessResponse(userService.register(user));
    }
}
