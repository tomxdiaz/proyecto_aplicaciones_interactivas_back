package com.uade.grupo5.api_trabajo_practico.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Buy;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Cart;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Role;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Search;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.User;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.WishListItem;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String emailAddress;
    @NotNull
    private LocalDate birthDate;
    @NotNull
    private String password;
    private Role role;
    @JsonManagedReference
    private Cart cart;
    private List<Buy> orders;
    private List<WishListItem> wishList;
    private List<Search> lastSearches;

    public User toEntity() {
        return new User(
                this.id,
                this.username,
                this.name,
                this.lastName,
                this.emailAddress,
                this.birthDate,
                this.password,
                this.role,
                this.cart,
                this.orders,
                this.wishList,
                this.lastSearches);
    }
}
