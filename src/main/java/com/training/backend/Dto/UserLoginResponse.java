package com.training.backend.Dto;

import com.training.backend.Entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponse {
  private Long userId;
  private Role role;
}
