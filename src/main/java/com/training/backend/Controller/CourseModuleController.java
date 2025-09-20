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

  @PostMapping("/create")
  public ResponseEntity<ApiResponse> createCourseModule(@RequestBody CourseModuleDto module) {
    courseModuleService.createCourseModule(module);
    return ResponseEntity.ok(new ApiResponse("Modulo creado correctamente"));
  }

  @GetMapping("")
  public List<CourseModule> getAllCourseModules() {
    return courseModuleService.getAllCourseModules();
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<ApiResponse> updateCourseModule(@PathVariable Long id,
      @RequestBody CourseModule moduleDetails) {
    courseModuleService.updateCourseModule(id, moduleDetails);
    return ResponseEntity.ok(new ApiResponse("Modulo actualizado correctamente"));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<ApiResponse> deleteCourseModule(@PathVariable Long id) {
    courseModuleService.deleteCourseModule(id);
    return ResponseEntity.ok(new ApiResponse("Modulo eliminado correctamente"));
  }

}
