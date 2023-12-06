package com.example.splitwise.Commands;

public interface Command {
    boolean matches(String inp);
    void execute(String inp);
}
