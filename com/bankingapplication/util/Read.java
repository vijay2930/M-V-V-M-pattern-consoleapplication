package com.bankingapplication.util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public static String getUserName(String prompt) {
        while (true) {
            try {
                String res = input(prompt);
                if (Check.isValidEmail(res)) {
                    return res;
                } else {
                    System.out.println("Please Enter a Valid username.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    public static String inputWithDefault(String prompt,String defaultValue) {
        System.out.print(prompt);
        String str=sc.nextLine();
        if(str.isBlank()){
            return defaultValue;
        }
        return str;
    }

    public static String getBankName(String prompt,List<String> list) {
        System.out.println(prompt);
        for (int i=0;i<list.size();i++) {
            if (i%3==0)
                System.out.println();
            System.out.printf("%3d %-30s",i+1,list.get(i));
        }
        while (true){
            try{
                int choice=Read.changeToInt(Read.input("\nEnter your choice:"));
                if(choice>=1 && choice<=list.size()){
                    return list.get(choice-1);
                }
            }catch (Exception e){}
        }
    }

    public static String getMobile(String prompt) {
        while (true){
            String res=Read.input(prompt);
            if(Check.isValidMobileNo(res)){
                return res;
            }
            System.out.println("Please enter valid mobile no");
        }
    }

    public static String getPinNo(String prompt) {
        while (true){
            String res=Read.input(prompt);
            if(Check.isValidPin(res)){
                return res;
            }
            System.out.println("Please enter a 4 digit pin");
        }
    }
    public static String generateAccountNumber(String filed1, String field2, String field3,String field4)
    {
        String account="";
        String str=filed1+field2+field3+field4;
        int l=str.length();
        for (int i=0;i<l;i++){
            account+=(int)str.charAt(i)+(int)str.charAt(l-i-1);
        }
        return account.substring(0,16);
    }
    public static String generateUsername(String name){
        Random rand = new Random();
        int randomNum = rand.nextInt(9000);
        return name+"@"+randomNum+".in";
    }

    public static float changeToFloat(String str) throws NumberFormatException {
        return Float.parseFloat(str);
    }
    public static String getCurrentDateAndTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }
}
