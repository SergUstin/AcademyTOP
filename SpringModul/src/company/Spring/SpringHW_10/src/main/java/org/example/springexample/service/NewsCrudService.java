package org.example.springexample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springexample.dto.NewsDto;
import org.example.springexample.models.Category;
import org.example.springexample.models.News;
import org.example.springexample.repository.CategoryRepository;
import org.example.springexample.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsCrudService implements CRUDService<NewsDto> {

    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public NewsDto getById(Long id) {
        log.info("Get by ID {}", id);
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("News not found!"));
        return mapToDto(news);
    }

    @Override
    public Collection<NewsDto> getAll() {
        log.info("Get All");
        return newsRepository.findAll()
                .stream()
                .map(NewsCrudService::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public NewsDto create(NewsDto item) {
        log.info("Create");
        News news = mapToEntity(item);

        Long categoryId = item.getCategoryId();
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        news.setCategory(category);

        newsRepository.save(news);
        return mapToDto(news);
    }

    @Override
    public NewsDto update(NewsDto item) {
        log.info("Update");
        News news = mapToEntity(item);

        Long categoryId = item.getCategoryId();
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        news.setCategory(category);

        newsRepository.save(news);
        return mapToDto(news);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Delete by ID {}", id);
        newsRepository.deleteById(id);
    }

    public static NewsDto mapToDto(News news) {
        NewsDto newsDto = new NewsDto();
        newsDto.setId(news.getId());
        newsDto.setTitle(news.getTitle());
        newsDto.setText(news.getText());
        newsDto.setCategoryId(news.getCategory().getId());
        return newsDto;
    }

    public static News mapToEntity(NewsDto newsDto) {
        News news = new News();
        news.setId(newsDto.getId());
        news.setTitle(newsDto.getTitle());
        news.setText(newsDto.getText());
        return news;
    }
}
