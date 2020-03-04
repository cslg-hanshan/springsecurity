package com.h2sj.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizationController {


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "admin")
    public String admin(){
        return "admin";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "user")
    public String user(){
        return "user";
    }

    @PreAuthorize("hasAuthority('addmember')")
    @GetMapping(value = "addmember")
    public String addmember(){
        return "addmember";
    }

}


