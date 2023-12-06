package com.example.splitwise.controller;

import com.example.splitwise.dtos.SignUpRequestDto;
import com.example.splitwise.dtos.SignUpResponseDto;
import com.example.splitwise.exceptions.UserAlreadyExistsException;
import com.example.splitwise.exceptions.UserNotExistsException;
import com.example.splitwise.models.User;
import com.example.splitwise.service.UserService;

public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignUpResponseDto signup(SignUpRequestDto signUpRequestDto){
        SignUpResponseDto signUpResponseDto=new SignUpResponseDto();
        try{
            User user =userService.registerUser(signUpRequestDto.getPhone(),
                signUpRequestDto.getPassword(),
                signUpRequestDto.getName());
            signUpResponseDto.setId(user.getId());
            signUpResponseDto.setResponse("user : "+user.getName()+"/n"+"registered successfully");
        }
        catch (UserAlreadyExistsException userAlreadyExistsException){
            signUpResponseDto.setResponse("User already exist try logging in");
        }
        return signUpResponseDto;
    }
    public SignUpResponseDto updatePassword(SignUpRequestDto signUpRequestDto){
        SignUpResponseDto signUpResponseDto=new SignUpResponseDto();
        try{
            User user=userService.updatePassword(signUpRequestDto.getPhone(),
                    signUpRequestDto.getPassword());
            signUpResponseDto.setId(user.getId());
            signUpResponseDto.setResponse("password updated successfully");
        }
        catch(UserNotExistsException userNotExistsException){
            signUpResponseDto.setResponse("User doesn't exist");
        }
        return signUpResponseDto;
    }
}
