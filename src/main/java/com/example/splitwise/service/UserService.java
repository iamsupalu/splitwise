package com.example.splitwise.service;

import com.example.splitwise.exceptions.UserAlreadyExistsException;
import com.example.splitwise.exceptions.UserNotFoundException;
import com.example.splitwise.models.User;
import com.example.splitwise.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String phone, String password, String name) throws UserAlreadyExistsException {
        if(userRepository.findUserByPhone(phone).isPresent()){
            throw new UserAlreadyExistsException();
        }
        else {
            User user = new User();
            user.setPhone(phone);
            user.setName(name);
            user.setPassword(password);
            System.out.println(user);
            return userRepository.save(user);
        }
    }

    public User updatePassword(int userId,String password) throws UserNotFoundException {
        if(userRepository.findUserById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        else{
            User user=userRepository.findUserById(userId).get();
            user.setPassword(password);
            userRepository.save(user);
            return user;
        }
    }
}
