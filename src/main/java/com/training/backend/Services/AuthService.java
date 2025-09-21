package com.training.backend.Services;

import org.springframework.stereotype.Service;
import com.training.backend.Dto.UserLoginResponse;
import com.training.backend.Entity.User;
import com.training.backend.Repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;

  // Method to authenticate a user and return their ID and role
  public UserLoginResponse login(String email, String password) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("Email o contraseña incorrectos"));
    if (!user.getPassword().equals(password)) {
      throw new RuntimeException("Email o contraseña incorrectos");
    }
    return new UserLoginResponse(user.getId(), user.getRole());
  }
}
