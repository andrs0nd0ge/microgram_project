package com.microgram.project.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Subscription {
    private Long id;
    private Long subscriberId;
    private Long subscribedToId;
    private LocalDate date;
}
