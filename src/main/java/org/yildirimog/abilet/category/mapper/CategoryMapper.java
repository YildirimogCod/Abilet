package org.yildirimog.abilet.category.mapper;

import org.springframework.stereotype.Component;
import org.yildirimog.abilet.category.dto.CategoryRequestDto;
import org.yildirimog.abilet.category.dto.CategoryResponseDto;
import org.yildirimog.abilet.category.entity.Category;

@Component
public class CategoryMapper {
    public Category toEntity(CategoryRequestDto dto) {
       Category category = Category.builder()
               .name(dto.name())
               .description(dto.description())
               .build();
       return category;
    }

    public CategoryResponseDto toDto(Category category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}
