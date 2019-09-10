package com.my.dubbo.controller;

import com.my.dubbo.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Reference(loadbalance = "roundrobin")
    private UserService userService;

    @GetMapping("/sayHello")
    public String sayHello( String name)
    {
      return   userService.sayHello(name);
    }


}
