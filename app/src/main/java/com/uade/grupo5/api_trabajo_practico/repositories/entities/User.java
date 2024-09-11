package com.uade.grupo5.api_trabajo_practico.repositories.entities;

import java.util.Date;
import java.util.List;

import com.uade.grupo5.api_trabajo_practico.dto.UserDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String name;
    private String surname;
    private String emailAddress;
    private Date birthDate;
    private String password;
    private String rol;
    @OneToMany(mappedBy = "wish_list_item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WishListItem> wishList;
    @OneToMany(mappedBy = "search", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Search> lastSearches;
    @OneToMany(mappedBy = "buy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Buy> orders;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private List<Cart> carts;

    public UserDTO toDTO(){
        return new UserDTO(
                this.id,
                this.userName,
                this.name,
                this.surname,
                this.emailAddress,
                this.birthDate,
                this.password,
                this.rol,
                this.wishList,
                this.lastSearches,
                this.orders,
                this.carts);
    }
}
