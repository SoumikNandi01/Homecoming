package com.homecoming.homecoming.model.rest;

import com.homecoming.homecoming.model.error.Error;
import com.homecoming.homecoming.model.AppUserDo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetLoginUserResponse {
    private AppUserDo appUserDo;
    private Error error;
    private String message;
}
