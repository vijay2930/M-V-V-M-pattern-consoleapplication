package com.chatapplication.util;

import com.chatapplication.dto.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {
    public static boolean isStrongPassword(String password) {
        if (password.length() < 8) {
            return false;
        }

        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        if (!password.matches(".*[a-z].*")) {
            return false;
        }

        if (!password.matches(".*\\d.*")) {
            return false;
        }

        if (!password.matches(".*[!@#$%^&*()_+\\-=[{]};:'\"\\\\|,.<>/?].*")) {
            return false;
        }
        return true;
    }


    public static boolean isValidEmail(String email){
        String emailRegex = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
