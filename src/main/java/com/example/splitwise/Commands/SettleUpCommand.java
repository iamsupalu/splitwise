package com.example.splitwise.Commands;

import com.example.splitwise.controller.SettleUpController;
import com.example.splitwise.dtos.SettleUpUserRequestDto;
import com.example.splitwise.dtos.SettleUpUserResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SettleUpCommand implements Command{
    SettleUpController settleUpController;

    public SettleUpCommand(SettleUpController settleUpController) {
        this.settleUpController = settleUpController;
    }

    @Override
    public boolean matches(String inp) {
        List<String> words= List.of(inp.split( " "));
        return words.size()==2 && words.get(1).equalsIgnoreCase(CommandKeywords.SETTLE_UP);
    }

    @Override
    public void execute(String inp) {
        List<String> words= List.of(inp.split( " "));
        SettleUpUserRequestDto settleUpUserRequestDto=new SettleUpUserRequestDto();
        settleUpUserRequestDto.setUserId(Integer.parseInt(words.get(0)));
        SettleUpUserResponseDto responseDto= settleUpController.settleUpUser(settleUpUserRequestDto);
        if(responseDto.getTransactions().isEmpty()){
            System.out.println("No expense found");
        }
    }
}
