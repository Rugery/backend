package com.training.backend.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.training.backend.Dto.ApiResponse;
import com.training.backend.Entity.Course;
import com.training.backend.Services.CourseService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

  private final CourseService courseService;

  // Endpoint to get all courses or courses assigned to a specific user
  @GetMapping
  public List<Course> getAllCourses(@RequestParam(required = false) Long userId) {
    // If a userId is provided, return only courses assigned to that user
    if (userId != null) {
      return courseService.getCoursesForUser(userId);
    }
    // If no userId is provided, return all courses
    return courseService.getAllCourses();
  }

  // Endpoint to create a new course
  @PostMapping("/create")
  public ResponseEntity<ApiResponse> createCourse(@RequestBody Course course) {
    // Calls the service layer to save the course in the database
    courseService.createCourse(course);
    // Returns a success message to the client
    return ResponseEntity.ok(new ApiResponse("Curso creado exitosamente"));
  }

  // Endpoint to update an existing course
  @PutMapping("/update/{id}")
  public ResponseEntity<ApiResponse> updateCourse(@PathVariable Long id, @RequestBody Course entity) {
    // Calls the service layer to update the course in the database
    courseService.updateCourse(id, entity);
    // Returns a success message to the client
    return ResponseEntity.ok(new ApiResponse("Curso actualizado exitosamente"));
  }

  // Endpoint to delete a course by its ID
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<ApiResponse> deleteCourse(@PathVariable Long id) {
    // Calls the service layer to delete the course from the database
    courseService.deleteCourse(id);
    // Returns a success message to the client
    return ResponseEntity.ok(new ApiResponse("Curso eliminado exitosamente"));
  }
}
