package com.example.splitwise.repository;

import com.example.splitwise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Locale;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findUserByphone(String phone);

    public User saveUser(User user);
}
