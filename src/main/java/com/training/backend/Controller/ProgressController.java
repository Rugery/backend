package com.training.backend.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.training.backend.Dto.ApiResponse;
import com.training.backend.Entity.Progress;
import com.training.backend.Services.ProgressService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/progress")
public class ProgressController {

  private final ProgressService progressService;

  // Endpoint to get all progress records or progress records for a specific user
  @GetMapping
  public List<Progress> getProgress(@RequestParam(required = false) Long userId) {
    // If a userId is provided, return only progress records for that user
    if (userId != null) {
      return progressService.getProgressByUser(userId);
    } else {
      return progressService.getAllProgress();
    }
  }

  // Endpoint to get progress for a specific user in a specific course
  @GetMapping("/user/{userId}/course/{courseId}")
  public ResponseEntity<Progress> getProgressByUserAndCourse(
      @PathVariable Long userId,
      @PathVariable Long courseId) {

    // Calls the service layer to retrieve the progress record
    Progress progress = progressService.getByUserAndCourse(userId, courseId)
        .orElse(null);
    // Returns the progress record to the client
    return ResponseEntity.ok(progress);
  }

  // Endpoint to create a new progress record
  @PostMapping("/create")
  public ResponseEntity<ApiResponse> createProgress(@RequestBody Progress progress) {
    // Calls the service layer to create a new progress record
    progressService.createProgress(progress);
    // Returns a success message to the client
    return ResponseEntity.ok(new ApiResponse("Progreso creado con exito"));
  }

  // Endpoint to update an existing progress record
  @PutMapping("/update/{id}")
  public ResponseEntity<ApiResponse> updateProgress(@PathVariable Long id, @RequestBody Progress progress) {
    // Calls the service layer to update the progress record
    progressService.updateProgress(id, progress);
    // Returns a success message to the client
    return ResponseEntity.ok(new ApiResponse("Progreso actualizado con exito"));
  }

}
