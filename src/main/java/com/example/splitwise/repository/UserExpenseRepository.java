package com.example.splitwise.repository;

import com.example.splitwise.models.Expense;
import com.example.splitwise.models.User;
import com.example.splitwise.models.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserExpenseRepository extends JpaRepository<UserExpense,Long>{
    List<UserExpense> findAllByUser(User user);
    List<UserExpense> findAllByExpenseIn(List<Expense> expenses);
}
