package com.bankingapplication.dto;

public class Transactions {
    public Transactions() {}
    private String myAccountNo;
    private String theirAccountNo;
    private String myBankName;
    private String theirBankName;
    private boolean isCredit;
    private float amount;
    private String date;
    public void display(){
        System.out.printf("\n--------------------------------------------------------");
        System.out.printf("\nDate: %s",this.date);
        System.out.printf("\n Account No: %50s",this.theirAccountNo);
        System.out.printf("\n Amount: %50f ",isCredit?"+"+(this.amount):(this.amount*-1));
        System.out.printf("\n--------------------------------------------------------\n");
    }

    public Transactions(String myAccountNo, String theirAccountNo, String myBankName, String theirBankName, boolean isCredit, float amount, String date) {
        this.myAccountNo = myAccountNo;
        this.theirAccountNo = theirAccountNo;
        this.myBankName = myBankName;
        this.theirBankName = theirBankName;
        this.isCredit = isCredit;
        this.amount = amount;
        this.date = date;
    }

    public String getMyAccountNo() {
        return myAccountNo;
    }

    public void setMyAccountNo(String myAccountNo) {
        this.myAccountNo = myAccountNo;
    }

    public String getTheirAccountNo() {
        return theirAccountNo;
    }
    public String getMyBankName() {
        return myBankName;
    }

    public void setMyBankName(String myBankName) {
        this.myBankName = myBankName;
    }

    public String getTheirBankName() {
        return theirBankName;
    }

    public void setTheirBankName(String theirBankName) {
        this.theirBankName = theirBankName;
    }
    public void setTheirAccountNo(String theirAccountNo) {
        this.theirAccountNo = theirAccountNo;
    }

    public boolean isCredit() {
        return isCredit;
    }

    public void setCredit(boolean credit) {
        isCredit = credit;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public void displaySuccessTransaction(){
        System.out.printf("\n-------------------------------------------------\n");
        System.out.println("Amount Debited Successfully");
        System.out.printf("\n Account No: %-20s Date: %s",this.theirAccountNo,this.date);
        System.out.printf("\n Amount: %f ",this.amount);
        System.out.printf("\n-------------------------------------------------\n");
    }
}
