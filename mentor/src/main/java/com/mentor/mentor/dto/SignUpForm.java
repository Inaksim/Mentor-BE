package com.mentor.mentor.dto;

import com.mentor.mentor.security.Role;
import lombok.Data;

@Data
public class SignUpForm {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
