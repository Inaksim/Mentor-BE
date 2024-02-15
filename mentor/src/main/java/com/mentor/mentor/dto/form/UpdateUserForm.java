package com.mentor.mentor.dto.form;

import lombok.Data;

@Data
public class UpdateUserForm {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;


}
