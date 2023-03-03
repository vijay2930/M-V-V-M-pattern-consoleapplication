package com.calculator.login;

import com.calculator.calculator.CalculatorView;
import com.calculator.dto.*;
import com.calculator.util.*;

public class LoginView {
    private LoginViewModel loginViewModel;
    public LoginView() {
        loginViewModel =new LoginViewModel(this);
    }
    public void init(){this.start();}
    private void start() {
        System.out.println("-----------------");
        System.out.println("Calculator Application");
        System.out.println("-----------------");
        System.out.println("Main Page");
        System.out.println("---------");
        System.out.println("1. Login");
        System.out.println("2. SignUp");
        System.out.println("3. Exit");
        int choice=0;
        try {
            choice= Read.Int(Read.input("Enter your choice: "));
        }catch (NumberFormatException e){}
        this.checkValidChoice(choice);
    }

    private void checkValidChoice(int choice) {
        switch (choice){
        case 1:
//            code here
            this.getLoginInfo();
            break;
        case 2:
            this.getSignUpInfo();
//            code here
            break;
        case 3:
            loginViewModel.exit();
            System.exit(0);
//            code here
            break;
        default:
            System.out.println("Please enter only the Valid Choice....\n");
            this.init();
        }
    }

    private void getSignUpInfo() {
        System.out.println("SignUp Page");
        System.out.println("-----------");
        String userName=Read.input("Enter UserName: ");
        String email=Read.getEmail("Enter Email: ");
        String password=Read.getPassword("Enter password: ");
        loginViewModel.addNewUser(new User(userName,email,password));
    }
    private void getLoginInfo() {
        System.out.println("Login Page");
        System.out.println("----------");
        String email=Read.getEmail("Enter Email: ");
        String password=Read.input("Enter Password: ");
        loginViewModel.checkLoginCredential(email,password);
    }
    public void loginSuccessful(User user) {
        System.out.println("\nLogin Successful.");
        System.out.println("Welcome "+user.getUserName());
//        Redirecting;
        new CalculatorView(user.getEmail()).init();

    }
    public void loginFailed(String msg) {
        System.out.println("\nLogin Failed");
        System.out.println(msg);
        this.init();
    }

    public void signupFailed(String msg) {
        System.out.println("\nSignUp Failed");
        System.out.println(msg);
        this.init();
    }

    public void signupSuccessful(User user) {
        System.out.println("\nSignup Successful.");
        System.out.println("Welcome "+user.getUserName());
//      Redirect;
        new CalculatorView(user.getEmail()).init();
    }
}
