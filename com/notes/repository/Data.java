package com.notes.repository;

import com.notes.dto.User;

import java.util.List;

public class Data {
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
