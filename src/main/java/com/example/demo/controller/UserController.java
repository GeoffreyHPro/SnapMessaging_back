package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserList;
import com.example.demo.model.User;
import com.example.demo.payload.PseudoPayload;
import com.example.demo.reponses.payload.UserPayload;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.stream.Stream;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "Authorization")
@Api(tags = "User", description = "Endpoint")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/infos")
    public ResponseEntity getUsers(
            Authentication authentication) {
        try {
            UserPayload response = userService.getUserInformations(authentication.getName());
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("response");
        }
    }

    @PatchMapping("/modifPseudo")
    public ResponseEntity updatePseudo(
            Authentication authentication,
            @RequestBody PseudoPayload pseudoPayload) {

        userService.changePseudo(pseudoPayload.getPseudo(), authentication.getName());

        return ResponseEntity.status(200).body("");
    }

    @GetMapping
    public ResponseEntity getAllUsers() {

        Stream<UserDTO> listUsers = userService.getAllUsers();
        List<UserDTO> users = listUsers.collect(Collectors.toList());
        UserList list = new UserList(users);
        return ResponseEntity.status(200).body(list);
    }

    @PostMapping(path = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadImage(
            @RequestParam("file") MultipartFile file) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a file!", HttpStatus.BAD_REQUEST);
        }
        try {
            userService.updateImage(authentication.getName(), file.getBytes(), file.getOriginalFilename());
            return new ResponseEntity<>("File uploaded successfully: " + file.getOriginalFilename(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/image")
    public ResponseEntity getImage() throws SQLException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) userService.loadUserByUsername(authentication.getName());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                .filename(user.getNameImage()).build().toString());

        return ResponseEntity.status(200).headers(httpHeaders)
                .contentLength(user.getImage().length).contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(user.getImage());
    }
}