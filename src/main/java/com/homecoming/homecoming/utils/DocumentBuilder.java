package com.homecoming.homecoming.utils;

import com.homecoming.homecoming.model.AppUserDo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.bson.Document;
import org.springframework.stereotype.Component;

import static com.homecoming.homecoming.constants.Constants.AppUserFieldConstants.*;
import static com.homecoming.homecoming.constants.Constants.AppUserFieldConstants.NAME;

@Component
public class DocumentBuilder {

    public Document getDocumentFromAppUserDo(AppUserDo appUserDo) {
        validateAppUserData(appUserDo);
        Document document = new Document();
        document.put(USERNAME, appUserDo.getUsername());
        document.put(PASSWORD, appUserDo.getPassword());
        if (StringUtils.isNotEmpty(appUserDo.getLocation()))
            document.put(LOCATION, appUserDo.getLocation());
        if (StringUtils.isNotEmpty(appUserDo.getName()))
            document.put(NAME, appUserDo.getName());
        return document;
    }

    public void validateAppUserData(AppUserDo appUserDo) {
        Validate.notEmpty(appUserDo.getUsername(), "username cannot be empty");
        Validate.notEmpty(appUserDo.getPassword(), "please enter the password");
    }
}
