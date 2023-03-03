package com.bankingapplication.login;

import com.bankingapplication.dto.Account;
import com.bankingapplication.repository.Repository;

import java.util.List;

public class LoginViewModel {
    private LoginView loginView;
    private Repository repository;
    public LoginViewModel(LoginView loginView) {
        this.loginView = loginView;
        repository=Repository.getInstance();
    }

    public void exit() {
        repository.save();
    }

    public void getAllBankNamesForNewUser() {
        List<String> bankNames=repository.getBankNames();
        loginView.getNewUserDetails(bankNames);
    }

    public void createNewUser(Account account) {
        Account newAccount=repository.createAccount(account);
        if(newAccount==null){
            loginView.newUserCreationFailed("Something went wrong.Try again");
        }else {
            loginView.newUserCreatedSuccessfully(account.getAccountNumber(),account.getUserName());
        }
    }

    public void checkValidLogin(String userName, String mPin) {
        Account account=repository.getAccount(userName,mPin);
        if(account==null){
            loginView.userLoginFailed("UserName or mpin is inCorrect");
        }else {
            loginView.userLoginSuccessful(account.getUserName(),account.getFirstName());
        }
    }
}
