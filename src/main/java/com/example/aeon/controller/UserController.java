package com.example.aeon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aeon.models.Users;
import com.example.aeon.service.UserService;

import java.security.Principal;

import javax.validation.Valid;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping("/")
  public String home() {
    return "Hello World";
  }

  @RequestMapping("/user")
  public Principal user(Principal user) {
    return user;
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@Valid @RequestBody Users user){
    userService.saveUser(user);
    return ResponseEntity.created(null).body("success");
  }

}
