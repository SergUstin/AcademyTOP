package org.example.springexample.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewsDto {
    Long id;
    Long categoryId;
    String title;
    String text;
}
