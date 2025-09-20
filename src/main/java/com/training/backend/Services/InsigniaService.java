package com.training.backend.Services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.training.backend.Entity.Insignia;
import com.training.backend.Repository.CourseRepository;
import com.training.backend.Repository.InsigniaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InsigniaService {

  private final InsigniaRepository insigniaRepository;
  private final CourseRepository courseRepository;

  public Insignia createInsignia(Insignia insignia) {
    if (!courseRepository.existsById(insignia.getCourse().getId())) {
      throw new RuntimeException("Curso no existe con id " + insignia.getCourse().getId());
    }
    return insigniaRepository.save(insignia);
  }

  public List<Insignia> getAllInsignias() {
    return insigniaRepository.findAll();
  }

  public Insignia updateInsignia(Long id, Insignia insigniaDetails) {
    return insigniaRepository.findById(id).map(insignia -> {
      insignia.setName(insigniaDetails.getName());
      insignia.setImageUrl(insigniaDetails.getImageUrl());
      return insigniaRepository.save(insignia);
    }).orElseThrow(() -> new RuntimeException("Insignia no encontrada con id " + id));
  }

  public void deleteInsignia(Long id) {
    try {
      if (!insigniaRepository.existsById(id)) {
        throw new RuntimeException("Insignia no encontrada con id " + id);
      }
      insigniaRepository.deleteById(id);
    } catch (DataIntegrityViolationException ex) {
      throw new RuntimeException(
          "No se puede eliminar la insignia porque tiene dependencias asociadas.", ex);
    } catch (Exception e) {
      throw new RuntimeException("Error al eliminar la insignia con id " + id, e);
    }
  }

}
