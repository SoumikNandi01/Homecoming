package com.homecoming.homecoming.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDo {
    private String username;
    private String password;
    private String name;
    private String location;
}
