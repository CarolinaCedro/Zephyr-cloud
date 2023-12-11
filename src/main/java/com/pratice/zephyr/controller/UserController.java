package com.pratice.zephyr.controller;

import com.pratice.zephyr.model.User;
import com.pratice.zephyr.service.Rest;
import com.pratice.zephyr.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/zephyr/user")
public class UserController extends AbstractRestController<User> {

    private final UserServiceImpl service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @Override
    protected Rest<User> getService() {
        return this.service;
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }



}
