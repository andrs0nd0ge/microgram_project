package com.microgram.project.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Post {
    private String image;
    private String desc;
    private LocalDateTime date;
}
