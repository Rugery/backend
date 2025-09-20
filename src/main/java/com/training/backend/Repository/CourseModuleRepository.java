package com.training.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.training.backend.Entity.CourseModule;

public interface CourseModuleRepository extends JpaRepository<CourseModule, Long> {

}
