package com.easyfood.user.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class UserDTO {

    private String username;
    private String email;
    private String password;

    public UserDTO(String userfortesting, String s, String testing123) {
        this.username = userfortesting;
        this.email = s;
        this.password = testing123;
    }
}
