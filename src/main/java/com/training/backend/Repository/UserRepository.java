package com.training.backend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.training.backend.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  // Custom query method to find a user by email
  Optional<User> findByEmail(String email);
}
