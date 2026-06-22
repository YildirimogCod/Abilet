package org.yildirimog.abilet.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RegisterRequest(
       @NotBlank String firstName,
       @NotBlank String lastName,
       @Email  @NotBlank String email,
       @NotBlank @Size(min = 8) String password,
       @NotNull LocalDate birthDate
) {
}
