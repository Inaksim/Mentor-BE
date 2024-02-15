package com.mentor.mentor.dto.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SaveCourseForm {
    private String title;
    private String description;
    private String department;
    private int review;
    private Long teacher_id;
}
