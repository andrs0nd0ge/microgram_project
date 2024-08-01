package com.microgram.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDto {
    private String name;
    private String username;
    private String email;
    private String password;
}
