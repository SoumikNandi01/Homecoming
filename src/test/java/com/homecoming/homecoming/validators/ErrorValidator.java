package com.homecoming.homecoming.validators;

import com.homecoming.homecoming.model.error.Error;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Component
public class ErrorValidator {
    public void validateErrorPayload(Error actualError, Error expectedError) {

        if (Objects.isNull(actualError) && Objects.isNull(expectedError)) {
            return;
        }
        assertEquals(actualError.getErrorCode(), expectedError.getErrorCode());
        assertEquals(actualError.getErrorMessage(), actualError.getErrorMessage());
    }
}
