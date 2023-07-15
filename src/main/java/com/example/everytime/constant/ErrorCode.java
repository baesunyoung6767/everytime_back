package com.example.everytime.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DUPLICATED_USER_ID(HttpStatus.CONFLICT, "user id is duplicated"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "not found error"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "bad Request");

    private HttpStatus httpStatus;
    private String message;
}
