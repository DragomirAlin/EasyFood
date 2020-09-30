package com.easyfood.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private String username;
    private String email;
    private String password;

    public User(String userfortesting, String s, String testing123) {
        this.username = userfortesting;
        this.email = s;
        this.password = testing123;
    }
}
