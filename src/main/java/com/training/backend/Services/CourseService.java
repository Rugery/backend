package com.training.backend.Services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.training.backend.Entity.Course;
import com.training.backend.Repository.CourseModuleRepository;
import com.training.backend.Repository.CourseRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {
  private final CourseRepository courseRepository;
  private final CourseModuleRepository courseModuleRepository;

  public Course createCourse(Course course) {
    if (!courseModuleRepository.existsById(course.getModule().getId())) {
      throw new RuntimeException(
          "No se puede crear el curso porque el módulo con id " + course.getModule().getId() + " no existe.");
    }
    return courseRepository.save(course);
  }

  public List<Course> getAllCourses() {
    return courseRepository.findAll();
  }

  public Course updateCourse(Long id, Course courseDetails) {
    return courseRepository.findById(id).map(course -> {
      course.setTitle(courseDetails.getTitle());
      course.setDescription(courseDetails.getDescription());
      if (courseDetails.getModule() != null &&
          !courseModuleRepository.existsById(courseDetails.getModule().getId())) {
        throw new RuntimeException(
            "No se puede actualizar el curso porque el módulo con id " + courseDetails.getModule().getId()
                + " no existe.");
      }
      return courseRepository.save(course);
    }).orElseThrow(() -> new RuntimeException("Course not found with id " + id));
  }

  public void deleteCourse(Long id) {
    try {
      courseRepository.deleteById(id);
    } catch (DataIntegrityViolationException ex) {
      throw new RuntimeException(
          "No se puede eliminar el curso porque tiene progresos o insignias asociadas.", ex);
    } catch (Exception e) {
      throw new RuntimeException("Error al eliminar el curso con id " + id, e);
    }
  }

}
