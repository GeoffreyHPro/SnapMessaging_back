package com.example.demo.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UpdatePassword {
    private String currentPassword;
    private String password;

    public UpdatePassword(String cPassword, String PasswordParam) {
        this.currentPassword = cPassword;
        this.password = PasswordParam;
    }
}
