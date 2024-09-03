package com.uade.grupo5.api_trabajo_practico.dto;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String emailAddress;
    private Date birthDate;
    private String password;
    private String rol;
    private List<Product> whishList;
    private List<Product> lastSearches;
    private List<Product> orders;

    public User toEntity() {
        return new User(
                this.id,
                this.username,
                this.name,
                this.surname,
                this.emailAddress,
                this.birthDate,
                this.password,
                this.rol,
                this.whishList,
                this.lastSearches,
                this.orders);
    }
}
