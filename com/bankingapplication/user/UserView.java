package com.bankingapplication.user;

import com.bankingapplication.dto.Account;
import com.bankingapplication.dto.Request;
import com.bankingapplication.dto.Transactions;
import com.bankingapplication.login.LoginView;
import com.bankingapplication.util.Read;

import java.util.List;

public class UserView{

    private final String userName;
    private UserViewModel userViewModel;
    public UserView(String userName) {
        this.userName=userName;
        this.userViewModel =new UserViewModel(this);
    }

    public void init() {
        this.start();
    }

    private void start() {
        System.out.println("Home Page");
        System.out.println("---------");
        System.out.println("1. My Account");
        System.out.println("2. check Balance");
        System.out.println("3. Transfer Amount");
        System.out.println("4. View All Translation History");
        System.out.println("5. Give Request");
        System.out.println("6. View My Requests");
        System.out.println("7. View Incoming Request");
        System.out.println("8. Logout");
        int choice=0;
        try{
            choice= Read.changeToInt(Read.input("Enter your choice: "));
        }catch (NumberFormatException e){}
        this.checkValidChoice(choice);
    }

    private void checkValidChoice(int choice) {
        switch (choice){
            case 1:
                this.getMyAccountCredential();
            case 2:
                this.getMyAccountBalance();
            case 3:
                this.getTransactionDetails();
            case 4:
                this.getTransactionHistory();
            case 5:
                this.getGiveRequestPaymentDetail();
            case 6:
                this.getMyRequestsConfirmation();
            case 7:
                this.getIncomingRequestsConfirmation();
            case 8:
                this.logout();
            default:
                System.out.println("Please enter only the valid Choice.");
        }
    }

    private void getIncomingRequestsConfirmation() {
        System.out.println("Confirm on Viewing Incoming Request");
        String mtpin=Read.getPinNo("Enter your mtpin: ");
        userViewModel.getIncomingRequests(userName,mtpin);
    }

    private void getMyRequestsConfirmation() {
        System.out.println("Confirm on Getting Request");
        String mtpin=Read.getPinNo("Enter your mtpin: ");
        userViewModel.getMyRequests(userName,mtpin);
    }

    private void getGiveRequestPaymentDetail() {
        System.out.println("Give Request");
        String accountId=Read.input("Enter their Account No/Username:");
        String mtpin=Read.input("Enter your mtpin: ");
        float amount;
        while (true){
            try{
                amount=Read.changeToFloat(Read.input("Enter request amount: "));
                break;
            }catch (Exception e){
                System.out.println("Please enter a valid Amount.");
            }

        }
        userViewModel.checkGiveRequestPaymentDetailValid(userName,mtpin,amount,accountId);
    }

    private void getTransactionHistory() {
        System.out.println("Confirm getting Transaction History");
        String mtpin=Read.getPinNo("Enter MTPIN: ");
        userViewModel.getTransactionHistory(userName,mtpin);
    }

    private void getTransactionDetails () {
        System.out.println("Transaction Page");
        String accountId=Read.input("Enter their AccountNo/UserName: ");
        userViewModel.getTheirAccount(accountId);
    }

    private void getMyAccountBalance() {
        System.out.println("Confirm View your Account Balance");
        String mPin=Read.getPinNo("Enter MPIN: ");
        userViewModel.getMyAccountBalance(this.userName, mPin );
        System.out.println("Returning to the Home Page.");
        this.init();
    }

    private void logout() {
        System.out.println("Logout Successfully");
        System.out.println("Returning to the Main Page");
        new LoginView().init();
    }

    private void getMyAccountCredential() {
        System.out.println("Confirm View your Account");
        String mPin=Read.input("Enter your MPIN: ");
        userViewModel.getMyAccount(this.userName,mPin);
    }

    public void getMyAccountFailed(String msg) {
        System.out.println("Failed to Get Account Details");
        System.out.println(msg);
        System.out.println("Returning to the Home Page");
        this.init();
    }
    public void getMyAccountSuccessfully(Account account) {
        System.out.println("The Account Details");
        System.out.println("-------------------");
        account.display();
        System.out.println("Returning to the Home Page");
        this.init();
    }

    public void getMyAccountBalanceSuccessfully(float balance) {
        System.out.println("Account Balance Fetched Successfully");
        System.out.println("Balance are ₹"+balance);
    }

    public void getMyAccountBalanceFailed(String msg) {
        System.out.println("Failed to get the Account Balance");
        System.out.println(msg);
        System.out.println("Returning to the Home Page");
        this.init();
    }

