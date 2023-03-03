package com.calculator.repository;


import java.util.List;
import com.calculator.dto.User;

public class Data {
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
