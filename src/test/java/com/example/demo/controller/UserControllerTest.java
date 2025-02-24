package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.reponses.payload.UserPayload;
import com.example.demo.repository.userRepository.UserRepository;
import com.example.demo.service.JWTUtils;
import com.example.demo.service.UserService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepo;

    @MockBean
    private JWTUtils jwtUtils;
    
    @InjectMocks
    UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    Authentication authentication;

    @Test
    @WithMockUser(username = "admin@admin.com", roles = "admin")
    public void GetUserInformations() throws Exception {
        UserPayload user = new UserPayload("admin@admin.com","password");
        //User user2 = new User("admin@admin.com","password");

        Mockito.when(authentication.getName()).thenReturn("admin@admjt.com");

        //assertEquals(authentication.getName(), );

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/users"))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }
}
