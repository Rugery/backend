package com.training.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.training.backend.Entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
