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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String name;
    private String lastName;
    @Column(unique = true)
    private String emailAddress;
    private LocalDate birthDate;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    @JsonManagedReference
    private Cart cart;

    @NotNull
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Buy> orders;

    @NotNull
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL ,orphanRemoval = true)
    @JsonManagedReference
    private List<WishListItem> wishList;

    @NotNull
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
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
                this.role,
                this.cart,
                this.orders,
                this.wishList,
                this.lastSearches);
    }

    public void updateData(User newUser){
        setName(newUser.getName());
        setLastName(newUser.getLastName());
        setBirthDate(newUser.getBirthDate());
        setEmailAddress(newUser.getEmailAddress());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public void assignCart(Cart cart) {
        this.cart.setUser(this);
    }

}
