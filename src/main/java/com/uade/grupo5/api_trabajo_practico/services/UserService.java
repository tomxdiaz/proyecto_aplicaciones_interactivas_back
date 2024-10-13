package com.uade.grupo5.api_trabajo_practico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.uade.grupo5.api_trabajo_practico.exceptions.CartException;
import com.uade.grupo5.api_trabajo_practico.exceptions.UserException;
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

    // ** SIRVE **
		@Transactional
    public User createUser(RegisterRequest request) throws Exception {
			try {
				Boolean userExist = userRepository.existsByUsername(request.getUsername());
        
				if(userExist) throw new UserException("El usuario " + request.getUsername() + " ya existe");

				Cart cart = cartService.createCart();

				User user = new User(null, request.getUsername(), request.getName(), request.getLastName(),
								request.getEmailAddress(), LocalDate.parse(request.getBirthDate()),
								passwordEncoder.encode(request.getPassword()),
								Role.USER, cart, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

				user.assignCart(cart);

				return userRepository.save(user);
			}catch(UserException error){
        throw new UserException(error.getMessage());
      }catch(Exception error){
				throw new Exception("[UserService.createUser] -> " + error.getMessage());
			}
    }

    // ** SIRVE **
    public void updateRole(User user,Role role) throws Exception {
      try{
        user.setRole(role);
        this.updateUser(user);
      }catch(Exception error){
        throw new Exception("[UserService.updateRole] -> " + error.getMessage());
      }
    }

    // ** SIRVE **
    public User getUserByUsername(String username) throws Exception {
      try{
        return userRepository.findByUsername(username).orElseThrow(() -> new Exception("User not found"));
      }catch(Exception error){
        throw new Exception("[UserService.getUserByUsername] -> " + error.getMessage());
      }
    }

    // ** SIRVE **
    public List<User> getAllUsers() throws Exception {
      try{
        return userRepository.findAll();
      }catch(Exception error){
        throw new Exception("[UserService.getAllUsers] -> " + error.getMessage());
      }
    }

    // ** SIRVE **
    public User updateUser(User user) throws Exception {
      try{
        return userRepository.save(user);
      }catch(Exception error){
        throw new Exception("[UserService.updateUser] -> " + error.getMessage());
      }
    }

}
