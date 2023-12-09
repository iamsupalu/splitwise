package com.example.splitwise.repository;

import com.example.splitwise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Locale;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findUserByPhone(String phone);
    public Optional<User> findUserById(int id);
}
