package com.uade.grupo5.api_trabajo_practico.repositories.entities;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String userName;
    private String name;
    private String surname;
    private String emailAddress;
    private LocalDate birthDate;
    private String password;
    private Role rol;
}
