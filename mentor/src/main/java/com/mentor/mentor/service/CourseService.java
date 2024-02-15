package com.mentor.mentor.service;

import com.mentor.mentor.dto.form.SaveCourseForm;
import com.mentor.mentor.dto.form.UpdateCourseForm;
import com.mentor.mentor.dto.view.CourseView;
import com.mentor.mentor.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    List<Course> getAllCourse();

/*    List<Course> getTopCourse();
    Course getCourseId();*/
    CourseView saveCourse(SaveCourseForm form);

    Course updateCourse(UpdateCourseForm form);

    void deleteCourse(Long id);



}
