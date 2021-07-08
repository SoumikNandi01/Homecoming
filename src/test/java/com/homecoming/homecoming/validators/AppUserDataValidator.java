package com.homecoming.homecoming.validators;

import com.homecoming.homecoming.model.AppUserDo;
import com.homecoming.homecoming.model.error.Error;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Component
public class AppUserDataValidator {
    public void validateAppUserData(AppUserDo actualAppUserData, AppUserDo expectedAppUserData) {

        if (Objects.isNull(actualAppUserData) && Objects.isNull(expectedAppUserData)) {
            return;
        }
        assertEquals(actualAppUserData.getUsername(), expectedAppUserData.getUsername());
        assertEquals(actualAppUserData.getLocation(), expectedAppUserData.getLocation());
        assertEquals(actualAppUserData.getName(), expectedAppUserData.getName());
        assertEquals(actualAppUserData.getPassword(), expectedAppUserData.getPassword());
    }
}