    public void getTheirAccountFailed(String msg) {
        System.out.println("Failed to get their Account.");
        System.out.println(msg);
        System.out.println("Returning to the Home Page");
        this.init();
    }
    public void getTheirAccountSuccessfully(Account account) {
        System.out.println("Their Account Fetched Successfully");
        account.displayDetails();
        String confirm=Read.input("Enter [CONFIRM] to proceed Transaction: ");
        if(confirm.equals("CONFIRM")){
            this.getTransactionAmount(account);
        }else {
            System.out.println("Abort Transaction");
            System.out.println("Returning to the Home Page");
            this.init();
        }
    }
    public void transactionFailed(String msg) {
        System.out.println("Transaction Failed");
        System.out.println(msg);
        System.out.println("Returning to the Home Page");
        this.init();
    }
    public void transactionSuccessfully(Transactions transactions) {
        System.out.println("Transaction Successfully");
        transactions.displaySuccessTransaction();
        System.out.println("Returning to the Home Page");
        this.init();
    }

    public void getTransactionHistoryFailed(String msg) {
        System.out.println("Failed to get Transaction History");
        System.out.println(msg);
        System.out.println("Returning to the Home Page");
        this.init();
    }

    public void getTransactionHistorySuccessfully(List<Transactions> transactionsList) {
        System.out.println("Transaction History");
        if(transactionsList.isEmpty()){
            System.out.println("No Transaction has been done.");
        }
        for (Transactions transactions: transactionsList){
            transactions.display();
        }
        System.out.println("Returning to the Home Page");
        this.init();
    }

    public void giveRequestPaymentFailed(String msg) {
        System.out.println("Give Payment Request Failed");
        System.out.println(msg);
        System.out.println("Returning to the Home Page");
        this.init();
    }

    public void giveRequestPaymentSuccessfully(Request myRequest) {
        System.out.println("Request Send Successfully");
        myRequest.display();
        System.out.println("Returning to the Home Page");
        this.init();
    }

    public void getMyRequestFailed(String msg) {
        System.out.println("Failed to get My Request");
        System.out.println(msg);
        System.out.println("Returning to the Home Page");
        this.init();
    }

    public void getMyRequestSuccessfully(List<Request> requestList) {
        System.out.println("My Request get Successfully");
        if(requestList.isEmpty()){
            System.out.println("You haven't send any request yet");
        }else {
            for (Request request:requestList) {
                if(request.isRequestPending()){
                    System.out.println("Request State Pending");
                    request.display();
                }else if(request.isRequestSuccess()) {
                    System.out.println("Request state Success");
                    request.display();
                }else {
                    System.out.println("Request state Failed");
                    request.display();
                }
            }
        }
        System.out.println("Returning to the Home Page");
        this.init();
    }

    public void getIncomingRequestsFailed(String msg) {
        System.out.println("Failed to get the Incoming Requests");
        System.out.println(msg);
        System.out.println("Returning to the Home Page.");
        this.init();
    }

    public void getIncomingRequestsSuccessfully(List<Request> requestList) {
        System.out.println("Incoming Requests fetched Successfully");
        if(requestList.isEmpty()){
            System.out.println("No New Request Found.");
            System.out.println("Returning to the Home Page");
            this.init();
        }else {
            Request request=requestList.get(0);
            request.display();
            String mtpin=Read.getPinNo("Enter your mtpin: ");
            System.out.println("Do you want to accept the Request");
            System.out.println("1. Yes");
            System.out.println("2. No");
            String choice="";
            while (true) {
                try {
                    choice = Read.input("Enter your choice:");
                    if(choice.equals("1")){
                        userViewModel.acceptingIncomingRequest(request,userName,mtpin,requestList);
                    }else if(choice.equals("2")){
                        userViewModel.cancellingIncomingRequest(request,userName,mtpin,requestList);
                    }
                    System.out.println(choice);

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Please enter only the Valid Choice");
                }
            }

        }
    }

    public void cancellingIncomingRequestSuccessfully(Request request, List<Request> requestList) {
        System.out.println("Request Cancelled Successfully");
        request.display();
        requestList.remove(request);
        this.getIncomingRequestsSuccessfully(requestList);
    }

    public void cancellingIncomingRequestFailed(String msg) {
        System.out.println("Cancelling Request Failed");
        System.out.println(msg);
        System.out.println("Returning to the Home Page");
        this.init();
    }

    public void acceptingIncomingRequestFailed(String msg) {
        System.out.println("Accepting Request Failed");
        System.out.println(msg);
        System.out.println("Returning to the Home Page");
        this.init();
    }

    public void acceptingIncomingRequestSuccessfully(Request request, List<Request> requestList) {
        System.out.println("Request Accepted Successfully");
        request.display();
        requestList.remove(request);
        this.getIncomingRequestsSuccessfully(requestList);
    }

    private void getTransactionAmount(Account theirAccount) {
        System.out.println("Transaction Page");
        float amount;
        while (true){
            try {
                amount=Read.changeToFloat(Read.input("Enter Amount:"));
                break;
            }catch (Exception e){
                System.out.println("Please only enter the amount in number format");
            }
        }
        String mtPin=Read.getPinNo("Enter MTPIN: ");
        userViewModel.checkTransactionIsValid(userName,mtPin,amount,theirAccount);
    }
}
