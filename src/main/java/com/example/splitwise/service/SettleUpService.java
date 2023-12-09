package com.example.splitwise.service;

import com.example.splitwise.controller.SettleUpController;
import com.example.splitwise.exceptions.UserAlreadyExistsException;
import com.example.splitwise.exceptions.UserNotFoundException;
import com.example.splitwise.models.Expense;
import com.example.splitwise.models.User;
import com.example.splitwise.models.UserExpense;
import com.example.splitwise.repository.UserExpenseRepository;
import com.example.splitwise.repository.UserRepository;
import com.example.splitwise.srategies.SettleUpStrategy;
import com.example.splitwise.srategies.TwoHeapStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SettleUpService {

    UserRepository userRepository;
    UserExpenseRepository userExpenseRepository;
    SettleUpStrategy settleUpStrategy;

    public SettleUpService(UserRepository userRepository, UserExpenseRepository userExpenseRepository, SettleUpStrategy settleUpStrategy) {
        this.userRepository = userRepository;
        this.userExpenseRepository = userExpenseRepository;
        this.settleUpStrategy = settleUpStrategy;
    }


    public List<Transaction> settleUpUser(int userId) throws UserNotFoundException {
        Optional<User> userOptional=userRepository.findUserById(userId);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException();
        }

            User user=userOptional.get();
            List<UserExpense> userExpenses=userExpenseRepository.findAllByUser(user);
            List<Expense> expenses=new ArrayList<>();
            for(UserExpense userExpense:userExpenses){
                expenses.add(userExpense.getExpense());
            }
        return settleUpStrategy.settleUp(expenses);
    }
    public List<Transaction> settleUpGroup(int groupId){
        return null;
    }
}
