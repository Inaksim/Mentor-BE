package com.mentor.mentor.dto.form;

import lombok.Data;

@Data
public class UpdateCourseForm {
    private Long id;
    private String name;
    private String description;
    private String department;
    private int review;
    private Long teacher_id;
}
