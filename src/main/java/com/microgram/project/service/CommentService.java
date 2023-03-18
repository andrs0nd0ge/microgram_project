package com.microgram.project.service;

import com.microgram.project.dao.CommentDao;
import com.microgram.project.dto.CommentDto;
import com.microgram.project.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentDao commentDao;

    public List<CommentDto> getAllComments() {
        List<Comment> comments = commentDao.getAllComments();
        return comments.stream()
                .map(CommentDto::from)
                .collect(Collectors.toList());
    }
}
