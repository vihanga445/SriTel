package com.example.user_service.service;

import com.example.user_service.dto.UserRegistrationDTO;
import com.example.user_service.entity.User;
import com.example.user_service.exception.UserAlreadyExistsException;
import com.example.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(UserRegistrationDTO dto){

        Optional<User> existingUser = userRepository.findByEmail(dto.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("Email is already in use: " + dto.getEmail());
        }

        // Create new user entity
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setFullName(dto.getFullName());
        user.setPassword(dto.getPassword());


        return userRepository.save(user);



    }

}
