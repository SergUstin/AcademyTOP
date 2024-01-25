package org.example.springexample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springexample.dto.CategoryDto;
import org.example.springexample.models.Category;
import org.example.springexample.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryCrudService implements CRUDService<CategoryDto> {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto getById(Long id) {
        log.info("Get by ID {}", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("News not found!"));
        return mapToDto(category);
    }

    @Override
    public Collection<CategoryDto> getAll() {
        log.info("Get All");
        return categoryRepository.findAll()
                .stream()
                .map(CategoryCrudService::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto create(CategoryDto item) {
        log.info("Create");
        Category category = mapToEntity(item);
        categoryRepository.save(category);
        return mapToDto(category);
    }

    @Override
    public CategoryDto update(CategoryDto item) {
        log.info("Update");
        Category category = mapToEntity(item);
        categoryRepository.save(category);
        return mapToDto(category);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Delete by ID {}", id);
        categoryRepository.deleteById(id);
    }

    public static CategoryDto mapToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setNews(category.getNewsList()
                .stream()
                .map(NewsCrudService::mapToDto)
                .collect(Collectors.toList()));
        return categoryDto;
    }

    public static Category mapToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setTitle(categoryDto.getTitle());
        category.setNewsList(categoryDto.getNews()
                .stream()
                .map(NewsCrudService::mapToEntity)
                .collect(Collectors.toList()));
        return category;
    }


}
