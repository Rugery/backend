package com.training.backend.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.backend.Dto.ApiResponse;
import com.training.backend.Entity.Insignia;
import com.training.backend.Services.InsigniaService;
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
@RequestMapping("/insignias")
public class InsigniaController {
  private final InsigniaService insigniaService;

  @PostMapping("/create")
  public ResponseEntity<ApiResponse> createInsignia(@RequestBody Insignia insignia) {
    insigniaService.createInsignia(insignia);
    return ResponseEntity.ok(new ApiResponse("Insignia creada exitosamente"));
  }

  @GetMapping("/")
  public ResponseEntity<List<Insignia>> getAllInsignias() {
    return ResponseEntity.ok(insigniaService.getAllInsignias());
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<ApiResponse> updateInsignia(@PathVariable Long id, @RequestBody Insignia insignia) {
    insigniaService.updateInsignia(id, insignia);
    return ResponseEntity.ok(new ApiResponse("Insignia actualizada exitosamente"));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<ApiResponse> deleteInsignia(@PathVariable Long id) {
    insigniaService.deleteInsignia(id);
    return ResponseEntity.ok(new ApiResponse("Insignia eliminada exitosamente"));
  }

}
