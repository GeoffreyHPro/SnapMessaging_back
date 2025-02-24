package com.example.demo.repository.userRepository;

import com.example.demo.model.User;

public interface CustomUserRepository  {
    boolean saveUser(User user);
    boolean changePassword(User user, String password);
    void changePseudo(User user, String pseudo);
    void updateImage(String email, byte[] image, String nameImage) throws Exception;
}
