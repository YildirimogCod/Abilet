package org.yildirimog.abilet.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UserUpdateDto(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotNull LocalDate birthDate
) {
}
