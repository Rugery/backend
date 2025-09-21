package com.training.backend.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.training.backend.Entity.Progress;
import com.training.backend.Repository.CourseRepository;
import com.training.backend.Repository.ProgressRepository;
import com.training.backend.Repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgressService {
  private final ProgressRepository progressRepository;
  private final UserRepository userRepository;
  private final CourseRepository courseRepository;

  // Method to get all progress records
  public List<Progress> getAllProgress() {
    return progressRepository.findAll();
  }

  // Method to get progress records by user ID
  public List<Progress> getProgressByUser(Long userId) {
    return progressRepository.findAllByUserId(userId);
  }

  // Method to create a new progress record
  public Progress createProgress(Progress progress) {
    if (!userRepository.existsById(progress.getUser().getId())) {
      throw new RuntimeException("Usuario no existe con id " + progress.getUser().getId());
    }
    if (!courseRepository.existsById(progress.getCourse().getId())) {
      throw new RuntimeException("Curso no existe con id " + progress.getCourse().getId());
    }
    return progressRepository.save(progress);
  }

  // Method to update an existing progress record
  @Transactional
  public Progress updateProgress(Long id, Progress updatedProgress) {
    Progress progress = progressRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Progress no encontrado"));

    progress.setStatus(updatedProgress.getStatus());
    progress.setDateUpdated(updatedProgress.getDateUpdated());

    return progressRepository.save(progress);
  }

  public Optional<Progress> getByUserAndCourse(Long userId, Long courseId) {
    return progressRepository.findByUserIdAndCourseId(userId, courseId);
  }

}
