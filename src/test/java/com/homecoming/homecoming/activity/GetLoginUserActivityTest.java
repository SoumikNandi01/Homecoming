package com.homecoming.homecoming.activity;

import com.google.gson.Gson;
import com.homecoming.homecoming.model.AppUserDo;
import com.homecoming.homecoming.model.rest.GetLoginUserResponse;
import com.homecoming.homecoming.userdatacontainerdao.UserDataContainerDao;
import com.homecoming.homecoming.utils.JsonReader;
import com.homecoming.homecoming.utils.ResponseBuilder;
import com.homecoming.homecoming.validators.AppUserDataValidator;
import com.homecoming.homecoming.validators.ErrorValidator;
import com.homecoming.homecoming.validators.activity.GetLoginUserValidator;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static com.homecoming.homecoming.TestConstants.UserDocumentConstants.*;
import static com.homecoming.homecoming.constants.Constants.AppUserFieldConstants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class GetLoginUserActivityTest extends AbstractTest {

    @Mock
    private UserDataContainerDao userDataContainerDao;

    @Mock
    private ResponseBuilder responseBuilder;

    @InjectMocks
    private GetLoginUserActivity getLoginUserActivity;

    private JsonReader jsonReader;
    private String testDirectory;
    private Gson gson;
    private GetLoginUserValidator getLoginUserValidator;

    @BeforeEach
    public void startup() {
        gson = new Gson();
        jsonReader = new JsonReader();
        testDirectory = "getloginuser/";
        getLoginUserValidator = new GetLoginUserValidator(new ErrorValidator(),
                new AppUserDataValidator());
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getLoginUser_happyCase() throws IOException {
        when(userDataContainerDao.getUserByUsernameAndPassword(anyString(), anyString()))
                .thenReturn(getSampleUser());
        when(responseBuilder.getAppUserFromDocument(any(Document.class)))
                .thenReturn(getSampleAppUser());
        GetLoginUserResponse actualResponse = getLoginUserActivity.getLoginUser(
                TEST_USERNAME, TEST_PASSWORD);
        GetLoginUserResponse expectedResponse = getLoginUserResponse("happyCase.json");
        getLoginUserValidator.validateGetLoginUserResponse(actualResponse, expectedResponse);
    }

    @Test
    public void getLoginUser_invalidCredentials() throws IOException {
        when(userDataContainerDao.getUserByUsernameAndPassword(anyString(), anyString()))
                .thenReturn(null);
        when(responseBuilder.getAppUserFromDocument(any(Document.class)))
                .thenReturn(null);
        GetLoginUserResponse actualResponse = getLoginUserActivity.getLoginUser(
                TEST_USERNAME, TEST_PASSWORD);
        GetLoginUserResponse expectedResponse = getLoginUserResponse("userNotFound.json");
        getLoginUserValidator.validateGetLoginUserResponse(actualResponse, expectedResponse);
    }

    private Document getSampleUser() {
        Document document = new Document();
        document.put(USERNAME, TEST_USERNAME);
        document.put(LOCATION, TEST_LOCATION);
        document.put(PASSWORD, TEST_PASSWORD);
        document.put(NAME, TEST_NAME);
        return document;
    }

    private AppUserDo getSampleAppUser() {
        return AppUserDo.builder()
                .location(TEST_LOCATION)
                .username(TEST_USERNAME)
                .name(TEST_NAME)
                .build();
    }

    private GetLoginUserResponse getLoginUserResponse(String fileName) throws IOException {
        return gson.fromJson(jsonReader.getJsonReaderFromFile(
                baseDirectory + testDirectory + fileName), GetLoginUserResponse.class);
    }
}
