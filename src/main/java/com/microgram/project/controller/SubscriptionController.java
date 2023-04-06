package com.microgram.project.controller;

import com.microgram.project.dto.SubscriptionDto;
import com.microgram.project.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subs")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subService;

    @GetMapping
    public List<SubscriptionDto> getSubscriptions() {
        return subService.getSubscriptions();
    }

    @PostMapping("/sub/{followedId}")
    public void subscribe(Authentication auth, @PathVariable Long followedId) {
        subService.subscribe(auth, followedId);
    }

    @DeleteMapping("/unsub/{followedId}")
    public void unsubscribe(Authentication auth, @PathVariable Long followedId) {
        subService.unsubscribe(auth, followedId);
    }
}

