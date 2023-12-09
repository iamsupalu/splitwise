package com.example.splitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserExpense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Expense expense;

    @ManyToOne
    private User user;

    private int amount;

    @Enumerated(EnumType.ORDINAL)
    private UserExpenseType userExpenseType;
}