package com.training.backend.Dto;

import lombok.Data;

@Data
public class UserRegisterDto {
  private String username;
  private String email;
  private String password;
}
