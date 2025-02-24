package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "messages")
@Getter
@Setter
public class Message {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String content;

    private int userSender;

    private int userReceiver;

    public Message(){}

    public Message(String content, int userSender, int userReceiver) {
        this.content = content;
        this.userSender = userSender;
        this.userReceiver = userReceiver;
    }
}
