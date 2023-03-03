package com.calculator.util;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Read {
    static Scanner sc=new Scanner(System.in);
    public static String getDate(){
        LocalDate currentDate = LocalDate.now();
        return currentDate.toString();
    }
    public static String getTime(){
        LocalTime currentTime = LocalTime.now();
        return currentTime.toString();
    }
    public static String input(String prompt){
        while (true){
            try{
                System.out.println(prompt);
                String res=sc.nextLine();
                if(!res.isEmpty()){
                    return res;
                }
            }catch (Exception e){}
        }
    }
    public static double Double(String str) throws NumberFormatException{
        return Double.parseDouble(str);
    }
    public static String str(double val){
        return String.valueOf(val);
    }

    public static int Int(String str) throws NumberFormatException{
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

}
