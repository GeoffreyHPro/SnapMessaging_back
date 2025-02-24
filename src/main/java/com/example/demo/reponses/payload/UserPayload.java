package com.example.demo.reponses.payload;

import lombok.Data;

@Data
public class UserPayload {
    private String email;
    private String pseudo;

    public UserPayload(String email, String pseudo){
        this.email = email;
        this.pseudo = pseudo;
    }
}
