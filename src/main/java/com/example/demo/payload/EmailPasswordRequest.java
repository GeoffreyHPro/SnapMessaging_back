package com.example.demo.payload;

import org.apache.commons.validator.routines.EmailValidator;

import com.example.demo.exception.EmailException;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class EmailPasswordRequest {
    private String email;
    private String password;

    public EmailPasswordRequest(){}

    public EmailPasswordRequest(String emailParam, String passworParam){
        this.email = emailParam;
        this.password = passworParam;
    }

    public void isValid() throws EmailException {
        EmailValidator emailValidator = EmailValidator.getInstance();
        if(emailValidator.isValid(email)){
            
        }else{
            throw new EmailException();
        }
    }
}
