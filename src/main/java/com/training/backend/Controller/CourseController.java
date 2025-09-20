package com.training.backend.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
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

  @PostMapping("/create")
  public ResponseEntity<ApiResponse> createCourse(@RequestBody Course entity) {
    courseService.createCourse(entity);
    return ResponseEntity.ok(new ApiResponse("Curso creado exitosamente"));
  }

  @GetMapping("")
  public ResponseEntity<List<Course>> getAllCourses() {
    return ResponseEntity.ok(courseService.getAllCourses());
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<ApiResponse> updateCourse(@PathVariable Long id, @RequestBody Course entity) {
    courseService.updateCourse(id, entity);
    return ResponseEntity.ok(new ApiResponse("Curso actualizado exitosamente"));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<ApiResponse> deleteCourse(@PathVariable Long id) {
    courseService.deleteCourse(id);
    return ResponseEntity.ok(new ApiResponse("Curso eliminado exitosamente"));
  }
}
