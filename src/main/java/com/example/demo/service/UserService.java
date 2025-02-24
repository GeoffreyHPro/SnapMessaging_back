package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.payload.EmailPasswordRequest;
import com.example.demo.payload.UpdatePassword;
import com.example.demo.reponses.TokenResponse;
import com.example.demo.reponses.payload.UserPayload;
import com.example.demo.repository.userRepository.UserRepository;
import com.example.demo.repository.userRepository.UserRepositoryImpl;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
  @Autowired
  private UserRepository ourUserRepo;
  @Autowired
  private UserRepositoryImpl customUserRepository;

  @Autowired
  private JWTUtils jwtUtils;

  public void save(User user) throws Exception {
    User userGet = null;
    userGet = (User) loadUserByUsername(user.getUsername());
    if (userGet == null) {
      customUserRepository.saveUser(user);
    } else {
      throw new Exception();
    }
  }

  public TokenResponse getTokenResponse(EmailPasswordRequest content) {
    User user = (User) loadUserByUsername(content.getEmail());
    String jwt = jwtUtils.generateToken(user);
    return new TokenResponse(jwt, user.getRole());
  }

  public UserPayload getUserInformations(String email) throws Exception{
    User user = (User) loadUserByUsername(email);
    return new UserPayload(user.getUsername(), user.getPseudo());
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return ourUserRepo.findByEmail(username);
  }

  public void changePassword(UpdatePassword updatePassword, String email) {
    User user = (User) loadUserByUsername(email);
    customUserRepository.changePassword(user, updatePassword.getPassword());
  }

  public void changePseudo(String pseudo, String email) {
    User user = (User) loadUserByUsername(email);
    customUserRepository.changePseudo(user, pseudo);
  }

  public Stream<UserDTO> getAllUsers(){
    return ourUserRepo.findAll().stream().map(user -> new UserDTO(user.getUsername(), user.getPseudo()));
  }

  public void updateImage(String email, byte[] image, String nameImage) throws Exception{
    customUserRepository.updateImage(email, image, nameImage);
  }
}
