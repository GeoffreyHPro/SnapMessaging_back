package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.demo.model.User;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;

@Component
public class InitConfig implements CommandLineRunner {

    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public InitConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User("admin@admin.com", passwordEncoder.encode("password"));
        User user2 = new User("userName@gmail.com", passwordEncoder.encode("username"));
        User user3 = new User("geoffrey@gmail.com", passwordEncoder.encode("geoffrey"));
        user.setPseudo("admin");
        user2.setPseudo("username2");

        userService.save(user);
        userService.save(user2);
        userService.save(user3);

        messageService.saveMessage("bonjour",1,2);
        messageService.saveMessage("Comment vas tu ?",2,1);
        messageService.saveMessage("je vais tres bien",2,1);

        messageService.saveMessage("re-bonjour, je suis content de te voir",2,3);
        messageService.saveMessage("Inazuma eleven, quel jeu",3,2);
        messageService.saveMessage("tu vas y jouer ce soir ?",3,2);
        
        messageService.saveMessage("Slt gros tu vas a la fontaine ce soir ?",3,1);
        messageService.saveMessage("y a moye hein, je vais voir ça, je te dis ASAP",1,3);
    }
}