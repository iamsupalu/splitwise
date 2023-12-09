package com.example.splitwise.Commands;

import com.example.splitwise.controller.UserController;
import com.example.splitwise.dtos.SignUpRequestDto;
import com.example.splitwise.dtos.SignUpResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegisterCommand implements Command{
    private UserController userController;

    @Autowired
    public RegisterCommand(UserController userController) {
        this.userController = userController;
    }

    @Override
    public boolean matches(String inp) {
        List<String> words=List.of(inp.split(" "));
        return words.size()==4 &&
                words.get(0).equalsIgnoreCase(CommandKeywords.REGISTER_USER);
    }

    @Override
    public void execute(String inp) {
        List<String> words=List.of(inp.split(" "));
        SignUpRequestDto requestDto=new SignUpRequestDto();
        requestDto.setName(words.get(1));
        requestDto.setPhone(words.get(2));
        requestDto.setPassword(words.get(3));
        SignUpResponseDto responseDto=userController.signup(requestDto);
//        System.out.println(responseDto.getId());
        System.out.println(responseDto.getResponse());
    }
}
