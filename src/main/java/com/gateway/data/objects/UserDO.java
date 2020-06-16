package com.gateway.data.objects;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDO {
    private int id;
    private String name;
    private String password;

    public UserDO() {}

    public UserDO(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
