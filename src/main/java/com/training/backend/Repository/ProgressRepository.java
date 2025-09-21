package com.training.backend.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.training.backend.Entity.Progress;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
  // Custom query method to find all progress records for a given user
  List<Progress> findAllByUserId(Long userId);

  // Custom query method to find a progress record by user ID and course ID
  Optional<Progress> findByUserIdAndCourseId(Long userId, Long courseId);

}
