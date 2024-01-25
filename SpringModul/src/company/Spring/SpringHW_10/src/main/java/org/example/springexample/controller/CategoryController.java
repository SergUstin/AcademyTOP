package org.example.springexample.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springexample.dto.CategoryDto;
import org.example.springexample.service.CategoryCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryCrudService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable Long id) {
        try {
            log.info("GET /api/category/{}", id);
            CategoryDto categoryDto = categoryService.getById(id);
            return ResponseEntity.ok(categoryDto);
        } catch (IllegalArgumentException e) {
            log.error("Error while getting category with id {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<Collection<CategoryDto>> getAll() {
        try {
            log.info("GET /api/category");
            Collection<CategoryDto> categoryDto = categoryService.getAll();
            return ResponseEntity.ok(categoryDto);
        } catch (Exception e) {
            log.error("Error while getting all category", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryDto) {
        try {
            log.info("POST /api/category: {}", categoryDto);
            categoryService.create(categoryDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryDto);
        } catch (Exception e) {
            log.error("Error while creating category: {}", categoryDto, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<CategoryDto> update(@RequestBody CategoryDto categoryDto) {
        try {
            log.info("PUT /api/category: {}", categoryDto);
            categoryService.update(categoryDto);
            return ResponseEntity.status(HttpStatus.OK).body(categoryDto);
        } catch (Exception e) {
            log.error("Error while updating category: {}", categoryDto, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDto> deleteById(@PathVariable Long id) {
        try {
            log.info("DELETE /api/category/{}", id);
            categoryService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            log.error("Error while deleting category with id {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
