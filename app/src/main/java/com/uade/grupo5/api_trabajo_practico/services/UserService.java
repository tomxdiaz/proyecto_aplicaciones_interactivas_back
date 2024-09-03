package com.uade.grupo5.api_trabajo_practico.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.grupo5.api_trabajo_practico.repositories.UserRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;

@Service

public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) throws Exception {
        User user= userRepository.getReferenceById(id);
        return user;

    }
}
