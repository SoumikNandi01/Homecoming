package com.homecoming.homecoming.model.rest;

import com.homecoming.homecoming.model.AppUserDo;
import com.homecoming.homecoming.model.error.Error;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostRegisterUserResponse {
    private final AppUserDo appUserDo;
    private final Error error;
    private final String message;
}
