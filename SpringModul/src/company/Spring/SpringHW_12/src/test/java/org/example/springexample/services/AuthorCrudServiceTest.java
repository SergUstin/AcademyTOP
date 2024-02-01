package org.example.springexample.services;

import org.example.springexample.dto.AuthorDto;
import org.example.springexample.entity.Author;
import org.example.springexample.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorCrudServiceTest {

    private final AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);

    private final AuthorCrudService authorCrudService = new AuthorCrudService(authorRepository);

    @Test
    void testGetById_ShouldGetAuthorDto_WhenAuthorDtoNotEmpty() {
        int authorId = 1;
        Author author = new Author();
        author.setId(authorId);
        author.setComments(List.of());

        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));

        AuthorDto authorDto = authorCrudService.getById(authorId);

        assertEquals(authorId, authorDto.getId());
        verify(authorRepository, times(1)).findById(authorId);
    }

    @Test
    void testGetAll_ShouldGetListAuthorDto_WhenAuthorDtoNotEmpty() {
        List<Author> authors = new ArrayList<>();
        Author author = new Author();
        author.setId(1);
        author.setComments(List.of());

        authors.add(author);
        when(authorRepository.findAll()).thenReturn(authors);
        Collection<AuthorDto> authorDtos = authorCrudService.getAll();

        assertEquals(authors.size(), authorDtos.size());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void testCreat_ShouldCreatAuthorDto_WhenAuthorDtoNotEmpty() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setComments(List.of());
        authorCrudService.create(authorDto);
        verify(authorRepository, times(1)).save(any());
    }

    @Test
    void testUpdate_ShouldUpdateAuthorDto_WhenAuthorDtoNotEmpty() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setComments(List.of());
        authorCrudService.update(authorDto);
        verify(authorRepository, times(1)).save(any());
    }

    @Test
    void testDeleteById_ShouldDeleteAuthorDto_WhenAuthorDtoNotEmpty() {
        int authorId = 1;
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(authorId);
        authorDto.setComments(List.of());
        authorCrudService.delete(authorId);
        verify(authorRepository, times(1)).deleteById(authorId);
    }

}