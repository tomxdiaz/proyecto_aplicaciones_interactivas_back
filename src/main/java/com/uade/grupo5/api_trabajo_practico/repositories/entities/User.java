package com.uade.grupo5.api_trabajo_practico.repositories.entities;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.uade.grupo5.api_trabajo_practico.dto.UserDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(nullable = false, unique = true)
    private String username;
    @NotNull
    @Column(nullable = false)
    private String name;
    @NotNull
    @Column(nullable = false)
    private String lastName;
    @NotNull
    @Column(nullable = false)
    private String emailAddress;
    @NotNull
    @Column(nullable = false)
    private LocalDate birthDate;
    @NotNull
    @Column(nullable = false)
    private String password;
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role rol;

    // @NotNull
    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(nullable = false, name = "cart_id")
    // private Cart cart;

    @NotNull
    @Column(nullable = false)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Buy> orders;

    @NotNull
    @Column(nullable = false)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<WishListItem> wishList;

    @NotNull
    @Column(nullable = false)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Search> lastSearches;

    public UserDTO toDTO() {
        return new UserDTO(
                this.id,
                this.username,
                this.name,
                this.lastName,
                this.emailAddress,
                this.birthDate,
                this.password,
                this.rol,
                /* this.cart, */
                this.orders,
                this.wishList,
                this.lastSearches);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }

}
