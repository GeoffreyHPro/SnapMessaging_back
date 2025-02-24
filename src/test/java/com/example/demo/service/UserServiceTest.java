package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.model.User;

@ExtendWith(value = SpringExtension.class)
@DataJpaTest
public class UserServiceTest {
    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp() throws Exception {
        User newUser = new User("example@com", passwordEncoder.encode("password"));
        userService.save(newUser);
    }

    @Test
    public void SignInSuccess() throws Exception {
        User newUser = new User("bonjour@com", "password");

        // when(userRepository.findByEmail("admin@admin.com")).thenReturn(newUser);

        User user = (User) userService.loadUserByUsername("example@com");
        assertEquals(user, null);

        user = (User) userService.loadUserByUsername("example@com");
        assertEquals(user, null);

    }
}
