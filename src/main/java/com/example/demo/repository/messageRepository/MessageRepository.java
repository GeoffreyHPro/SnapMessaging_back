package com.example.demo.repository.messageRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findAll();
    List<Message> findByUserReceiver(int userReceiver);
    List<Message> findByUserSender(int userSender);
}
