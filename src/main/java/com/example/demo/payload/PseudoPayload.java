package com.example.demo.payload;

import lombok.Data;

@Data
public class PseudoPayload {
    private String pseudo;

    public PseudoPayload(){}

    public PseudoPayload(String pseudo){
        this.pseudo = pseudo;
    }

    public String getPseudo(){
        return this.pseudo;
    }
}
