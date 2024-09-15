package com.uade.grupo5.api_trabajo_practico.repositories.entities;

import java.util.Date;
import java.util.List;


import com.uade.grupo5.api_trabajo_practico.dto.UserDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String name;
    private String surname;
    private String emailAddress;
    private Date birthDate;
    private String password;
    private String rol;
    
    /* @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart; */

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Buy> orders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WishListItem> wishList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Search> lastSearches;

    public UserDTO toDTO() {
        return new UserDTO(
                this.id,
                this.userName,
                this.name,
                this.surname,
                this.emailAddress,
                this.birthDate,
                this.password,
                this.rol,
                /* this.cart, */
                this.orders.stream().map(Buy::toDTO).toList(),
                this.wishList.stream().map(WishListItem::toDTO).toList(),
                this.lastSearches.stream().map(Search::toDTO).toList()
                );
    }
}
