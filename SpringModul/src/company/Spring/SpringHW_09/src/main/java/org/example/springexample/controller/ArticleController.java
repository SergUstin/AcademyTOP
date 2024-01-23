package org.example.springexample.controller;

import lombok.RequiredArgsConstructor;
import org.example.springexample.dto.ArticleDto;
import org.example.springexample.services.ArticleCrudService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleCrudService articleService;

    @GetMapping
    public Collection<ArticleDto> getAll() {
        return articleService.getAll();
    }

    @PostMapping
    public void create(@RequestBody ArticleDto articleDto) {
        articleService.create(articleDto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody ArticleDto articleDto) {
        articleDto.setId(id);
        articleService.update(articleDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        articleService.delete(id);
    }


}
