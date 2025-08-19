package com.example.telusko.JobAppNew.repo;

import com.example.telusko.JobAppNew.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
