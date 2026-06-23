package org.yildirimog.abilet.auth.dto;

import org.yildirimog.abilet.user.enums.Role;

import java.time.LocalDateTime;

public record RegisterResponse(
        Long id,
        String email,
        Role role,
        LocalDateTime createdAt
) {
}
