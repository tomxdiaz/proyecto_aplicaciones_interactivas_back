package com.uade.grupo5.api_trabajo_practico.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.grupo5.api_trabajo_practico.exceptions.UserException;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Category;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.ResponseData;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Role;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;
import com.uade.grupo5.api_trabajo_practico.services.CategoryService;
import com.uade.grupo5.api_trabajo_practico.services.ProductService;
import com.uade.grupo5.api_trabajo_practico.services.UserService;

@RestController
@RequestMapping("/mockup")
public class MockupController {
  @Autowired
  private UserService userService;

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private ProductService productService;

  @PostMapping("/initialize")
  public ResponseEntity<ResponseData<String>> initializeDB(@AuthenticationPrincipal UserDetails userDetails) {
    try {
      userService.getUserByUsername(userDetails.getUsername());

      Category categoria1 = new Category(null, "Alimentos para Perros",
          "Comida balanceada para perros de todas las edades.");
      Category categoria2 = new Category(null, "Juguetes para Gatos",
          "Juguetes interactivos para mantener a tu gato entretenido.");
      Category categoria3 = new Category(null, "Accesorios para Peceras",
          "Filtros, piedras y otros accesorios para peceras.");
      Category categoria4 = new Category(null, "Camas para Mascotas",
          "Camas cómodas para perros y gatos de todas las razas.");
      Category categoria5 = new Category(null, "Productos de Higiene",
          "Productos para la higiene y el cuidado de tu mascota.");

      Category categoria1Creada = categoryService.createCategory(categoria1);
      Category categoria2Creada = categoryService.createCategory(categoria2);
      Category categoria3Creada = categoryService.createCategory(categoria3);
      Category categoria4Creada = categoryService.createCategory(categoria4);
      Category categoria5Creada = categoryService.createCategory(categoria5);

      Product producto1 = new Product(null, "Croquetas Premium para Perros",
          "Alimento de alta calidad para perros adultos", 25.99,
          List.of("imagen1.jpg", "imagen2.jpg"),
          "Ingredientes naturales, tamaño de bolsa 15kg", 50, true, categoria1Creada);
      Product producto2 = new Product(null, "Snacks para Perros",
          "Snacks saludables y sabrosos para premiar a tu perro", 9.99,
          List.of("imagen3.jpg", "imagen4.jpg"),
          "Bolsa de 500g, sin conservantes artificiales", 100, false, categoria1Creada);
      Product producto3 = new Product(null, "Rascador para Gatos",
          "Rascador de varios niveles para mantener a tu gato entretenido", 35.49,
          List.of("imagen5.jpg", "imagen6.jpg"),
          "Material de sisal natural, incluye juguetes", 25, true, categoria2Creada);
      Product producto4 = new Product(null, "Pelota con Plumas",
          "Juguete interactivo con plumas para estimular a tu gato", 5.99,
          List.of("imagen7.jpg", "imagen8.jpg"),
          "Ideal para mantener a tu gato activo", 150, false, categoria2Creada);
      Product producto5 = new Product(null, "Filtro de Agua para Peceras",
          "Filtro de agua de alta eficiencia para peceras medianas", 45.00,
          List.of("imagen9.jpg", "imagen10.jpg"),
          "Adecuado para peceras de hasta 50 litros", 30, true, categoria3Creada);
      Product producto6 = new Product(null, "Piedras Decorativas para Peceras",
          "Piedras decorativas de colores para peceras", 12.00,
          List.of("imagen11.jpg", "imagen12.jpg"),
          "Pack de 1kg, varios colores disponibles", 75, false, categoria3Creada);
      Product producto7 = new Product(null, "Cama Premium para Perros",
          "Cama ergonómica y cómoda para perros grandes", 85.99,
          List.of("imagen13.jpg", "imagen14.jpg"),
          "Material impermeable, fácil de limpiar", 20, true, categoria4Creada);
      Product producto8 = new Product(null, "Cama para Gatos",
          "Cama acolchada y suave para gatos", 29.99,
          List.of("imagen15.jpg", "imagen16.jpg"),
          "Ideal para gatos pequeños y medianos", 40, false, categoria4Creada);
      Product producto9 = new Product(null, "Shampoo para Perros",
          "Shampoo hipoalergénico para perros con piel sensible", 14.50,
          List.of("imagen17.jpg", "imagen18.jpg"),
          "Botella de 500ml, sin parabenos", 60, true, categoria5Creada);
      Product producto10 = new Product(null, "Cepillo para Mascotas",
          "Cepillo suave para eliminar pelo muerto de tu mascota", 7.50,
          List.of("imagen19.jpg", "imagen20.jpg"),
          "Ideal para perros y gatos", 80, false, categoria5Creada);

      Product producto1Creado = productService.createProduct(producto1);
      Product producto2Creado = productService.createProduct(producto2);
      Product producto3Creado = productService.createProduct(producto3);
      Product producto4Creado = productService.createProduct(producto4);
      Product producto5Creado = productService.createProduct(producto5);
      Product producto6Creado = productService.createProduct(producto6);
      Product producto7Creado = productService.createProduct(producto7);
      Product producto8Creado = productService.createProduct(producto8);
      Product producto9Creado = productService.createProduct(producto9);
      Product producto10Creado = productService.createProduct(producto10);

      return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success("Base inicializada correctamente!"));

    } catch (UserException error) {
      return ResponseEntity.status(HttpStatus.OK).body(ResponseData.error(error.getMessage()));
    } catch (Exception error) {
      System.out.printf("[MockupController.initializeDB] -> %s", error.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(ResponseData.error("No se pudo inicializar la DB"));
    }
  }

  @PutMapping("/makeAllUsersAdmin")
  public ResponseEntity<ResponseData<String>> makeAllUsersAdmin() {
    try {
      for (User user : userService.getAllUsers()) {
        userService.updateRole(user, Role.ADMIN);
      }
      return ResponseEntity.status(HttpStatus.OK)
          .body(ResponseData.success("Todos los usuarios son ahora administradores!"));

    } catch (Exception error) {
      System.out.printf("[MockupController.makeAllUsersAdmin] -> %s", error.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseData.error(error.getMessage()));
    }
  }

}
