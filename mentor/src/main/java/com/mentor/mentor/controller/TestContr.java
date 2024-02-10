package com.mentor.mentor.controller;

import com.mentor.mentor.model.User;
import com.mentor.mentor.service.TestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class TestContr {

    TestService service;

    @PostMapping("/test")
    public ResponseEntity<User> testController(@RequestBody User user) {
        User createdUser = service.createNewUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/test2")
    public ResponseEntity<String>  seyHello() {
        return ResponseEntity.ok("hhh");

    }


}
