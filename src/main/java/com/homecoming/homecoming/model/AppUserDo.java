package com.homecoming.homecoming.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppUserDo {
    private String username;
    private String password;
    private String name;
    private String location;
}
