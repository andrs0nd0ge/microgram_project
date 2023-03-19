package com.microgram.project.service;

import com.microgram.project.dao.LikeDao;
import com.microgram.project.dto.LikeDto;
import com.microgram.project.entity.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeDao likeDao;

    public List<LikeDto> getAllLikes() {
        List<Like> likes = likeDao.getAllLikes();
        return likes.stream()
                .map(LikeDto::from)
                .collect(Collectors.toList());
    }

    public String checkPostForLikes(Long postId) {
        Like like = likeDao.checkPostForLikes(postId);
        if (like != null) {
            return "Given post has at least one like";
        } else {
            return "Given post doesn't have any likes";
        }
    }
}
