package com.uade.grupo5.api_trabajo_practico.dto;

import java.util.Date;
import java.util.List;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String userName;
    private String name;
    private String surname;
    private String emailAddress;
    private Date birthDate;
    private String password;
    private String rol;
    /* private Cart cart; */
    private List<BuyDTO> orders;
    private List<WishListItemDTO> wishList;
    private List<SearchDTO> lastSearches;

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
                /* this.cart, */
                this.orders.stream().map(buy -> buy.toEntity()).toList(),
                this.wishList.stream().map(wish -> wish.toEntity()).toList(),
                this.lastSearches.stream().map(search -> search.toEntity()).toList()
                );
    }
}
