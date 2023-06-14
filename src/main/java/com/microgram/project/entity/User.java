package com.microgram.project.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private String usernae;
    private String email;
    private String password;
    @JsonProperty("post_qty")
    private Integer postQty;
    @JsonProperty("subs_qty")
    private Integer subsQty;
    @JsonProperty("followers_qty")
    private Integer followersQty;
}
