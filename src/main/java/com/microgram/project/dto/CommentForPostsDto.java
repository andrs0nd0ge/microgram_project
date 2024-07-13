package com.microgram.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentForPostsDto {
    @JsonProperty("post_id")
    private long postId;
    @JsonProperty("user_id")
    private long userId;

    private String comment; // Only used for comment creation

    @JsonProperty("comment_id")
    private Long commentId; // Only used for comment deletion
}
