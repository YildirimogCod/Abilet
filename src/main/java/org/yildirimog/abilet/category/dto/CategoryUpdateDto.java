package org.yildirimog.abilet.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryUpdateDto(
        @NotBlank
        @Size(max = 30)
        String name,
        @NotBlank
        @Size(max = 100)
        String description
) {
}
