package com.homecoming.homecoming.model.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Error {
    private ErrorCodes errorCode;
    private String errorMessage;
}
