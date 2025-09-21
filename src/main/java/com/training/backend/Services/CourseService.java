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

  // Method to get all courses
  public List<Course> getAllCourses() {
    return courseRepository.findAll();
  }

  // Method to get all courses with progress and badges for a specific user
  public List<Course> getCoursesForUser(Long userId) {
    List<Course> courses = courseRepository.findAll();
    courses.forEach(course -> {
      course.setProgress(
          course.getProgress().stream()
              .filter(p -> p.getUser().getId().equals(userId))
              .toList());
      course.setInsignias(
          course.getInsignias().stream()
              .filter(i -> i.getUser().getId().equals(userId))
              .toList());
    });
    return courses;
  }

  // Method to create a new course
  public Course createCourse(Course course) {
    if (!courseModuleRepository.existsById(course.getModule().getId())) {
      throw new RuntimeException(
          "No se puede crear el curso porque el módulo con id " + course.getModule().getId() + " no existe.");
    }
    return courseRepository.save(course);
  }

  // Method to update an existing course
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

  // Method to delete a course
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
