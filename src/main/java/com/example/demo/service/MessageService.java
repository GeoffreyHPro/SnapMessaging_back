package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Message;
import com.example.demo.model.User;
import com.example.demo.repository.messageRepository.MessageRepository;
import com.example.demo.repository.messageRepository.MessageRepositoryImpl;

@Service
public class MessageService {
    @Autowired
    private MessageRepository defaultMessageRepository;
    @Autowired
    private MessageRepositoryImpl customMessageRepositoryImpl;

    @Autowired
    private UserService userService;

    public List<Message> getAllMessages() {
        return defaultMessageRepository.findAll();
    }

    public List<Message> getAllMyMessages(String email) {
        User user = (User) userService.loadUserByUsername(email);
        return customMessageRepositoryImpl.getMessageFromId(user.getId());
    }

    public List<Integer> getAllContacts(String email) {
        User user = (User) userService.loadUserByUsername(email);
        return customMessageRepositoryImpl.getContacts(user.getId());
    }

    public List<Message> getConversationMessages(String email, Integer otherUser) {
        User user = (User) userService.loadUserByUsername(email);

        return customMessageRepositoryImpl.getMessagesFromConversation(user.getId(), otherUser);
    }

    public void saveMessage(String content, int userSender, int userReceiver) {
        customMessageRepositoryImpl.saveMessage(new Message(content, userSender, userReceiver));
    }
}
