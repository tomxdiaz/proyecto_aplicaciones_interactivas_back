package com.uade.grupo5.api_trabajo_practico.dto;

import java.sql.Date;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Search;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchDTO {
  private Long id;
  private Date date;
  private ProductDTO product;
  private UserDTO user;

  public Search toEntity(){
    return Search.builder()
                 .id(this.id)
                 .date(this.date)
                 .product(this.product.toEntity())
                 .user(this.user.toEntity())
                 .build();
  }
}
