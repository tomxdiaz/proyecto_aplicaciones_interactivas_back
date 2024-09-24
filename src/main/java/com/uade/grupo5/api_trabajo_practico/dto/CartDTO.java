package com.uade.grupo5.api_trabajo_practico.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Long id;
    private User user;
    private List<ItemDTO> items;
}
