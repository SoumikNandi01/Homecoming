package com.homecoming.homecoming.model.error;
import lombok.Getter;

@Getter
public enum ErrorCodes {
    USER_NOT_FOUND("no user found with the credentials"),
    USER_EXISTS("user already exists! Please login");

    private final String errorMsg;
    ErrorCodes(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
