package org.yildirimog.abilet.category.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.yildirimog.abilet.category.dto.CategoryRequestDto;
import org.yildirimog.abilet.category.dto.CategoryResponseDto;
import org.yildirimog.abilet.category.dto.CategoryUpdateDto;

public interface CategoryService {
    CategoryResponseDto create(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto getById(Long id);
    Page<CategoryResponseDto> getAll(Pageable pageable);
    CategoryResponseDto update(Long id, CategoryUpdateDto categoryUpdateDto);
    void delete(Long id);
}
