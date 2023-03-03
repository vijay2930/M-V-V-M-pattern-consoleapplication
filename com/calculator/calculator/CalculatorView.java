package com.calculator.calculator;

import com.calculator.dto.Expression;
import com.calculator.login.LoginView;
import com.calculator.util.Read;

import java.util.List;

public class CalculatorView{
    private CalculatorModelView calculatorModelView;
    private String userName;

    public CalculatorView(String userName) {
        this.userName = userName;
        this.calculatorModelView =new CalculatorModelView(this);
    }

    public void init() {
        this.start();
    }

    private void start() {
        System.out.println("Home Page");
        System.out.println("---------");
        System.out.println("1. Compute Value");
        System.out.println("2. View History");
        System.out.println("3. Logout");
        int choice=0;
        try{
            choice=Read.Int(Read.input("Enter your choice: "));
        }catch (Exception e){}
        this.checkValidChoice(choice);
    }

    private void checkValidChoice(int choice) {
        switch (choice){
            case 1:
                this.getComputeValue();
            case 2:
                this.getHistory();
            case 3:
                this.logout();
            default:
                System.out.println("Please enter only the Valid Choice.");
        }
    }

    private void logout() {
        new LoginView().init();
    }

    private void getHistory() {
        System.out.println("Get History by Date");
        String date=Read.input("Enter your date<yyyy-mm-dd>: ");
        calculatorModelView.getMyHistory(userName,date);
    }

    private void getComputeValue() {
        System.out.println("Expression Page");
        System.out.println("sqrt - for finding sqrt(val)");
        System.out.println("log - for finding Log(val)");
        System.out.println("sin - for finding sin(val)");
        System.out.println("cos - for finding cos(val)");
        System.out.println("tan - for finding tan(val)");
        System.out.println("asin - for finding arcSin(val)");
        System.out.println("acos - for finding arcCos(val)");
        System.out.println("atan - for finding arcTan(val)");
        String exp=Read.input("Enter Expression:");
        calculatorModelView.getResult(userName,exp);

    }

    public void getMyHistoryFailed(String s) {
        System.out.println("Failed to get My History");
        System.out.println(s);
        System.out.println("Returning to the Home Page");
        this.init();
    }

    public void getMyHistorySuccessfully(List<Expression> expressionList) {
        System.out.println("History get Successfully");
        if(expressionList.isEmpty()){
            System.out.println("You haven't Done Any Calculation on that Date");
        }else {
            for (Expression exp:expressionList) {
                exp.display();
            }
        }
        System.out.println("Returning to the Home page");
        this.init();
    }

    public void getResultFailed(String s) {
        System.out.println("Failed to get Result");
        System.out.println(s);
        System.out.println("Do you want to Enter expression again");
        System.out.println("1. yes");
        System.out.println("2. no");
        while (true){
            try {
                int choice=Read.Int(Read.input("Enter your choice:"));
                if(choice==1){
                    System.out.println("Returning to the Exepression Page");
                    this.getComputeValue();
                }else if(choice==2){
                    System.out.println("Returning to the Home Page");
                    this.init();
                }
            }catch (Exception e){}
            System.out.println("Please Enter a Valid Choice.");
        }

    }

    public void getResultSuccessfully(String res) {
        System.out.println("Compute Successfully");
        System.out.println("the Result is "+res);
        System.out.println("Do you want to Enter expression again");
        System.out.println("1. yes");
        System.out.println("2. no");
        while (true){
            try {
                int choice=Read.Int(Read.input("Enter your choice:"));
                if(choice==1){
                    System.out.println("Returning to the Exepression Page");
                    this.getComputeValue();
                }else if(choice==2){
                    System.out.println("Returning to the Home Page");
                    this.init();
                }
            }catch (Exception e){}
            System.out.println("Please Enter a Valid Choice.");
        }
    }
}
