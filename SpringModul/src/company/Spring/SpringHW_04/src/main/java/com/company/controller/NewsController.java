package com.company.controller;

import com.company.model.News;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/news")
public class NewsController {
    private List<News> newsList;

    public NewsController() {
        newsList = new ArrayList<>();
        newsList.add(new News(1L,
                "Ученые открыли новый лекарственный препарат",
                "Открыт новый лекарственный препарат: новости из медицины!"));
        newsList.add(new News(2L,
                "Эксперты предсказывают рост экономики",
                "Эксперты предсказывают рост экономики: важные прогнозы"));
        newsList.add(new News(3L,
                "В Москве открылся новый парк",
                "Уникальный парк открылся в Москве: приглашаем посетить!"));
        newsList.add(new News(4L,
                "Спортсмен побил мировой рекорд",
                "Спортсмен установил мировой рекорд: эпичное достижение"));
        newsList.add(new News(5L,
                "Исследователи находят лечение рака",
                "Исследователи нашли лечение рака: надежда для пациентов"));
    }

    // Получение новости по id
    @GetMapping(path = "/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable Long id) {

        return newsList.stream()
                .filter(news -> news.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Получение списка всех новостей
    @GetMapping
    public List<News> getAllNews() {
        return newsList;
    }

    // Добавление новой новости или изменение существующей
    @PutMapping
    public ResponseEntity<News> createOrUpdate(@RequestBody News news) {
        String title = news.getTitle();
        String text = news.getText();

        Long id = news.getId();

        if (id == null || id <= 0) {
            // Генерируем новый идентификатор, если не указан или является неположительным числом
            id = (long) (newsList.size() + 1);
            news.setId(id);
            newsList.add(news);
            return ResponseEntity.status(HttpStatus.CREATED).body(news);
        } else {
            // Проверяем, есть ли новость с указанным идентификатором в списке новостей
            for (News existingNews : newsList) {
                if (existingNews.getId().equals(id)) {
                    // Если найдена существующая новость, обновляем ее поля
                    existingNews.setTitle(title);
                    existingNews.setText(text);
                    return ResponseEntity.ok(existingNews);
                }
            }

            // Создаем новую новость с указанным идентификатором, если не найдена существующая новость
            newsList.add(news);
            return ResponseEntity.status(HttpStatus.CREATED).body(news);
        }
    }

    // Удаление новости
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        // Поиск новости с указанным идентификатором
        Optional<News> newsOptional = newsList.stream()
                .filter(news -> news.getId().equals(id))
                .findFirst();

        if (newsOptional.isPresent()) {
            // Новость найдена, удаляем ее
            newsList.remove(newsOptional.get());
            return ResponseEntity.noContent().build();
        } else {
            // Новость не найдена, возвращаем код HTTP 404 (Not Found) и сообщение об ошибке
            String errorMessage = "Новость с ID " + id + " не найдена.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }
}
