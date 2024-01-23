package org.example.springexample.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springexample.dto.CommentDto;
import org.example.springexample.entity.Article;
import org.example.springexample.entity.Author;
import org.example.springexample.entity.Comment;
import org.example.springexample.repository.ArticleRepository;
import org.example.springexample.repository.AuthorRepository;
import org.example.springexample.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommentsCrudService implements CRUDService<CommentDto> {

    @Value("${comment.length.max}")
    private Integer maxLength;

    private final CommentRepository commentRepository;
    private final AuthorRepository authorRepository;
    private final ArticleRepository articleRepository;
    
    @Override
    public CommentDto getById(Integer id) {
        log.info("Get by id {}", id);
        Comment comment = commentRepository.findById(id).orElseThrow();
        return mapToDto(comment);
    }

    @Override
    public Collection<CommentDto> getAll() {
        log.info("Get all");
        return commentRepository.findAll()
                .stream()
                .map(CommentsCrudService::mapToDto)
                .toList();
    }

    @Override
    public void create(CommentDto commentDto) {
        log.info("Create");
        Comment comment = mapToEntity(commentDto);

        Integer authorId = commentDto.getAuthorId();
        Author author = authorRepository.findById(authorId).orElseThrow();
        comment.setAuthor(author);

        Integer articleId = commentDto.getArticleId();
        Article article = articleRepository.findById(articleId).orElseThrow();
        comment.setArticle(article);

        commentRepository.save(comment);
    }

    @Override
    public void update(CommentDto commentDto) {
        log.info("Update");
        if (commentDto.getText().length() > maxLength) {
            throw new RuntimeException("Comment is too long");
        }
        Comment comment = mapToEntity(commentDto);

        Integer authorId = commentDto.getAuthorId();
        Author author = authorRepository.findById(authorId).orElseThrow();
        comment.setAuthor(author);

        Integer articleId = commentDto.getArticleId();
        Article article = articleRepository.findById(articleId).orElseThrow();
        comment.setArticle(article);

        commentRepository.save(comment);
    }

    @Override
    public void delete(Integer id) {
        log.info("Delete by ID: {}", id);
        commentRepository.deleteById(id);
    }

    public static CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setText(comment.getText());
        commentDto.setAuthorId(comment.getAuthor().getId());
        commentDto.setArticleId(comment.getArticle().getId());
        return commentDto;
    }

    public static Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setText(commentDto.getText());
        return comment;
    }
}
