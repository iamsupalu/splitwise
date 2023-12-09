package com.example.splitwise.controller;

import com.example.splitwise.dtos.SettleUpUserRequestDto;
import com.example.splitwise.dtos.SettleUpUserResponseDto;
import com.example.splitwise.exceptions.UserNotFoundException;
import com.example.splitwise.service.SettleUpService;
import com.example.splitwise.service.Transaction;
import org.springframework.stereotype.Controller;

@Controller
public class SettleUpController {
    SettleUpService settleUpService;

    public SettleUpController(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
    }

    public SettleUpUserResponseDto settleUpUser(SettleUpUserRequestDto settleUpUserRequestDto){
        SettleUpUserResponseDto settleUpResponseDto =new SettleUpUserResponseDto();
        try{
            settleUpResponseDto.setTransactions(settleUpService.settleUpUser(settleUpUserRequestDto.getUserId()));
        }
        catch(UserNotFoundException userNotFoundException){
            settleUpResponseDto.setResponse("User doesnt exist");
        }
        return settleUpResponseDto;
    }
}
