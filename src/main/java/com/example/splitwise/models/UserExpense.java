package com.example.splitwise.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserExpense {
    private Expense expense;
    private User user;
    private int amount;
    private UserExpenseType userExpenseType;

}
