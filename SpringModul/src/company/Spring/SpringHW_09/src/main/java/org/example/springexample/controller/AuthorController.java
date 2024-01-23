package org.example.springexample.controller;

import lombok.RequiredArgsConstructor;
import org.example.springexample.dto.AuthorDto;
import org.example.springexample.services.AuthorCrudService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorCrudService authorCrudService;

    @GetMapping
    public Collection<AuthorDto> getAll() {
        return authorCrudService.getAll();
    }

    @PostMapping
    public void create(@RequestBody AuthorDto authorDto) {
        authorCrudService.create(authorDto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody AuthorDto authorDto) {
        authorDto.setId(id);
        authorCrudService.update(authorDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        authorCrudService.delete(id);
    }
}
