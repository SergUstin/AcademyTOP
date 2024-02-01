package org.example.springexample.services;

import org.example.springexample.dto.CommentDto;
import org.example.springexample.entity.Article;
import org.example.springexample.entity.Author;
import org.example.springexample.entity.Comment;
import org.example.springexample.repository.ArticleRepository;
import org.example.springexample.repository.AuthorRepository;
import org.example.springexample.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentsCrudServiceTest {

    @InjectMocks
    private CommentsCrudService commentsCrudService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private ArticleRepository articleRepository;

    @Value("${comment.length.max}")
    private Integer maxLength;


    @Test
    void testGetById_ShouldGetCommentDto_WhenCommentDtoNotEmpty() {
        Comment comment = new Comment();
        comment.setId(1);
        comment.setAuthor(new Author());
        comment.setArticle(new Article());
        comment.setText("text");

        when(commentRepository.findById(1)).thenReturn(Optional.of(comment));

        CommentDto commentDto = commentsCrudService.getById(1);

        assertEquals(comment.getId(), commentDto.getId());
        verify(commentRepository, times(1)).findById(1);
    }

    @Test
    void testGetAll_ShouldGetListCommentDto_WhenCommentDtoNotEmpty() {
        List<Comment> comments = new ArrayList<>();
        Comment comment = new Comment();
        comment.setId(1);
        comment.setAuthor(new Author());
        comment.setArticle(new Article());
        comment.setText("text");

        comments.add(comment);
        when(commentRepository.findAll()).thenReturn(comments);
        Collection<CommentDto> commentDtos = commentsCrudService.getAll();

        assertEquals(comments.size(), commentDtos.size());
        verify(commentRepository, times(1)).findAll();
    }

    @Test
    void testCreat_ShouldCreatCommentDto_WhenCommentDtoNotEmpty() {
        CommentDto commentDto = new CommentDto();
        commentDto.setAuthorId(1);
        commentDto.setArticleId(1);

        Author author = new Author();
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));

        Article article = new Article();
        when(articleRepository.findById(1)).thenReturn(Optional.of(article));

        commentsCrudService.create(commentDto);

        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    void testCreat_ShouldCreatCommentDto_WhenInvalidAuthorId() {
        CommentDto commentDto = new CommentDto();
        commentDto.setAuthorId(100);

        assertThrows(NoSuchElementException.class, () -> commentsCrudService.create(commentDto));
        verify(authorRepository, times(1)).findById(100);
        verifyNoInteractions(articleRepository, commentRepository);
    }

    @Test
    void testCreat_ShouldCreatCommentDto_WhenInvalidArticleId() {
        CommentDto commentDto = new CommentDto();
        commentDto.setAuthorId(1);
        commentDto.setArticleId(100);

        Author author = new Author();
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));

        assertThrows(NoSuchElementException.class, () -> commentsCrudService.create(commentDto));
        verify(authorRepository, times(1)).findById(1);
        verify(articleRepository, times(1)).findById(100);
        verifyNoMoreInteractions(commentRepository);
    }

    @Test
    public void testUpdate_ShouldUpdateCommentDto_WhenValidData() {
        // Arrange
        CommentDto commentDto = new CommentDto();
        commentDto.setText("Good comment");
        commentDto.setAuthorId(1);
        commentDto.setArticleId(2);

        Author author = new Author();
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));

        Article article = new Article();
        when(articleRepository.findById(2)).thenReturn(Optional.of(article));

        ReflectionTestUtils.setField(commentsCrudService, "maxLength", 100); // Set a mock value for maxLength

        // Act
        commentsCrudService.update(commentDto);

        // Assert
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    public void testUpdate_ShouldUpdateCommentDto_WhenInvalidData() {
        // Arrange
        CommentDto commentDto = new CommentDto();
        commentDto.setText("This is an extremely long comment that exceeds the maximum allowed length");
        commentDto.setAuthorId(1);
        commentDto.setArticleId(2);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> commentsCrudService.update(commentDto));
    }

    @Test
    void testDeleteById_ShouldDeleteCommentDto_WhenCommentDtoNotEmpty() {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(1);
        commentsCrudService.delete(1);
        verify(commentRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteById_ShouldDeleteCommentDto_WhenCommentDtoEmptyId() {
        Integer id = null;

        commentsCrudService.delete(id);

        verify(commentRepository, never()).deleteById(anyInt());
    }




}