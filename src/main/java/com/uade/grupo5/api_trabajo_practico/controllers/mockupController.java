package com.uade.grupo5.api_trabajo_practico.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;
import com.uade.grupo5.api_trabajo_practico.services.BuyService;
import com.uade.grupo5.api_trabajo_practico.services.ProductService;
import com.uade.grupo5.api_trabajo_practico.services.UserService;

@RestController
@RequestMapping("/initialize")
public class mockupController {

  @Autowired
  private BuyService buyService;
  private ProductService productService;
  /* private CartService cartService; */
  private UserService userService;

  /* public void initializeDB(){
    productService.createProduct(new Product())
  } */
  
}
