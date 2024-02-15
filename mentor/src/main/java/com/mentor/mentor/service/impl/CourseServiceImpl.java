package com.mentor.mentor.service.impl;

import com.mentor.mentor.dto.form.SaveCourseForm;
import com.mentor.mentor.dto.form.UpdateCourseForm;
import com.mentor.mentor.dto.view.CourseView;
import com.mentor.mentor.entity.Course;
import com.mentor.mentor.entity.User;
import com.mentor.mentor.exception.ApplicationException;
import com.mentor.mentor.repo.CourseRepository;
import com.mentor.mentor.repo.UserRepository;
import com.mentor.mentor.service.CourseService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Collections;
import java.util.List;

import static com.mentor.mentor.exception.Errors.CATEGORY_NOT_FOUND;
import static com.mentor.mentor.utils.Constraints.ID;


@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;
    private UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<Course> getAllCourse() {
        return null;
    }

    @Override
    public CourseView saveCourse(SaveCourseForm form) {
        User user = userRepository
                .findById(form.getTeacher_id())
                .orElseThrow(() -> new ApplicationException(
                        CATEGORY_NOT_FOUND,
                        Collections.singletonMap(ID, form.getTeacher_id())
                ));


        Course course = Course.builder()
                .title(form.getTitle())
                .description(form.getDescription())
                .department(form.getDepartment())
                .review(form.getReview())
                .teacher_id(user)
                .build();

        Course res = courseRepository.saveAndFlush(course);
        return modelMapper.map(res, CourseView.class);

    }

    @Override
    public Course updateCourse(UpdateCourseForm form) {
        return null;
    }

    @Override
    public void deleteCourse(Long id) {

    }
}
