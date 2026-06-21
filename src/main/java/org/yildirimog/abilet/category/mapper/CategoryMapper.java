package org.yildirimog.abilet.category.mapper;

import org.springframework.stereotype.Component;
import org.yildirimog.abilet.category.dto.CategoryRequestDto;
import org.yildirimog.abilet.category.dto.CategoryResponseDto;
import org.yildirimog.abilet.category.dto.CategoryUpdateDto;
import org.yildirimog.abilet.category.entity.Category;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryRequestDto dto) {
        return Category.builder()
                .name(dto.name())
                .description(dto.description())
                .build();
    }

    public CategoryResponseDto toDto(Category category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    public void updateEntity(CategoryUpdateDto dto, Category category) {
        category.setName(dto.name());
        category.setDescription(dto.description());
    }
}
