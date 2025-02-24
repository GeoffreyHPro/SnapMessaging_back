package com.example.demo.repository.messageRepository;

import com.example.demo.model.Message;
import java.util.List;

public interface CustomMessageRepository {
    void saveMessage(Message message);
    List<Message> getMessageFromId(int id);
    List<Message> getMessagesFromConversation(int idUser, int idOtherUser);
    List<Integer> getContacts(int idUser);
}
