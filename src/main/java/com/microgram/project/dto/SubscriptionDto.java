package com.microgram.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microgram.project.entity.Subscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDto {
    public static SubscriptionDto from(Subscription sub) {
        return builder()
                .subscriberId(sub.getSubscriberId())
                .subscribedToId(sub.getSubscribedToId())
                .date(sub.getDate())
                .build();
    }
    @JsonProperty("subscriber_id")
    public Long subscriberId;
    @JsonProperty("subscribed_to_id")
    public Long subscribedToId;
    public LocalDate date;
}
