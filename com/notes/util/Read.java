package com.notes.util;

import com.notes.dto.Notes;

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

    public static List<String> getContent(String prompt) {
        System.out.println("Type [#save?] to Save the Notes:");
        System.out.print(prompt);
        List<String> str=new ArrayList<>();
        while (true){
            String val=sc.nextLine();
            if(val.endsWith("#save?")){
                val=val.substring(0,val.length()-6).strip();
                if(val.length()>0)
                    str.add(val);
                break;
            }
            str.add(val);
        }
        return str;
    }
    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public static List<String> getUpdateContent(List<String> contents) {
        System.out.println("Type [#save?] to Save the Notes:");
        String oldContent="";
        for(String str:contents){
           oldContent+=str+"\\n";
        }
        System.out.println(oldContent);
        System.out.println("copy Paste the Content and then Edit:");
        boolean isEdited=true;
        List<String> str=new ArrayList<>();
        while (true){
            String val=sc.nextLine();
            if(isEdited || val.isBlank()){
                for (String s:val.split("\n")) {
                    if(s.endsWith("#save?")){
                        str.add(s.substring(0,s.length()-5));
                        return str;
                    }
                    str.add(s);
                }
                isEdited=false;
            }
            if(val.endsWith("#save?")){
                str.add(val.substring(0,val.length()-5));
                break;
            }
            str.add(val);
        }
        return str;
    }
    public static String inputWithDefault(String prompt,String defaultValue) {
        System.out.print(prompt);
        String str=sc.nextLine();
        if(str.isBlank()){
            return defaultValue;
        }
        return str;
    }
}
