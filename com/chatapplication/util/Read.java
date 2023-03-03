package com.chatapplication.util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Read {
    private static Scanner sc = new Scanner(System.in);

    public static String input(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String val = sc.nextLine();
                if (!val.isBlank())
                    return val;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static int changeToInt(String str) throws NumberFormatException {
        return Integer.parseInt(str);
    }

    public static String getEmail(String prompt) {
        while (true) {
            try {
                String res = input(prompt);
                if (Check.isValidEmail(res)) {
                    return res;
                } else {
                    System.out.println("Please Enter a Valid Email.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getPassword(String prompt) {
        while (true) {
            String res = input(prompt);
            if (Check.isStrongPassword(res)) {
                return res;
            } else {
                System.out.println("password is to weak. Password must contain one Special Characters,one Capital " +
                        "letters,and numbers");
            }
        }
    }

    public static String getContent(String prompt) {
        System.out.println("Type [#msg?] to send the message:");
        System.out.print(prompt);
        List<String> str=new ArrayList<>();
        while (true){
            String val=sc.nextLine();
            if(val.endsWith("#msg?")){
                val=val.substring(0,val.length()-5).strip();
                if(val.length()>0)
                    str.add(val);
                break;
            }
            str.add(val);
        }
        String res="";
        for (String s:str) {
            res+=s;
        }
        return res;
    }
}
