package com.example.splitwise.srategies;

import com.example.splitwise.models.Expense;
import com.example.splitwise.models.User;
import com.example.splitwise.models.UserExpense;
import com.example.splitwise.models.UserExpenseType;
import com.example.splitwise.repository.UserExpenseRepository;
import com.example.splitwise.service.Transaction;
import org.antlr.v4.runtime.misc.Pair;
import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TwoHeapStrategy implements SettleUpStrategy {
    UserExpenseRepository userExpenseRepository;

    public TwoHeapStrategy(UserExpenseRepository userExpenseRepository) {
        this.userExpenseRepository = userExpenseRepository;
    }

    @Override
    public List<Transaction> settleUp(List<Expense> expenses) {
        List<UserExpense> userExpenses=userExpenseRepository.findAllByExpenseIn(expenses);
        Map<User,Integer> amountPaid=new HashMap<>();
        for(UserExpense userExpense:userExpenses){
            int currAmount=0;
            User user = userExpense.getUser();
            if(amountPaid.containsKey(user)){
                currAmount=amountPaid.get(user);
            }
            if(userExpense.getUserExpenseType().equals(UserExpenseType.WHO_PAID)){
                amountPaid.put(user,currAmount+userExpense.getAmount());
            }
            else{
                amountPaid.put(user,currAmount-userExpense.getAmount());
            }
        }

        PriorityQueue<Pair<User, Integer>> paidLess=new PriorityQueue<>();
        PriorityQueue<Pair<User,Integer>> paidMore=new PriorityQueue<>();

        for(Map.Entry<User,Integer> userAmount:amountPaid.entrySet()){
            if(userAmount.getValue()<0){
                paidLess.add(new Pair<>(userAmount.getKey(), userAmount.getValue()));
            }
            else{
                paidMore.add(new Pair<>(userAmount.getKey(), userAmount.getValue()));
            }
        }

        List<Transaction> transactions=new ArrayList<>();
        while(!paidLess.isEmpty()){
            Pair<User,Integer> paidLessPair=paidLess.poll();
            Pair<User,Integer> paidMorePair=paidMore.poll();
            Transaction t=new Transaction();
            t.setTo(paidMorePair.a);
            t.setFrom(paidLessPair.a);
            //A=-100 B=+200
            //A=-200 B=+100
            if(Math.abs(paidLessPair.b)<paidMorePair.b){
                t.setAmount(Math.abs(paidLessPair.b));
                int temp = paidMorePair.b - Math.abs(paidLessPair.b);
                if(!(temp ==0)){
                    paidMore.add(new Pair<>(paidMorePair.a, temp));
                }
            }
            else{
                t.setAmount(Math.abs(paidMorePair.b));
                int temp = Math.abs(paidLessPair.b) - paidMorePair.b;
                if(!(temp ==0)){
                    paidMore.add(new Pair<>(paidLessPair.a, temp));
                }
            }
            transactions.add(t);
        }
        return transactions;
    }
}
