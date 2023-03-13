package com.microgram.project.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Comment {
    private Long id;
    private String text;
    private LocalDateTime date;
}
