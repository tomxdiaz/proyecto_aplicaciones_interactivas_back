package com.uade.grupo5.api_trabajo_practico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);
  Boolean existsByEmailAddress(String emailAddress);
}
