package com.mentor.mentor.controller;

import com.mentor.mentor.dto.form.SaveCourseForm;
import com.mentor.mentor.dto.view.CourseView;
import com.mentor.mentor.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
@AllArgsConstructor
public class CourseController {

    CourseService courseService;
    @PostMapping("/save")
    public CourseView createCourse(@RequestBody SaveCourseForm form) {
        return courseService.saveCourse(form);
    }

}
