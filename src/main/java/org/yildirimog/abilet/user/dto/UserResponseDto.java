package org.yildirimog.abilet.user.dto;

import org.yildirimog.abilet.user.enums.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserResponseDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        LocalDate birthDate,
        Role role,
        LocalDateTime createdAt
) {
}
