package com.az.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.az.model.User;
import com.az.service.ServerDetailService;
import com.az.service.UserService;

import lombok.extern.java.Log;

@Log
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ServerDetailService serverDetailService;    
    
    @GetMapping("/")
    public String root() {
    	return "{healthy : true}";
    }
    
    @GetMapping("/hello")
    public String hello() {
    	return "Hello World! V-1 "+serverDetailService.getServerDetail();
    }
    
    @GetMapping("/getUsers")
    public List<User> getUserList(){
        log.info("UserController getUserList start...");
        List<User> userList = userService.getAllUsers();
        if(CollectionUtils.isEmpty(userList))
            log.info("Size of user List :::: "+userList.size());
        log.info("UserController getUserList end...");

        return userList;
    }
    
    


}
