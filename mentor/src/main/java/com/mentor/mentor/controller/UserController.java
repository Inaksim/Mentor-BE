package com.mentor.mentor.controller;

import com.mentor.mentor.dto.form.UpdateUserForm;
import com.mentor.mentor.dto.view.UserView;
import com.mentor.mentor.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/users")
    public List<UserView> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public UserView getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/delete_user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("");
    }

    @PutMapping("/user")
    public UserView updateUser(@RequestBody UpdateUserForm form) {
        return userService.updateUser(form);
    }

}
