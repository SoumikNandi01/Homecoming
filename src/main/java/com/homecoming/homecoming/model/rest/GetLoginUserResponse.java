package com.homecoming.homecoming.model.rest;

import com.homecoming.homecoming.model.error.Error;
import com.homecoming.homecoming.model.AppUserDo;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetLoginUserResponse {
    private final AppUserDo appUserDo;
    private final Error error;
    private final String message;
}
