package com.example.demo.dto;

import java.util.List;

public class UserList {
    private List<UserDTO> users;

    public UserList(List<UserDTO> users) {
        this.users = users;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}
