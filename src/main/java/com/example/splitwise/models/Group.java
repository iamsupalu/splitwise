package com.example.splitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.example.splitwise.models.Expense;
import java.util.List;

@Entity(name="groupuser")
@Getter
@Setter
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> users;

    @ManyToOne
    private User admin;

    @OneToMany
    private List<Expense> expenses;
}