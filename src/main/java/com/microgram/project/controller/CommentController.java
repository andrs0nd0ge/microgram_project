package com.microgram.project.controller;

import com.microgram.project.dto.CommentDto;
import com.microgram.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @GetMapping
    public List<CommentDto> getAllComments() {
        return commentService.getAllComments();
    }
}
