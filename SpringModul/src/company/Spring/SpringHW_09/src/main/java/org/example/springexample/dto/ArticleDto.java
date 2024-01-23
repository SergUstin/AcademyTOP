package org.example.springexample.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleDto {
    Integer id;
    String title;
    String content;
    List<CommentDto> comments;
}
