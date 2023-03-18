package com.microgram.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microgram.project.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    public static CommentDto from(Comment comment) {
        return builder()
                .id(comment.getId())
                .text(comment.getText())
                .date(comment.getDate())
                .postId(comment.getPostId())
                .build();
    }

    private Long id;
    private String text;
    private LocalDateTime date;
    @JsonProperty("post_id")
    private Long postId;
}
