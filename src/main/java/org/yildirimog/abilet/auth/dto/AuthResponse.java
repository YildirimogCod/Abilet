package org.yildirimog.abilet.auth.dto;

import org.yildirimog.abilet.user.enums.Role;

public record AuthResponse(
        String token,
        String tokenType,
        String email,
        Role role
) {
}
