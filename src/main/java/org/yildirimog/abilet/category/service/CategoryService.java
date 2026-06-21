package org.yildirimog.abilet.category.service;

import org.yildirimog.abilet.category.dto.CategoryRequestDto;
import org.yildirimog.abilet.category.dto.CategoryResponseDto;
import org.yildirimog.abilet.category.dto.CategoryUpdateDto;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto create(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto getById(Long id);
    List<CategoryResponseDto> getAll();
    CategoryResponseDto update(Long id, CategoryUpdateDto categoryUpdateDto);
    void delete(Long id);
}
