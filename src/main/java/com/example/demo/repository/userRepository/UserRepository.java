package com.example.demo.repository.userRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
   User findByEmail(String email);
   List<User> findAll();
}
