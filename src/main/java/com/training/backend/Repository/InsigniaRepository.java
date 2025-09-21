package com.training.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.training.backend.Entity.Insignia;

public interface InsigniaRepository extends JpaRepository<Insignia, Long> {
  // Custom query method to check if an insignia exists for a given user and
  // course
  boolean existsByUserIdAndCourseId(Long userId, Long courseId);

  // Custom query method to find all insignias for a given user
  List<Insignia> findAllByUserId(Long userId);
}
