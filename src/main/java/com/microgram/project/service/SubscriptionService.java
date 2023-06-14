package com.microgram.project.service;

import com.microgram.project.dao.SubscriptionDao;
import com.microgram.project.dto.SubscriptionDto;
import com.microgram.project.entity.Subscription;
import lombok.RequiredArgsConstructor;
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

    public void subscribe(Long subscriberId, Long subscribedToId) {
        subsDao.subscribe(subscriberId, subscribedToId);
    }

    public void unsubscribe(Long subscriberId, Long subscribedToId) {
        subsDao.unsubscribe(subscriberId, subscribedToId);
    }
}
