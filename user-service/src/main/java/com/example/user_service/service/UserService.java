package com.example.user_service.service;

import com.example.user_service.dto.UserRegistrationDTO;
import com.example.user_service.entity.User;

public interface UserService {

    User registerUser(UserRegistrationDTO registrationDTO);
}
