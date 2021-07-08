package com.homecoming.homecoming.model.error;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Error {
    private final ErrorCodes errorCode;
    private final String errorMessage;
}
