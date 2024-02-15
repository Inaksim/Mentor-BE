package com.mentor.mentor.controller;


import com.mentor.mentor.dto.form.ResetPasswordForm;
import com.mentor.mentor.dto.form.SignInForm;
import com.mentor.mentor.dto.form.SignUpForm;
import com.mentor.mentor.dto.view.UserView;
import com.mentor.mentor.repo.UserRepository;
import com.mentor.mentor.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {


    private final UserServiceImpl userService;
    private final UserRepository userRepository;

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserView saveUser(@RequestBody SignUpForm form) {
        return userService.saveUser(form);
    }


    @PostMapping("/sign-in")
    public ResponseEntity<UserView> authenticateUser(@RequestBody SignInForm form) {
        return userService.authenticateUser(form);
    }

    @PostMapping("/sign-out")
    public ResponseEntity<Void> logoutUser() {
        return userService.logoutUser();
    }

    @PutMapping("/reset_password")
    public void resetPassword(@RequestBody ResetPasswordForm form) {
        userService.resetPassword(form);
    }


}
