package com.microgram.project.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Like {
    private Long id;
    private User user;
    private Post post;
    private LocalDateTime date;
}
