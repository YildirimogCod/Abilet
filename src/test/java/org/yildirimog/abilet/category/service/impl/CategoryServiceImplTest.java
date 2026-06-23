package org.yildirimog.abilet.category.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yildirimog.abilet.category.dto.CategoryRequestDto;
import org.yildirimog.abilet.category.dto.CategoryResponseDto;
import org.yildirimog.abilet.category.dto.CategoryUpdateDto;
import org.yildirimog.abilet.category.entity.Category;
import org.yildirimog.abilet.category.exception.CategoryAlreadyExistsException;
import org.yildirimog.abilet.category.exception.CategoryNotFoundException;
import org.yildirimog.abilet.category.mapper.CategoryMapper;
import org.yildirimog.abilet.category.repository.CategoryRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    void create_whenNameAlreadyExists_throwsAndDoesNotSave() {
        CategoryRequestDto request = new CategoryRequestDto("Concert", "Live music");
        when(categoryRepository.existsByName("Concert")).thenReturn(true);

        assertThatThrownBy(() -> categoryService.create(request))
                .isInstanceOf(CategoryAlreadyExistsException.class);

        verify(categoryRepository, never()).save(any());
    }

    @Test
    void create_whenNew_savesAndReturnsDto() {
        CategoryRequestDto request = new CategoryRequestDto("Concert", "Live music");
        Category entity = Category.builder().name("Concert").description("Live music").build();
        Category saved = Category.builder().id(1L).name("Concert").description("Live music").build();
        CategoryResponseDto expected = new CategoryResponseDto(1L, "Concert", "Live music");

        when(categoryRepository.existsByName("Concert")).thenReturn(false);
        when(categoryMapper.toEntity(request)).thenReturn(entity);
        when(categoryRepository.save(entity)).thenReturn(saved);
        when(categoryMapper.toDto(saved)).thenReturn(expected);

        CategoryResponseDto result = categoryService.create(request);

        assertThat(result).isEqualTo(expected);
        verify(categoryRepository).save(entity);
    }

    @Test
    void getById_whenMissing_throwsNotFound() {
        when(categoryRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> categoryService.getById(99L))
                .isInstanceOf(CategoryNotFoundException.class);
    }

    @Test
    void update_whenRenamingToExistingName_throwsConflict() {
        Category existing = Category.builder().id(1L).name("Concert").description("old").build();
        CategoryUpdateDto update = new CategoryUpdateDto("Theatre", "new");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(categoryRepository.existsByName("Theatre")).thenReturn(true);

        assertThatThrownBy(() -> categoryService.update(1L, update))
                .isInstanceOf(CategoryAlreadyExistsException.class);

        verify(categoryMapper, never()).updateEntity(any(), any());
    }

    @Test
    void delete_softDeletes_bySettingDeletedAt() {
        Category existing = Category.builder().id(1L).name("Concert").description("desc").build();
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(existing));

        categoryService.delete(1L);

        assertThat(existing.getDeletedAt()).isNotNull();
    }
}
