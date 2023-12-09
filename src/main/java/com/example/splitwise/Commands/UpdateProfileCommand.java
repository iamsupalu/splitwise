package com.example.splitwise.Commands;

import com.example.splitwise.controller.UserController;
import com.example.splitwise.dtos.SignUpRequestDto;
import com.example.splitwise.dtos.SignUpResponseDto;
import com.example.splitwise.dtos.UpdateProfileRequestDto;
import com.example.splitwise.dtos.UpdateProfileResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component

public class UpdateProfileCommand implements Command{

    UserController userController;

    @Autowired
    public UpdateProfileCommand(UserController userController) {
        this.userController = userController;
    }

    @Override
    public boolean matches(String inp) {
        List<String> words=List.of(inp.split(" "));
        return words.size()==3 &&
                words.get(1).equalsIgnoreCase(CommandKeywords.UPDATE_PROFILE);
    }

    @Override
    public void execute(String inp) {
        List<String> words=List.of(inp.split(" "));
        UpdateProfileRequestDto requestDto=new UpdateProfileRequestDto();
        requestDto.setUserId(Integer.parseInt(words.get(0)));
        requestDto.setPassword(words.get(2));
        UpdateProfileResponseDto responseDto=userController.updatePassword(requestDto);
        System.out.println(responseDto.getResponse()+" for id " + responseDto.getUserId());
    }
}