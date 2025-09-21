package com.training.backend.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.training.backend.Dto.ApiResponse;
import com.training.backend.Entity.Insignia;
import com.training.backend.Services.InsigniaService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/insignias")
public class InsigniaController {
  private final InsigniaService insigniaService;

  // Endpoint to get all insignias or insignias assigned to a specific user
  @GetMapping
  public List<Insignia> getInsignias(@RequestParam(required = false) Long userId) {
    // If a userId is provided, return only insignias assigned to that user
    if (userId != null) {
      return insigniaService.getInsigniasByUser(userId);
    } else {
      return insigniaService.getAllInsignias();
    }
  }

  // Endpoint to create a new insignia
  @PostMapping("/create")
  public ResponseEntity<ApiResponse> createInsignia(@RequestBody Insignia insignia) {
    // Calls the service layer to save the insignia in the database
    insigniaService.createInsignia(insignia);
    // Returns a success message to the client
    return ResponseEntity.ok(new ApiResponse("Insignia creada exitosamente"));
  }

}
