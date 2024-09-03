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

    public UserDTO toDTO(){
        return new UserDTO(
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
