package com.company.controller;

import com.company.model.News;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewsControllerTest {

    private NewsController newsController;
    private List<News> newsList;

    @BeforeEach
    void setUp() {
        newsController = new NewsController();
        newsList = new ArrayList<>();
    }

    @Test
    void testGetNewsById() {
        ResponseEntity<News> response = newsController.getNewsById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void testGetNewsByIdNotFound() {
        ResponseEntity<News> response = newsController.getNewsById(10L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertFalse(response.hasBody());
    }

    @Test
    void testGetAllNews() {
        List<News> newsList = newsController.getAllNews();

        assertEquals(5, newsList.size());
    }

    @Test
    void testCreateOrUpdateNewNews() {
        News news = new News();
        news.setTitle("Test News");
        news.setText("This is a test news");

        ResponseEntity<News> response = newsController.createOrUpdate(news);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        News createdNews = response.getBody();
        assertNotNull(createdNews.getId());
        assertEquals(news.getTitle(), createdNews.getTitle());
        assertEquals(news.getText(), createdNews.getText());
    }

    @Test
    void testCreateOrUpdateExistingNews() {
        // Create a news
        News news = new News();
        news.setTitle("Test News");
        news.setText("This is a test news");
        ResponseEntity<News> createResponse = newsController.createOrUpdate(news);
        News createdNews = createResponse.getBody();

        // Update the news
        createdNews.setTitle("Updated Test News");
        createdNews.setText("This is an updated test news");
        ResponseEntity<News> updateResponse = newsController.createOrUpdate(createdNews);
        News updatedNews = updateResponse.getBody();

        assertEquals(createdNews.getId(), updatedNews.getId());
        assertEquals(createdNews.getTitle(), updatedNews.getTitle());
        assertEquals(createdNews.getText(), updatedNews.getText());
    }

    @Test
    void testCreateOrUpdateInvalidId() {
        // Create a news with invalid id
        News news = new News();
        news.setId(-1L);
        news.setTitle("Test News");
        news.setText("This is a test news");
        ResponseEntity<News> response = newsController.createOrUpdate(news);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        News createdNews = response.getBody();
        assertNotNull(createdNews.getId());
        assertTrue(createdNews.getId() > 0);
        assertEquals(news.getTitle(), createdNews.getTitle());
        assertEquals(news.getText(), createdNews.getText());
    }

    @Test
    void testDeleteExistingNews() {
        // Создаем новость
        News news = new News();
        news.setId(1L);
        news.setTitle("Test News");
        news.setText("This is a test news");
        newsList.add(news);

        // Удаляем новость
        boolean result = newsController.deleteById(1L).hasBody();

        // Проверяем, что новость не была успешно удалена
        assertFalse(result);
    }

    @Test
    void testDeleteNonExistingNews() throws Exception {
        // Создаем новый экземпляр контроллера
        NewsController newsController = new NewsController();

        // Создаем список новостей
        newsList.add(new News(1L, "Заголовок 1", "Содержание 1"));
        newsList.add(new News(2L, "Заголовок 2", "Содержание 2"));

        // Используем рефлексию для установки значения приватного поля newsList
        Field field = NewsController.class.getDeclaredField("newsList");
        field.setAccessible(true);
        field.set(newsController, newsList);

        // Удаляем несуществующую новость по ID
        ResponseEntity<?> response = newsController.deleteById(3L);

        // Проверяем статус код ответа
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        // Проверяем сообщение об ошибке
        String errorMessage = (String) response.getBody();
        assertEquals("Новость с ID 3 не найдена.", errorMessage);
    }
}