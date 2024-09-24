package com.uade.grupo5.api_trabajo_practico.repositories.entities;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotNull
    private String username;
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String emailAddress;
    @NotNull
    private String birthDate;
    @NotNull
    private String password;
}
