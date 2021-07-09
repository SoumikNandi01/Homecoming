package com.homecoming.homecoming.containerdao;

import com.homecoming.homecoming.model.AppUserDo;
import org.bson.Document;

public interface UserDataContainerDao {

    void save(AppUserDo appUserDo);
    void update(AppUserDo appUserDo);
    Document getUserByUsernameAndPassword(String username, String password);
    Document getUserByUsername(String username);
}
