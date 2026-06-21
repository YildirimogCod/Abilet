package org.yildirimog.abilet.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CategoryRequestDto(
        @NotBlank
        @Size(min = 1, max = 30)
        String name,
        @NotBlank
        @Size(min = 1, max = 100)
        String description
) {
}
