package com.uade.grupo5.api_trabajo_practico.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Buy;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;
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
  /* private CartService cartService; */
  @Autowired
  private UserService userService;

  @PostMapping("/initialize")
  public ResponseEntity<Void> initializeDB() throws Exception {

    productService.createProduct(new Product(null, "Royal Canin adulto 3KG", "Royal Canin adulto mordida chica 3KG",
        15.00, Arrays.asList("https://http2.mlstatic.com/D_NQ_NP_835947-MLA54584711620_032023-O.webp"), "", 50, false,
        null));
    productService.createProduct(new Product(null, "Royal Canin adulto 15KG", "Royal Canin adulto mordida grande 15KG",
        45.00, Arrays.asList("https://http2.mlstatic.com/D_NQ_NP_965386-MLA31011602147_062019-O.webp"), "", 30, false,
        null));
    productService.createProduct(new Product(null, "Dog Chow adulto 3KG", "Dog Chow adulto mordida chica 3KG", 12.00,
        Arrays.asList(
            "https://petbaar.vtexassets.com/arquivos/ids/159373-150-auto?v=638340990045330000&width=150&height=auto&aspect=true"),
        "", 45, false, null));
    productService.createProduct(new Product(null, "Dog Chow adulto 15KG", "Dog Chow adulto mordida grande 15KG", 42.00,
        Arrays.asList("https://statics.dinoonline.com.ar/imagenes/full_600x600_ma/2520746_f.jpg"), "", 28, false,
        null));
    productService.createProduct(new Product(null, "Guante cepillo",
        "Cepillo Guante True Touch Mascotas Sacapelos Y Masajeador", 8.00,
        Arrays.asList("https://http2.mlstatic.com/D_NQ_NP_810639-MLU70500938624_072023-O.webp"), "", 15, false, null));

    userService.createUser(new User(null, "Ivan", "Ivan", "Natucce", "anatucce@uade.edu.ar",
        new SimpleDateFormat("dd/MM/yyyy").parse("04/06/1999"), "1234", "Admin", new ArrayList<>(), new ArrayList<>(),
        new ArrayList<>()));
    userService.createUser(new User(null, "Joanna", "Joanna", "Lazarte", "jlazartelagos@uade.edu.ar",
        new SimpleDateFormat("dd/MM/yyyy").parse("04/06/1999"), "1234", "Admin", new ArrayList<>(), new ArrayList<>(),
        new ArrayList<>()));
    userService.createUser(new User(null, "Patricio", "Patricio", "Plem", "pplem@uade.edu.ar",
        new SimpleDateFormat("dd/MM/yyyy").parse("04/06/1999"), "1234", "Admin", new ArrayList<>(), new ArrayList<>(),
        new ArrayList<>()));
    userService.createUser(new User(null, "Santiago", "Santiago", "Lopez", "santiagonlopez@uade.edu.ar",
        new SimpleDateFormat("dd/MM/yyyy").parse("04/06/1999"), "1234", "Admin", new ArrayList<>(), new ArrayList<>(),
        new ArrayList<>()));
    userService.createUser(new User(null, "Tomas", "Tomas", "Diaz", "todiaz@uade.edu.ar",
        new SimpleDateFormat("dd/MM/yyyy").parse("04/06/1999"), "1234", "Admin", new ArrayList<>(), new ArrayList<>(),
        new ArrayList<>()));

    buyService
        .createBuy(new Buy(null, new SimpleDateFormat("dd/MM/yyyy").parse("15/09/2024"), userService.getUserById(2L)));

    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

}
