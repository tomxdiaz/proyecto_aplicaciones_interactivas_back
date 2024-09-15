package com.uade.grupo5.api_trabajo_practico.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDTO {
    private Long productId;
    private int quantity;
}
