package com.microgram.project.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Post {
    private Long id;
    private byte[] image;
    private String description;
    private LocalDateTime date;
    private Long userId;
}
