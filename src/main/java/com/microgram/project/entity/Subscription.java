package com.microgram.project.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Subscription {
    private User subscriber;
    private User subscribedTo;
    private LocalDate date;
}
