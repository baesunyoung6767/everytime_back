package com.example.everytime.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DUPLICATED_USER_ID(HttpStatus.CONFLICT, "user id is duplicated"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "user not found error"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "bad Request"),
    FREE_NOT_FOUND(HttpStatus.NOT_FOUND, "free post not found error"),
    PR_NOT_FOUND(HttpStatus.NOT_FOUND, "pr post not found error"),

    USER_NOT_MATCH(HttpStatus.BAD_REQUEST, "user does not match");

    private HttpStatus httpStatus;
    private String message;
}
