package com.microgram.project.controller;

import com.microgram.project.dto.CommentDto;
import com.microgram.project.service.CommentService;
import com.microgram.project.util.UtilityClass;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final UtilityClass util;

    @GetMapping
    public List<CommentDto> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/create")
    public ResponseEntity<String> createCommentsTable() {
        return new ResponseEntity<>(util.createCommentsTable(), HttpStatus.OK);
    }

    @GetMapping("/insert")
    public ResponseEntity<String> insertIntoCommentsTable() {
        return new ResponseEntity<>(util.insertIntoComments(), HttpStatus.OK);
    }
}
