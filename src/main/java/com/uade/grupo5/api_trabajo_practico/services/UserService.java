package com.uade.grupo5.api_trabajo_practico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.uade.grupo5.api_trabajo_practico.repositories.UserRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Cart;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.RegisterRequest;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Role;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(
            RegisterRequest request) throws Exception {
        Cart cart = cartService.createCart();

        User user = new User(null, request.getUsername(), request.getName(), request.getLastName(),
                request.getEmailAddress(), LocalDate.parse(request.getBirthDate()),
                passwordEncoder.encode(request.getPassword()),
                Role.USER, cart, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        return userRepository.save(user);

    }

    public void updateRole(User user,
            Role role) throws Exception {

        user.setRole(role);
        this.updateUser(user);

    }

    public User getUserById(Long id) throws Exception {
        return userRepository.getReferenceById(id);
    }

    public User getUserByUsername(String username) throws Exception {
        return userRepository.findByUsername(username).orElseThrow(() -> new Exception("User not found"));
    }

    public List<User> getAllUsers() throws Exception {
        return userRepository.findAll();
    }

    public User updateUser(
            User user) throws Exception {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) throws Exception {
        userRepository.deleteById(id);
    }

}
