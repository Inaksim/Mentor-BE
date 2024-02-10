package com.mentor.mentor.repo;

import com.mentor.mentor.model.CourseEnrollments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<CourseEnrollments, Long> {

}
