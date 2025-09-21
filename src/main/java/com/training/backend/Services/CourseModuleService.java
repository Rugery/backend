package com.training.backend.Services;

import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.training.backend.Dto.CourseModuleDto;
import com.training.backend.Entity.CourseModule;
import com.training.backend.Mapper.CourseModuleMapper;
import com.training.backend.Repository.CourseModuleRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseModuleService {

  private final CourseModuleRepository courseModuleRepository;
  private final CourseModuleMapper courseModuleMapper;

  // Method to get all course modules
  public List<CourseModule> getAllCourseModules() {
    return courseModuleRepository.findAll();
  }

  // Method to get a course module by ID
  public CourseModule createCourseModule(CourseModuleDto module) {
    CourseModule courseModule = courseModuleMapper.toEntity(module);
    if (courseModule.getCourses() != null) {
      courseModule.getCourses().forEach(course -> course.setModule(courseModule));
    }
    return courseModuleRepository.save(courseModule);
  }

  // Method to update a course module
  public CourseModule updateCourseModule(Long id, CourseModule moduleDetails) {
    return courseModuleRepository.findById(id).map(module -> {
      module.setTitle(moduleDetails.getTitle());
      module.setDescription(moduleDetails.getDescription());
      return courseModuleRepository.save(module);
    }).orElseThrow(() -> new RuntimeException("Modulo no encontrado con id " + id));
  }

  // Method to delete a course module
  public void deleteCourseModule(Long id) {
    try {
      courseModuleRepository.deleteById(id);
    } catch (DataIntegrityViolationException ex) {
      throw new RuntimeException("No se puede eliminar el modulo porque tiene cursos asociados.", ex);
    } catch (Exception e) {
      throw new RuntimeException("Error al eliminar el modulo con id " + id, e);
    }
  }

}
