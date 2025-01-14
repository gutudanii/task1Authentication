package com.prodigyinfotech.task1SecureAuthentication.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegisterResponseDTO {
    private String username;
    private String email;
    private String firstName;
    private String lastName;

    public UserRegisterResponseDTO(String username, String email, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
