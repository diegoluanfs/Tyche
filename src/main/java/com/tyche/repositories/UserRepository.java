package com.tyche.repositories;

import com.tyche.domain.user.User;
import com.tyche.dtos.UserUpdateDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByDocument(String document);
    Optional<User> findUserById(Long id);
}
