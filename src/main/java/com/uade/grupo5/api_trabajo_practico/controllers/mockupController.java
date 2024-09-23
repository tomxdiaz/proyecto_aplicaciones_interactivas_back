package com.uade.grupo5.api_trabajo_practico.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.grupo5.api_trabajo_practico.repositories.UserRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Buy;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.RegisterRequest;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Role;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;
import com.uade.grupo5.api_trabajo_practico.services.AuthenticationService;
import com.uade.grupo5.api_trabajo_practico.services.BuyService;
import com.uade.grupo5.api_trabajo_practico.services.ProductService;
import com.uade.grupo5.api_trabajo_practico.services.UserService;

@RestController
@RequestMapping("/mockup")
public class mockupController {

  @Autowired
  private BuyService buyService;

  @Autowired
  private ProductService productService;

  // @Autowired
  /* private CartService cartService; */

  @Autowired
  private UserService userService;

  @Autowired
  private AuthenticationService authService;

  // only for create admin users purpose
  @Autowired
  private UserRepository userRepository;

  // TODO: change to postMapping before deploy
  @GetMapping("/initialize")
  public ResponseEntity<String> initializeDB() {
    try {
      productService.createProduct(new Product(null, "Royal Canin adulto 3KG",
          "Royal Canin adulto mordida chica 3KG", 15.00,
          Arrays.asList("https://http2.mlstatic.com/D_NQ_NP_835947-MLA54584711620_032023-O.webp"),
          "", 50, false, null));
      productService.createProduct(new Product(null, "Royal Canin adulto 15KG",
          "Royal Canin adulto mordida grande 15KG", 45.00,
          Arrays.asList("https://http2.mlstatic.com/D_NQ_NP_965386-MLA31011602147_062019-O.webp"),
          "", 30, false, null));
      productService.createProduct(new Product(null, "Dog Chow adulto 3KG",
          "Dog Chow adulto mordida chica 3KG", 12.00,
          Arrays.asList(
              "https://petbaar.vtexassets.com/arquivos/ids/159373-150-auto?v=638340990045330000&width=150&height=auto&aspect=true"),
          "", 45, false, null));
      productService.createProduct(new Product(null, "Dog Chow adulto 15KG",
          "Dog Chow adulto mordida grande 15KG", 42.00,
          Arrays.asList("https://statics.dinoonline.com.ar/imagenes/full_600x600_ma/2520746_f.jpg"),
          "", 28, false, null));
      productService.createProduct(new Product(null, "Guante cepillo",
          "Cepillo Guante True Touch Mascotas Sacapelos Y Masajeador", 8.00,
          Arrays.asList("https://http2.mlstatic.com/D_NQ_NP_810639-MLU70500938624_072023-O.webp"),
          "", 15, false, null));

      userRepository.save(new User(null, "Joanna", "Joanna", "Lazarte", "jlazartelagos@uade.edu.ar",
          LocalDate.of(2020, 1, 8), "1234", Role.ADMIN, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
      userRepository.save(new User(null, "Ivan", "Ivan", "Natucce", "anatucce@uade.edu.ar",
          LocalDate.of(2020, 1, 8), "1234", Role.ADMIN, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
      userRepository.save(new User(null, "Patricio", "Patricio", "Plem", "pplem@uade.edu.ar",
          LocalDate.of(2020, 1, 8), "1234", Role.ADMIN, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
      userRepository.save(new User(null, "Santiago", "Santiago", "Lopez", "santiagonlopez@uade.edu.ar",
          LocalDate.of(2020, 1, 8), "1234", Role.ADMIN, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
      userRepository.save(new User(null, "Tomas", "Tomas", "Diaz", "todiaz@uade.edu.ar",
          LocalDate.of(2020, 1, 8), "1234", Role.ADMIN, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

      // Test customer
      authService.register(new RegisterRequest("JuanPerez", "Juan", "Perez", "santiagonlopez@uade.edu.ar",
          "2020-01-08", "1234"));

      buyService.createBuy(new Buy(null, LocalDate.now(), userService.getUserById(2L)));

      return ResponseEntity.status(HttpStatus.OK).body("Base inicializada correctamente!");
    } catch (Exception error) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
  }

}
