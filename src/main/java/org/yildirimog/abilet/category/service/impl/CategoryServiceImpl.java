package org.yildirimog.abilet.category.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yildirimog.abilet.category.dto.CategoryRequestDto;
import org.yildirimog.abilet.category.dto.CategoryResponseDto;
import org.yildirimog.abilet.category.dto.CategoryUpdateDto;
import org.yildirimog.abilet.category.entity.Category;
import org.yildirimog.abilet.category.exception.CategoryAlreadyExistsException;
import org.yildirimog.abilet.category.exception.CategoryNotFoundException;
import org.yildirimog.abilet.category.mapper.CategoryMapper;
import org.yildirimog.abilet.category.repository.CategoryRepository;
import org.yildirimog.abilet.category.service.CategoryService;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryResponseDto create(CategoryRequestDto categoryRequestDto) {
        boolean exists = categoryRepository.existsByName(categoryRequestDto.name());

        if (exists) {
            throw new CategoryAlreadyExistsException(categoryRequestDto.name());
        }

        Category category = categoryMapper.toEntity(categoryRequestDto);
        Category savedCategory = categoryRepository.save(category);

        log.info("Category created: id={}, name={}", savedCategory.getId(), savedCategory.getName());
        return categoryMapper.toDto(savedCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseDto getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        return categoryMapper.toDto(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponseDto> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public CategoryResponseDto update(Long id, CategoryUpdateDto categoryUpdateDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        boolean nameConflict = !category.getName().equals(categoryUpdateDto.name())
                && categoryRepository.existsByName(categoryUpdateDto.name());

        if (nameConflict) {
            throw new CategoryAlreadyExistsException(categoryUpdateDto.name());
        }

        categoryMapper.updateEntity(categoryUpdateDto, category);
        Category updatedCategory = categoryRepository.save(category);

        log.info("Category updated: id={}, name={}", updatedCategory.getId(), updatedCategory.getName());
        return categoryMapper.toDto(updatedCategory);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        category.setDeletedAt(LocalDateTime.now());

        log.info("Category soft deleted: id={}", id);
    }
}
