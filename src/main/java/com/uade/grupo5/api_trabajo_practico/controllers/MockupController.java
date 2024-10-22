package com.uade.grupo5.api_trabajo_practico.controllers;

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
          List.of(
              "https://kywiec.vtexassets.com/arquivos/ids/203819-800-auto?v=638615157543100000&width=800&height=auto&aspect=true",
              "https://petmax.ec/140-thickbox_default/avant-croquetas-premium-para-razas-miniaturas-y-pequenas.jpg"),
          "Ingredientes naturales, tamaño de bolsa 15kg", 50, true, categoria1Creada);
      Product producto2 = new Product(null, "Snacks para Perros",
          "Snacks saludables y sabrosos para premiar a tu perro", 9.99,
          List.of(
              "https://st4.depositphotos.com/36803914/40720/v/450/depositphotos_407204302-stock-illustration-flyer-template-dogs-design-brochure.jpg",
              "https://cdn.onemars.net/sites/pedigree_es_NkyIN_lYfo/image/5010394133852_1676979593986.png"),
          "Bolsa de 500g, sin conservantes artificiales", 100, false, categoria1Creada);
      Product producto3 = new Product(null, "Rascador para Gatos",
          "Rascador de varios niveles para mantener a tu gato entretenido", 35.49,
          List.of(
              "https://static.miscota.com/media/1/photos/products/028412/rascador-para-gato-bella-gris-29-x-29-x-39-cm-4016598346129-1-2_3_g.jpg",
              "https://static.miscota.com/media/1/photos/products/130555/mueble-rascador-para-gatos-v-high-base-vesper-3-621de8e426501_g.jpg"),
          "Material de sisal natural, incluye juguetes", 25, true, categoria2Creada);
      Product producto4 = new Product(null, "Pelota con Plumas",
          "Juguete interactivo con plumas para estimular a tu gato", 5.99,
          List.of("https://www.animalia.com.ar/wp-content/uploads/2023/09/24-1.png",
              "https://static.miscota.com/media/1/photos/products/112329/112329-8019808121826-a_1_g.jpg"),
          "Ideal para mantener a tu gato activo", 150, false, categoria2Creada);
      Product producto5 = new Product(null, "Filtro de Agua para Peceras",
          "Filtro de agua de alta eficiencia para peceras medianas", 45.00,
          List.of("https://http2.mlstatic.com/D_NQ_NP_981084-MLA49489096072_032022-O.webp",
              "https://http2.mlstatic.com/D_NQ_NP_919777-MLA50157818510_062022-O.webp"),
          "Adecuado para peceras de hasta 50 litros", 30, true, categoria3Creada);
      Product producto6 = new Product(null, "Piedras Decorativas para Peceras",
          "Piedras decorativas de colores para peceras", 12.00,
          List.of("https://cuestiondepeces.com.ar/wp-content/uploads/2018/07/Piedras-Magma.jpg",
              "https://catycanar.vtexassets.com/arquivos/ids/155839/2087.jpg?v=637732965952170000"),
          "Pack de 1kg, varios colores disponibles", 75, false, categoria3Creada);
      Product producto7 = new Product(null, "Cama Premium para Perros",
          "Cama ergonómica y cómoda para perros grandes", 85.99,
          List.of(
              "https://d28hi93gr697ol.cloudfront.net/071e89ac-46a5-8ab3/img/Producto/1370/01-1612902129-632127176dcdb.jpeg",
              "https://simmonsarg.vtexassets.com/arquivos/ids/155917/SIMMONS--ColchonPerros---03.jpg?v=637153207559600000"),
          "Material impermeable, fácil de limpiar", 20, true, categoria4Creada);
      Product producto8 = new Product(null, "Cama para Gatos",
          "Cama acolchada y suave para gatos", 29.99,
          List.of("https://http2.mlstatic.com/D_NQ_NP_694464-MLA76555816042_052024-O.webp",
              "https://http2.mlstatic.com/D_NQ_NP_876609-MLA31650797888_082019-O.webp"),
          "Ideal para gatos pequeños y medianos", 40, false, categoria4Creada);
      Product producto9 = new Product(null, "Shampoo para Perros",
          "Shampoo hipoalergénico para perros con piel sensible", 14.50,
          List.of(
              "https://acdn.mitiendanube.com/stores/001/151/906/products/osspretpgp1-196570682675c509c716690723431774-640-0.jpeg",
              "https://www.animalia.com.ar/wp-content/uploads/2021/09/Diseno-sin-titulo-29-8.png"),
          "Botella de 500ml, sin parabenos", 60, true, categoria5Creada);
      Product producto10 = new Product(null, "Cepillo para Mascotas",
          "Cepillo suave para eliminar pelo muerto de tu mascota", 7.50,
          List.of(
              "https://static.miscota.com/media/1/photos/products/114324/set-de-cepillos-para-perros-3-piezas_1_g.jpeg",
              "https://static.miscota.com/media/1/photos/products/175690/cepillo-para-perros-grandes-de-pelo-corto_1_g.jpeg"),
          "Ideal para perros y gatos", 80, false, categoria5Creada);

      productService.createProduct(producto1);
      productService.createProduct(producto2);
      productService.createProduct(producto3);
      productService.createProduct(producto4);
      productService.createProduct(producto5);
      productService.createProduct(producto6);
      productService.createProduct(producto7);
      productService.createProduct(producto8);
      productService.createProduct(producto9);
      productService.createProduct(producto10);

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
