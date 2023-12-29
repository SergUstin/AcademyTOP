package com.compony.springexample.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CommentDto {
    private Integer id;
    private String text;
    private String author;
}
