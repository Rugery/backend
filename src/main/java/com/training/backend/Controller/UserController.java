package com.training.backend.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.training.backend.Dto.ApiResponse;
import com.training.backend.Dto.UserRegisterDto;
import com.training.backend.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @PostMapping("/create")
  public ResponseEntity<ApiResponse> createUser(@RequestBody UserRegisterDto userRegisterDto) {
    userService.createUser(userRegisterDto);
    return ResponseEntity.ok(new ApiResponse("User created successfully"));
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<ApiResponse> updateUser(@PathVariable Long id, @RequestBody UserRegisterDto userDetails) {
    userService.updateUser(id, userDetails);
    return ResponseEntity.ok(new ApiResponse("Usuario actualizado correctamente"));
  }

}
