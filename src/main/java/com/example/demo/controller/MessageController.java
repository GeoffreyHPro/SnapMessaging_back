package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Authorization")
@RequestMapping(path = "/message")
@Api(tags = "Message", description = "Endpoint")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @GetMapping(path = "/conversation")
    public ResponseEntity getConversation(@RequestParam Integer otherUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return ResponseEntity.status(200)
                .body(messageService.getConversationMessages(authentication.getName(), otherUser));
    }

    @GetMapping(path = "/allMessages")
    public ResponseEntity getMessages() {

        return ResponseEntity.status(200).body(messageService.getAllMessages());
    }

    @GetMapping(path = "/myMessages")
    public ResponseEntity getMyMessages() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return ResponseEntity.status(200).body(messageService.getAllMyMessages(authentication.getName()));
    }

    @GetMapping(path = "/contact")
    public ResponseEntity getContacts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return ResponseEntity.status(200).body(messageService.getAllContacts(authentication.getName()));
    }

    @PostMapping
    public ResponseEntity saveNewMessage(@RequestParam int idSender, @RequestParam String content) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) userService.loadUserByUsername(authentication.getName());

        messageService.saveMessage(content, user.getId(), idSender);
        return ResponseEntity.status(200).body("good");
    }
}
