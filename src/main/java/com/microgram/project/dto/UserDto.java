package com.microgram.project.dto;

import com.microgram.project.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    public static UserDto from(User user) {
        return builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .postQty(user.getPostQty())
                .subsQty(user.getSubsQty())
                .followersQty(user.getFollowersQty())
                .build();
    }

    private Long id;
    private String name;
    private String username;
    private String email;
    private Integer postQty;
    private Integer subsQty;
    private Integer followersQty;
}
