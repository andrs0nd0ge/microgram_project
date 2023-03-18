package com.microgram.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microgram.project.entity.Like;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeDto {
    public static LikeDto from(Like like) {
        return builder()
                .id(like.getId())
                .userId(like.getUserId())
                .postId(like.getPostId())
                .date(like.getDate())
                .build();
    }

    private Long id;
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("post_id")
    private Long postId;
    private LocalDateTime date;
}
