package com.mentor.mentor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserView {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;


}
