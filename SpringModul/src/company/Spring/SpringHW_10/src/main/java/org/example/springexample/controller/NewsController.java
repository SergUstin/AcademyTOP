package org.example.springexample.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springexample.dto.NewsDto;
import org.example.springexample.service.NewsCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsCrudService newsService;

    @GetMapping("/{id}")
    public ResponseEntity<NewsDto> getById(@PathVariable Long id) {
        try {
            log.info("GET /api/news/{}", id);
            NewsDto newsDto = newsService.getById(id);
            return ResponseEntity.ok(newsDto);
        } catch (IllegalArgumentException e) {
            log.error("Error while getting news with id {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<Collection<NewsDto>> getAll() {
        try {
            log.info("GET /api/news");
            Collection<NewsDto> newsDto = newsService.getAll();
            return ResponseEntity.ok(newsDto);
        } catch (Exception e) {
            log.error("Error while getting all news", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<NewsDto> create(@RequestBody NewsDto newsDto) {
        try {
            log.info("POST /api/news: {}", newsDto);
            newsService.create(newsDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newsDto);
        } catch (Exception e) {
            log.error("Error while creating news: {}", newsDto, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<NewsDto> update(@RequestBody NewsDto newsDto) {
        try {
            log.info("PUT /api/news: {}", newsDto);
            newsService.update(newsDto);
            return ResponseEntity.status(HttpStatus.OK).body(newsDto);
        } catch (Exception e) {
            log.error("Error while updating news: {}", newsDto, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NewsDto> deleteById(@PathVariable Long id) {
        try {
            log.info("DELETE /api/news/{}", id);
            newsService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            log.error("Error while deleting news with id {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
