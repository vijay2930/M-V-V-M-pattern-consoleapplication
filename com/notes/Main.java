package com.notes;

import com.notes.login.LoginView;
import com.notes.repository.Repository;
import com.notes.util.Read;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LoginView loginView=new LoginView();
        loginView.init();
    }
}
