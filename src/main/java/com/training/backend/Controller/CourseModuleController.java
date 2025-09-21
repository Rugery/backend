package com.training.backend.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.training.backend.Dto.ApiResponse;
import com.training.backend.Dto.CourseModuleDto;
import com.training.backend.Entity.CourseModule;
import com.training.backend.Services.CourseModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course-modules")
public class CourseModuleController {

  private final CourseModuleService courseModuleService;

  // Endpoint to get all course modules
  @GetMapping("")
  public List<CourseModule> getAllCourseModules() {
    // Calls the service layer to retrieve all course modules
    return courseModuleService.getAllCourseModules();
  }

  // Endpoint to create a new course module
  @PostMapping("/create")
  public ResponseEntity<ApiResponse> createCourseModule(@RequestBody CourseModuleDto module) {
    // Calls the service layer to create a new course module
    courseModuleService.createCourseModule(module);
    // Returns a success message to the client
    return ResponseEntity.ok(new ApiResponse("Modulo creado correctamente"));
  }

  // Endpoint to update an existing course module
  @PutMapping("/update/{id}")
  public ResponseEntity<ApiResponse> updateCourseModule(@PathVariable Long id,
      @RequestBody CourseModule moduleDetails) {
    // Calls the service layer to update the course module
    courseModuleService.updateCourseModule(id, moduleDetails);
    // Returns a success message to the client
    return ResponseEntity.ok(new ApiResponse("Modulo actualizado correctamente"));
  }

  // Endpoint to delete a course module by its ID
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<ApiResponse> deleteCourseModule(@PathVariable Long id) {
    // Calls the service layer to delete the course module
    courseModuleService.deleteCourseModule(id);
    // Returns a success message to the client
    return ResponseEntity.ok(new ApiResponse("Modulo eliminado correctamente"));
  }

}
