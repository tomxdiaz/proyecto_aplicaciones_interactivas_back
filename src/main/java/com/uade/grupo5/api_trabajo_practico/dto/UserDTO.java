package com.uade.grupo5.api_trabajo_practico.dto;

import java.util.Date;
import java.util.List;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Buy;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String userName;
    private String name;
    private String surname;
    private String emailAddress;
    private Date birthDate;
    private String password;
    private String rol;
    private Cart cart;
    private List<Buy> orders;
    private List<WishListItem> wishList;
    private List<Search> lastSearches;

    public User toEntity() {
        return new User(
                this.id,
                this.userName,
                this.name,
                this.surname,
                this.emailAddress,
                this.birthDate,
                this.password,
                this.rol,
                this.cart,
                this.orders,
                this.wishList,
                this.lastSearches);
    }
}
