package com.training.backend.Services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.training.backend.Entity.Insignia;
import com.training.backend.Repository.InsigniaRepository;
import com.training.backend.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InsigniaService {

  private final InsigniaRepository insigniaRepository;
  private final UserRepository userRepository;
  private final EmailService emailService;

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

    var user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    insignia.setUser(user);

    Insignia saved = insigniaRepository.save(insignia);

    // Send email notification
    String to = user.getEmail();
    String insigniaName = saved.getName() != null ? saved.getName() : "insignia desconocida";

    if (to != null && !to.isEmpty()) {
      emailService.sendInsigniaNotification(to, insigniaName);
    } else {
      System.err.println("insigniaName: " + insigniaName);
    }

    return saved;
  }

}
