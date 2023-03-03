package com.notes.login;

import com.notes.dto.User;
import com.notes.repository.Repository;

public class LoginViewModel {
    private LoginView loginView;
    private Repository repository;
    public LoginViewModel(LoginView loginView) {
        this.loginView = loginView;
        repository=Repository.getInstance();
    }

    public void checkLoginCredential(String email, String password) {
        User user=repository.getUser(email,password);
        if(user==null){
            loginView.loginFailed("Invalid Credentials.");
        }else {
            loginView.loginSuccessful(user);
        }
    }

    public void addNewUser(User user) {
        User newUser=repository.addUser(user);
        if(newUser==null){
            loginView.signupFailed("Email you provided was Already exits.Try Again With Another Email...");
        }else {
            loginView.signupSuccessful(user);
        }
    }
    public void exit() {
        repository.save();
    }
}
