package org.yildirimog.abilet.category.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yildirimog.abilet.category.dto.CategoryRequestDto;
import org.yildirimog.abilet.category.dto.CategoryResponseDto;
import org.yildirimog.abilet.category.dto.CategoryUpdateDto;
import org.yildirimog.abilet.category.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDto>  create(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {

       CategoryResponseDto responseDto = categoryService.create(categoryRequestDto);
       return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getById(@PathVariable Long id){
        CategoryResponseDto responseDto = categoryService.getById(id);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> update(@PathVariable Long id, @Valid @RequestBody CategoryUpdateDto categoryUpdateDto) {
        CategoryResponseDto responseDto = categoryService.update(id, categoryUpdateDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
