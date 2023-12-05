package com.example.splitwise.service;

import com.example.splitwise.exceptions.UserAlreadyExistsException;
import com.example.splitwise.models.User;
import com.example.splitwise.repository.UserRepository;


public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String phone, String password, String name) throws UserAlreadyExistsException {
        if(userRepository.findUserByphone(phone).isPresent()){
            throw new UserAlreadyExistsException();
        }
        else{
            User user=new User();
            user.setPhone(phone);
            user.setName(name);
            user.setPassword(password);
            return userRepository.saveUser(user);
        }
    }
}
