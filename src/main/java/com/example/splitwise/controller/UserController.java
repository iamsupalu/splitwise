package com.example.splitwise.controller;

import com.example.splitwise.dtos.SignUpRequestDto;
import com.example.splitwise.dtos.SignUpResponseDto;
import com.example.splitwise.dtos.UpdateProfileRequestDto;
import com.example.splitwise.dtos.UpdateProfileResponseDto;
import com.example.splitwise.exceptions.UserAlreadyExistsException;
import com.example.splitwise.exceptions.UserNotFoundException;
import com.example.splitwise.models.User;
import com.example.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    UserService userService;

    @Autowired
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
            signUpResponseDto.setResponse("user "+user.getName()+" registered successfully"+" with id :" + user.getId());
        }
        catch (UserAlreadyExistsException userAlreadyExistsException){
            signUpResponseDto.setResponse("User already exist try logging in");
        }
        return signUpResponseDto;
    }
    public UpdateProfileResponseDto updatePassword(UpdateProfileRequestDto updateProfileRequestDto){
        UpdateProfileResponseDto updateProfileResponseDto=new UpdateProfileResponseDto();
        try{
            User user=userService.updatePassword(updateProfileRequestDto.getUserId(),
                    updateProfileRequestDto.getPassword());
            updateProfileResponseDto.setUserId(user.getId());
            updateProfileResponseDto.setResponse("password updated successfully");
        }
        catch(UserNotFoundException userNotExistsException){
            updateProfileResponseDto.setResponse("User doesn't exist");
        }
        return updateProfileResponseDto;
    }
}
