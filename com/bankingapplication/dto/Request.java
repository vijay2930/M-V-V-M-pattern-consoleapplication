package com.bankingapplication.dto;

public class Request {
    private String myAccountNo;
    private String theirAccountNo;
    private String myBankName;
    private String theirBankName;
    private String receiptantName;

    public String getReceiptantName() {
        return receiptantName;
    }

    public void setReceiptantName(String receiptantName) {
        this.receiptantName = receiptantName;
    }

    private boolean isRequestSuccess;
    private boolean isRequestPending;
    private float amount;
    private String date;

    public Request() {
    }

    public Request(String myAccountNo, String theirAccountNo, String myBankName, String theirBankName,
                   String requestName, float amount,
                   String date) {
        this.myAccountNo = myAccountNo;
        this.theirAccountNo = theirAccountNo;
        this.myBankName = myBankName;
        this.theirBankName = theirBankName;
        this.receiptantName =requestName;
        this.isRequestSuccess = false;
        this.isRequestPending = true;
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

    public void setTheirAccountNo(String theirAccountNo) {
        this.theirAccountNo = theirAccountNo;
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

    public boolean isRequestSuccess() {
        return isRequestSuccess;
    }

    public void setRequestSuccess(boolean requestSuccess) {
        isRequestSuccess = requestSuccess;
    }

    public boolean isRequestPending() {
        return isRequestPending;
    }

    public void setRequestPending(boolean requestPending) {
        isRequestPending = requestPending;
    }

    public float getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }


    public void display(){
        System.out.println("----------------------------------------------------------------");
        System.out.printf("\nRequester Name: %s\n",this.receiptantName);
        System.out.printf("\nDate: %s",this.getDate());
        System.out.println("IsPending: "+this.isRequestPending);
        System.out.println("IsSuccess: "+this.isRequestSuccess);
        System.out.println("Amount: "+this.amount);
        System.out.println("----------------------------------------------------------------");
    }


}
