package com.example.everytime.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ROLE {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String value;
}
