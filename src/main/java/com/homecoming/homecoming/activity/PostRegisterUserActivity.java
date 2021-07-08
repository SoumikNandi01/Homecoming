package com.homecoming.homecoming.activity;

import com.homecoming.homecoming.model.AppUserDo;
import com.homecoming.homecoming.model.error.Error;
import com.homecoming.homecoming.model.error.ErrorCodes;
import com.homecoming.homecoming.model.rest.PostRegisterUserResponse;
import com.homecoming.homecoming.userdatacontainerdao.UserDataContainerDao;
import com.homecoming.homecoming.utils.DocumentBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PostRegisterUserActivity {
    private final UserDataContainerDao userDataContainerDao;
    private final DocumentBuilder documentBuilder;

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public PostRegisterUserResponse addUser(@RequestBody AppUserDo user) {
        documentBuilder.validateAppUserData(user);
        Document userByUsername = userDataContainerDao.getUserByUsername(user.getUsername());
         if (Objects.isNull(userByUsername)) {
             userDataContainerDao.save(user);
             return PostRegisterUserResponse.builder()
                     .appUserDo(AppUserDo.builder()
                             .name(user.getName())
                             .username(user.getUsername())
                             .location(user.getLocation())
                             .build())
                     .message("registered successfully")
                     .build();
         }

         return PostRegisterUserResponse.builder()
                 .error(Error.builder()
                         .errorCode(ErrorCodes.USER_EXISTS)
                         .errorMessage(ErrorCodes.USER_EXISTS.getErrorMsg())
                         .build())
                 .build();
    }
}
