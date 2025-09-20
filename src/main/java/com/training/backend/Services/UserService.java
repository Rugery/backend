package com.training.backend.Services;

import org.springframework.stereotype.Service;
import com.training.backend.Dto.UserRegisterDto;
import com.training.backend.Entity.Role;
import com.training.backend.Entity.User;
import com.training.backend.Mapper.UserMapper;
import com.training.backend.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public void createUser(UserRegisterDto userRegisterDto) {
    User user = userMapper.toEntity(userRegisterDto);
    user.setRole(Role.USER);
    userRepository.save(user);
  }

  public void updateUser(Long id, UserRegisterDto userDetails) {
    User existingUser = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + id));
    existingUser.setUsername(userDetails.getUsername());
    existingUser.setEmail(userDetails.getEmail());
    existingUser.setPassword(userDetails.getPassword());
    userRepository.save(existingUser);
  }

}
