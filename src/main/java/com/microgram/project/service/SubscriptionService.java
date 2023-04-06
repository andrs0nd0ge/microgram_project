package com.microgram.project.service;

import com.microgram.project.dao.SubscriptionDao;
import com.microgram.project.dto.SubscriptionDto;
import com.microgram.project.entity.Subscription;
import com.microgram.project.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionDao subsDao;
    public List<SubscriptionDto> getSubscriptions() {
        List<Subscription> subs = subsDao.getAllSubscriptions();
        return subs.stream()
                .map(SubscriptionDto::from)
                .collect(Collectors.toList());
    }

    public void subscribe(Authentication auth, Long subscribedToId) {
        User subscriber = (User) auth.getPrincipal();
        subsDao.subscribe(subscriber.getId(), subscribedToId);
    }

    public void unsubscribe(Authentication auth, Long subscribedToId) {
        User subscriber = (User) auth.getPrincipal();
        subsDao.unsubscribe(subscriber.getId(), subscribedToId);
    }
}
