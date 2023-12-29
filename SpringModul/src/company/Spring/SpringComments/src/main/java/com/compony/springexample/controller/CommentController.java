package com.compony.springexample.controller;

import com.compony.springexample.dto.CommentDto;
import com.compony.springexample.services.CommentsCrudServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentsCrudServiceImpl commentsService;

    public CommentController(CommentsCrudServiceImpl commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping("/{id}")
    public CommentDto getCommentById(@PathVariable Integer id) {
        return commentsService.getById(id);
    }

    @GetMapping
    public Collection<CommentDto> getAllComments() {
        return commentsService.getAll();
    }

    @PostMapping
    public void createComment(@RequestBody CommentDto commentDto) {
        commentsService.creat(commentDto);
    }

    @PutMapping("/{id}")
    public void updateComment(@PathVariable Integer id, @RequestBody CommentDto commentDto) {
        commentsService.update(id, commentDto);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Integer id) {
        commentsService.delete(id);
    }
}
