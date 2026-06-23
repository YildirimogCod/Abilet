package org.yildirimog.abilet.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RegisterRequest(
       @NotBlank @Size(max = 100) String firstName,
       @NotBlank @Size(max = 100) String lastName,
       @Email @NotBlank @Size(max = 255) String email,
       @NotBlank @Size(min = 8, max = 64) String password,
       @NotNull @Past LocalDate birthDate
) {
}
