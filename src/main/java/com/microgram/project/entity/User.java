package com.microgram.project.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String username;
    private String email;
    private String password;
    private Integer postQty;
    private Integer subsQty;
    private Integer followersQty;
}
