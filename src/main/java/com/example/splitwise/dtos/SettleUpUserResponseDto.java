package com.example.splitwise.dtos;

import com.example.splitwise.service.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleUpUserResponseDto {
    private List<Transaction> transactions;
    private String response;
}
