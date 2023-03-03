package com.bankingapplication.dto;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String firstName;
    private String lastName;
    private String userName;
    private String dob;
    private String accountNumber;
    private String bankName;
    private String mobile;
    private float balance;
    private String mPin;
    private String mtPin;
    private List<Transactions> transactionsList;
    private List<Request> inComingRequestList;
    private List<Request> outGoingRequestList;
    public Account() {
    }
    public Account(String firstName, String lastName, String dob, String bankName, String mobile, String mPin, String mtPin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.bankName = bankName;
        this.mobile = mobile;
        this.mPin = mPin;
        this.mtPin = mtPin;
        this.transactionsList=new ArrayList<>();
        this.inComingRequestList=new ArrayList<>();
        this.outGoingRequestList=new ArrayList<>();
        this.balance=20000.0f;
    }

    public Account(String firstName, String lastName,String userName, String dob, String bankName, String mobile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName= userName;
        this.dob = dob;
        this.bankName = bankName;
        this.mobile = mobile;
    }

    public List<Request> getInComingRequestList() {
        return inComingRequestList;
    }

    public void setInComingRequestList(List<Request> inComingRequestList) {
        this.inComingRequestList = inComingRequestList;
    }

    public List<Request> getOutGoingRequestList() {
        return outGoingRequestList;
    }

    public void setOutGoingRequestList(List<Request> outGoingRequestList) {
        this.outGoingRequestList = outGoingRequestList;
    }

    public void updateBalance(float amount){
        this.balance+=amount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getmPin() {
        return mPin;
    }

    public void setmPin(String mPin) {
        this.mPin = mPin;
    }

    public String getMtPin() {
        return mtPin;
    }

    public void setMtPin(String mtPin) {
        this.mtPin = mtPin;
    }

    public List<Transactions> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transactions> transactionsList) {
        this.transactionsList = transactionsList;
    }
    public void display(){
        System.out.println("----------------------------------------------------------------");
        System.out.printf("\n|     Bank Name  : %-20s",this.bankName);
        System.out.printf("\n|     First Name : %-16s   Last Name: %-16s ",this.firstName,this.lastName);
        System.out.printf("\n|     Account No : %-16s   DOB :%s",this.accountNumber,this.dob);
        System.out.printf("\n|     Mobile No  : %s",this.mobile);
        System.out.printf("\n|     Balance : %.2f\n",this.balance);
        System.out.println("----------------------------------------------------------------");

    }

    public void displayDetails() {
        System.out.print("\n----------------------------------------------------------------");
        System.out.printf("\n|    Bank Name  : %-20s",this.bankName);
        System.out.printf("\n|     First Name : %-16s   Last Name: %-16s ",this.firstName,this.lastName);
        System.out.printf("\n|     User Id : %-16s   DOB :%s",this.userName,this.dob);
        System.out.printf("\n|     Mobile No  : %s\n",this.mobile);
        System.out.println("----------------------------------------------------------------");
    }
}
