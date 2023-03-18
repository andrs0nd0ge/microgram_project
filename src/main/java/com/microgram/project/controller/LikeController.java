package com.microgram.project.controller;

import com.microgram.project.dto.LikeDto;
import com.microgram.project.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    @GetMapping
    public List<LikeDto> getLikes() {
        return likeService.getAllLikes();
    }
}
