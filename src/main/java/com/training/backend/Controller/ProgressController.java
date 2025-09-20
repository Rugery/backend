package com.training.backend.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.backend.Dto.ApiResponse;
import com.training.backend.Entity.Progress;
import com.training.backend.Services.ProgressService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

  @GetMapping("")
  public ResponseEntity<List<Progress>> getAllProgress() {
    return ResponseEntity.ok(progressService.getAllProgress());
  }

  @PostMapping("/create")
  public ResponseEntity<ApiResponse> createProgress(@RequestBody Progress progress) {
    progressService.createProgress(progress);
    return ResponseEntity.ok(new ApiResponse("Progreso creado con exito"));
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<ApiResponse> updateProgress(@PathVariable Long id, @RequestBody Progress progress) {
    progressService.updateProgress(id, progress);
    return ResponseEntity.ok(new ApiResponse("Progreso actualizado con exito"));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<ApiResponse> deleteProgress(@PathVariable Long id) {
    progressService.deleteProgress(id);
    return ResponseEntity.ok(new ApiResponse("Progreso eliminado con exito"));
  }

}
