package com.mentor.mentor.controller;


import com.mentor.mentor.dto.SignUpForm;
import com.mentor.mentor.dto.UserView;
import com.mentor.mentor.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class authController {


    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sing-up")
    public UserView saveUser(@RequestBody SignUpForm form) {
        return userService.saveUser(form);
    }
}
