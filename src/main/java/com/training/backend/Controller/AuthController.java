package com.training.backend.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.backend.Dto.UserLoginResponse;
import com.training.backend.Services.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<UserLoginResponse> login(@RequestParam String email,
      @RequestParam String password) {
    UserLoginResponse response = authService.login(email, password);
    return ResponseEntity.ok(response);
  }

}
