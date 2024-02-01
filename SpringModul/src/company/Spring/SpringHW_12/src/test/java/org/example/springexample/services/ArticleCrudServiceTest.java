package org.example.springexample.services;

import org.example.springexample.dto.ArticleDto;
import org.example.springexample.entity.Article;
import org.example.springexample.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleCrudServiceTest {

    @InjectMocks
    private ArticleCrudService articleCrudService;

    @Mock
    private ArticleRepository articleRepository;

    @Test
    void testGetById_ShouldGetArticleDto_WhenArticleDtoNotEmpty() {
        int articleId = 1;
        Article article = new Article();
        article.setId(articleId);
        article.setComments(List.of());

        when(articleRepository.findById(articleId)).thenReturn(Optional.of(article));

        ArticleDto articleDto = articleCrudService.getById(articleId);

        assertEquals(articleId, articleDto.getId());
        verify(articleRepository, times(1)).findById(articleId);
    }

    @Test
    void testGetAll_ShouldGetListArticleDto_WhenArticleDtoNotEmpty() {
        List<Article> articles = new ArrayList<>();
        Article article = new Article();
        article.setId(1);
        article.setComments(List.of());

        articles.add(article);
        when(articleRepository.findAll()).thenReturn(articles);
        Collection<ArticleDto> articleDtos = articleCrudService.getAll();

        assertEquals(articles.size(), articleDtos.size());
        verify(articleRepository, times(1)).findAll();
    }

    @Test
    void testCreat_ShouldCreatArticleDto_WhenArticleDtoNotEmpty() {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setComments(List.of());
        articleCrudService.create(articleDto);
        verify(articleRepository, times(1)).save(any());
    }

    @Test
    void testUpdate_ShouldUpdateAuthorDto_WhenAuthorDtoNotEmpty() {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setComments(List.of());
        articleCrudService.update(articleDto);
        verify(articleRepository, times(1)).save(any());
    }

    @Test
    void testDeleteById_ShouldDeleteArticleDto_WhenArticleDtoNotEmpty() {
        int authorId = 1;
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(authorId);
        articleDto.setComments(List.of());
        articleCrudService.delete(authorId);
        verify(articleRepository, times(1)).deleteById(authorId);
    }



}