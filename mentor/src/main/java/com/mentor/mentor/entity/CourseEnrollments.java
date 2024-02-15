package com.mentor.mentor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Table(name = "CourseEnrollments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseEnrollments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollment_id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course_id;
    @Column(nullable = false)
    private Date enrollment_date;
}
