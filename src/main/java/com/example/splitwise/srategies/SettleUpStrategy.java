package com.example.splitwise.srategies;

import com.example.splitwise.models.Expense;
import com.example.splitwise.service.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SettleUpStrategy {
    public List<Transaction> settleUp(List<Expense> expenses);
}
