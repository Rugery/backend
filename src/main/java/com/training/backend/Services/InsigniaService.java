package com.training.backend.Services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.training.backend.Entity.Insignia;
import com.training.backend.Entity.Progress;
import com.training.backend.Entity.ProgressStatus;
import com.training.backend.Repository.InsigniaRepository;
import com.training.backend.Repository.ProgressRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InsigniaService {

  private final InsigniaRepository insigniaRepository;
  private final ProgressRepository progressRepository;

  // Method to get all insignias
  public List<Insignia> getAllInsignias() {
    return insigniaRepository.findAll();
  }

  // Method to get insignias by user ID
  public List<Insignia> getInsigniasByUser(Long userId) {
    return insigniaRepository.findAllByUserId(userId);
  }

  // Method to create a new insignia
  public Insignia createInsignia(Insignia insignia) {
    Long courseId = insignia.getCourse().getId();
    Long userId = insignia.getUser().getId();

    boolean exists = insigniaRepository.existsByUserIdAndCourseId(userId, courseId);
    if (exists) {
      throw new RuntimeException("Insignia ya creada para este curso y usuario");
    }

    Progress progress = progressRepository
        .findByUserIdAndCourseId(userId, courseId)
        .orElseThrow(() -> new RuntimeException("No hay progreso registrado para este usuario y curso"));

    if (progress.getStatus() != ProgressStatus.COMPLETED) {
      throw new RuntimeException("No se puede crear la insignia porque el curso no está completado.");
    }

    return insigniaRepository.save(insignia);
  }

}
