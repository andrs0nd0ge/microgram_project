package com.microgram.project.controller;

import com.microgram.project.dto.LikeDto;
import com.microgram.project.service.LikeService;
import com.microgram.project.util.UtilityClass;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    private final UtilityClass util;

    @GetMapping
    public List<LikeDto> getLikes() {
        return likeService.getAllLikes();
    }

    @GetMapping("/create")
    public ResponseEntity<String> createLikesTable() {
        return new ResponseEntity<>(util.createLikesTable(), HttpStatus.OK);
    }

    @GetMapping("/insert")
    public ResponseEntity<String> insertIntoLikesTable() {
        return new ResponseEntity<>(util.insertIntoLikes(), HttpStatus.OK);
    }
}
