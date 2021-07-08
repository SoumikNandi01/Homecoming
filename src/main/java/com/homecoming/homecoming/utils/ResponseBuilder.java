package com.homecoming.homecoming.utils;

import com.homecoming.homecoming.model.AppUserDo;
import org.apache.commons.lang3.Validate;
import org.bson.Document;
import org.springframework.stereotype.Component;

import static com.homecoming.homecoming.constants.Constants.AppUserFieldConstants.USERNAME;
import static com.homecoming.homecoming.constants.Constants.AppUserFieldConstants.LOCATION;
import static com.homecoming.homecoming.constants.Constants.AppUserFieldConstants.NAME;

@Component
public class ResponseBuilder {
    public AppUserDo getAppUserFromDocument(Document document) {
        Validate.notNull(document);
        return AppUserDo.builder()
                .username(document.getString(USERNAME))
                .location(document.getString(LOCATION))
                .name(document.getString(NAME))
                .build();
    }
}
