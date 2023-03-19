package com.microgram.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microgram.project.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    public static PostDto from(Post post) {
        return builder()
                .id(post.getId())
                .image(post.getImage())
                .desc(post.getDescription())
                .date(post.getDate())
                .userId(post.getUserId())
                .build();
    }

    private Long id;
    private byte[] image;
    private String desc;
    private LocalDateTime date;
    @JsonProperty("user_id")
    private Long userId;
}
