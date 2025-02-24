package com.example.demo.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String email;
    private String pseudo;

    public UserDTO(String email, String pseudo){
        this.email = email;
        this.pseudo = pseudo;
    }
    public UserDTO(){}
}
