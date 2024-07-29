package com.microgram.project.dto;

import com.microgram.project.entity.Comment;
import com.microgram.project.entity.Post;
import com.microgram.project.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String text;
    private LocalDate date;
    private LocalTime time;
    private Post post;
    private User user;

    public static CommentDto from(Comment comment) {
        return builder()
                .id(comment.getId())
                .text(comment.getText())
                .date(comment.getDate() != null ? comment.getDate().toLocalDate() : null)
                .time(comment.getDate() != null ? comment.getDate().toLocalTime() : null)
                .post(comment.getPost())
                .user(comment.getUser())
                .build();
    }
}
