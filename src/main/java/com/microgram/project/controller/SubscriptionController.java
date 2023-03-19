package com.microgram.project.controller;

import com.microgram.project.dto.SubscriptionDto;
import com.microgram.project.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/sub/{subscriberId}/{followedId}")
    public void subscribe(@PathVariable Long subscriberId, @PathVariable Long followedId) {
        subService.subscribe(subscriberId, followedId);
    }

    @DeleteMapping("/unsub/{subscriberId}/{followedId}")
    public void unsubscribe(@PathVariable Long subscriberId, @PathVariable Long followedId) {
        subService.unsubscribe(subscriberId, followedId);
    }
}

