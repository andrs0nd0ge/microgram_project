package com.microgram.project.controller;

import com.microgram.project.dto.SubscriptionDto;
import com.microgram.project.service.SubscriptionService;
import com.microgram.project.util.UtilityClass;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subs")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subService;
    private final UtilityClass util;

    @GetMapping
    public List<SubscriptionDto> getSubscriptions() {
        return subService.getSubscriptions();
    }

    @GetMapping("/create")
    public ResponseEntity<String> createSubsTable() {
        return new ResponseEntity<>(util.createSubscriptionsTable(), HttpStatus.OK);
    }

    @GetMapping("/insert")
    public ResponseEntity<String> insertIntoSubsTable() {
        return new ResponseEntity<>(util.insertIntoSubs(), HttpStatus.OK);
    }
}
