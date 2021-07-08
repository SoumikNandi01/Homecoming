package com.homecoming.homecoming.validators.activity;
import com.homecoming.homecoming.model.rest.GetLoginUserResponse;
import com.homecoming.homecoming.validators.AppUserDataValidator;
import com.homecoming.homecoming.validators.ErrorValidator;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GetLoginUserValidator {

    private final ErrorValidator errorValidator;
    private final AppUserDataValidator appUserDataValidator;

    public void validateGetLoginUserResponse(GetLoginUserResponse actualResponse,
                                             GetLoginUserResponse expectedResponse) {

        if (Objects.isNull(actualResponse) && Objects.isNull(expectedResponse)) {
            return;
        }
        errorValidator.validateErrorPayload(actualResponse.getError(), expectedResponse.getError());
        appUserDataValidator.validateAppUserData(actualResponse.getAppUserDo(), expectedResponse.getAppUserDo());
        assertEquals(actualResponse.getMessage(), expectedResponse.getMessage());
    }
}
