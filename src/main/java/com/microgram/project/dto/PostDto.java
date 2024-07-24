package com.microgram.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class PostDto {
    private String description;
    private Long id;
    @JsonProperty("image_name")
    private String imageName;
    private LocalDate date;
    private LocalTime time;
    private User user;

    public static PostDto from(Post post) {
        return builder()
                .id(post.getId())
                .imageName(post.getImageName())
                .description(post.getDescription())
                .date(post.getDate() != null ? post.getDate().toLocalDate() : null)
                .time(post.getDate() != null ? post.getDate().toLocalTime() : null)
                .user(post.getUser())
                .build();
    }
}
