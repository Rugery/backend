package com.training.backend.Services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.training.backend.Entity.Progress;
import com.training.backend.Repository.CourseRepository;
import com.training.backend.Repository.ProgressRepository;
import com.training.backend.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgressService {
  private final ProgressRepository progressRepository;
  private final UserRepository userRepository;
  private final CourseRepository courseRepository;

  public Progress createProgress(Progress progress) {
    if (!userRepository.existsById(progress.getUser().getId())) {
      throw new RuntimeException("Usuario no existe con id " + progress.getUser().getId());
    }
    if (!courseRepository.existsById(progress.getCourse().getId())) {
      throw new RuntimeException("Curso no existe con id " + progress.getCourse().getId());
    }
    return progressRepository.save(progress);
  }

  public List<Progress> getAllProgress() {
    return progressRepository.findAll();
  }

  public Progress updateProgress(Long id, Progress progressDetails) {
    return progressRepository.findById(id).map(progress -> {
      progress.setUser(progressDetails.getUser());
      progress.setCourse(progressDetails.getCourse());
      progress.setStatus(progressDetails.getStatus());
      progress.setDateUpdated(progressDetails.getDateUpdated());
      return progressRepository.save(progress);
    }).orElseThrow(() -> new RuntimeException("Progreso  no encontrado con id " + id));
  }

  public void deleteProgress(Long id) {
    try {
      if (!progressRepository.existsById(id)) {
        throw new RuntimeException("Progreso no encontrado con id " + id);
      }
      progressRepository.deleteById(id);
    } catch (DataIntegrityViolationException ex) {
      throw new RuntimeException(
          "No se puede eliminar el progreso porque tiene dependencias asociadas.", ex);
    } catch (Exception e) {
      throw new RuntimeException("Error al eliminar el progreso con id " + id, e);
    }
  }
}
