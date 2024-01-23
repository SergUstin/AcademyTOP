package org.example.springexample.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springexample.dto.AuthorDto;
import org.example.springexample.entity.Author;
import org.example.springexample.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorCrudService implements CRUDService<AuthorDto> {

    private final AuthorRepository repository;

    @Override
    public AuthorDto getById(Integer id) {
        log.info("Get by id {}", id);
        return mapToDto(repository.findById(id).orElseThrow());
    }

    @Override
    public Collection<AuthorDto> getAll() {
        log.info("Get All");
        return repository.findAll()
                .stream()
                .map(AuthorCrudService::mapToDto)
                .toList();
    }

    @Override
    public void create(AuthorDto item) {
        log.info("Create");
        repository.save(mapToEntity(item));
    }

    @Override
    public void update(AuthorDto item) {
        log.info("Update");
        repository.save(mapToEntity(item));
    }

    @Override
    public void delete(Integer id) {
        log.info("Delete by ID: {}", id);
        repository.deleteById(id);
    }

    public static Author mapToEntity(AuthorDto authorDto) {
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        author.setRating(authorDto.getRating());
        author.setComments(authorDto.getComments()
                .stream()
                .map(CommentsCrudService::mapToEntity)
                .toList());
        return author;
    }

    public static AuthorDto mapToDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setFirstName(author.getFirstName());
        authorDto.setLastName(author.getLastName());
        authorDto.setRating(author.getRating());
        authorDto.setComments(author.getComments()
                .stream()
                .map(CommentsCrudService::mapToDto)
                .toList());
        return authorDto;
    }
}
