package com.microgram.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microgram.project.entity.Subscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDto {
    public static SubscriptionDto from(Subscription sub) {
        return builder()
                .id(sub.getId())
                .subscriberId(sub.getSubscriberId())
                .subscribedToId(sub.getSubscribedToId())
                .build();
    }

    public Long id;
    @JsonProperty("subscriber_id")
    public Long subscriberId;
    @JsonProperty("subscribed_to_id")
    public Long subscribedToId;
}
