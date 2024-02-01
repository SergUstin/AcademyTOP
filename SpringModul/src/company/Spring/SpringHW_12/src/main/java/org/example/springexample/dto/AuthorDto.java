package org.example.springexample.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorDto {
    Integer id;
    String firstName;
    String lastName;
    Long rating;
    List<CommentDto> comments;
}
