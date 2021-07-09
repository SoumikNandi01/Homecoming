package com.homecoming.homecoming.activity;

import com.homecoming.homecoming.model.AppUserDo;
import com.homecoming.homecoming.model.error.Error;
import com.homecoming.homecoming.model.error.ErrorCodes;
import com.homecoming.homecoming.model.rest.GetLoginUserResponse;
import com.homecoming.homecoming.containerdao.UserDataContainerDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.homecoming.homecoming.constants.Constants.AppUserFieldConstants.*;

@Slf4j
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GetLoginUserActivity {
    private final UserDataContainerDao userDataContainerDao;

    @GetMapping("/login")
    public GetLoginUserResponse getLoginUser(@RequestParam String username,
                                             @RequestParam String password) {
        Document userByUsername = userDataContainerDao
                .getUserByUsernameAndPassword(username, password);

        if (userByUsername == null) {
            log.info("no users with these credentials, please register");
            return GetLoginUserResponse.builder()
                    .appUserDo(null)
                    .error(Error.builder()
                            .errorCode(ErrorCodes.USER_NOT_FOUND)
                            .errorMessage(ErrorCodes.USER_NOT_FOUND.getErrorMsg())
                            .build())
                    .build();
        }

        return GetLoginUserResponse.builder()
                .appUserDo(AppUserDo.builder()
                        .name(userByUsername.getString(NAME))
                        .username(userByUsername.getString(USERNAME))
                        .location(userByUsername.getString(LOCATION))
                        .build())
                .message("welcome user")
                .build();
    }
}
