package com.example.splitwise.repository;

import com.example.splitwise.models.Group;
import com.example.splitwise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long>{
    Optional<Group> findGroupById(int id);
    List<User> findAllById(int id);
}
