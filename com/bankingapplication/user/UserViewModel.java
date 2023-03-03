package com.bankingapplication.user;

import com.bankingapplication.dto.Account;
import com.bankingapplication.dto.Request;
import com.bankingapplication.dto.Transactions;
import com.bankingapplication.repository.Repository;

import java.util.List;

public class UserViewModel {
    private UserView userView;
    private Repository repository;
    public UserViewModel(UserView UserView) {
        this.userView = UserView;repository=Repository.getInstance();
    }

    public void getMyAccount(String userName, String mPin) {
        Account account=repository.getAccount(userName,mPin);
        if(account==null){
            userView.getMyAccountFailed("MPIN is Wrong");
        }else {
            userView.getMyAccountSuccessfully(account);
        }
    }

    public void getMyAccountBalance(String userName, String mPin) {
        Account account=repository.getAccount(userName,mPin);
        if(account==null){
            userView.getMyAccountBalanceFailed("MPIN is Wrong");
        }else {
            userView.getMyAccountBalanceSuccessfully(account.getBalance());
        }
    }

    public void getTheirAccount(String accountId) {
        Account account=repository.getTheirAccount(accountId);
        if(account==null){
            userView.getTheirAccountFailed("Username or Account No you provided was Incorrect.Try again.");
        }else {
            userView.getTheirAccountSuccessfully(account);
        }
    }

    public void checkTransactionIsValid(String userName, String mtPin, float amount, Account theirAccount) {
        float balance = repository.getBalance(userName,mtPin);
        if(balance==-1.0f){
            userView.transactionFailed("mtPin is Incorrect.");
        }else if(amount>balance || amount==balance){
            userView.transactionFailed("Insufficient Balance.");
        }
        Transactions transactions=repository.addTransaction(userName,mtPin,amount,theirAccount);
        if(transactions==null){
            userView.transactionFailed("Something went wrong. Try again");
        }else {
            userView.transactionSuccessfully(transactions);
        }
    }

    public void getTransactionHistory(String userName, String mtpin) {
        List<Transactions> transactionsList=repository.getTransactionHistory(userName,mtpin);
        if(transactionsList==null){
            userView.getTransactionHistoryFailed("mtpin you provided was Incorrect");
        }else {
            userView.getTransactionHistorySuccessfully(transactionsList);
        }
    }

    public void checkGiveRequestPaymentDetailValid(String userName, String mtpin, float amount, String accountId) {
        Account account=repository.getTheirAccount(accountId);
        if(account==null){
            userView.giveRequestPaymentFailed("Account No/Username you provided was not Found check the details" +
                    " try again");
        }
        Request myRequest =repository.addGiveRequestPayment(userName,mtpin,amount,account.getUserName());
        if(myRequest==null){
            userView.giveRequestPaymentFailed("mtpin you provided was Incorrect.");
        }
        userView.giveRequestPaymentSuccessfully(myRequest);
    }

    public void getMyRequests(String userName, String mtpin) {
        List<Request> requestList=repository.getMyRequests(userName,mtpin);
        if(requestList==null){
            userView. getMyRequestFailed("mtpin you provided was InCorrect.");
        }
        userView.getMyRequestSuccessfully(requestList);
    }

    public void getIncomingRequests(String userName, String mtpin) {
        List<Request> requestList=repository.getIncomingRequests(userName,mtpin);
        if(requestList==null){
            userView.getIncomingRequestsFailed("mtpin you provided was Incorrect");
        }
        userView.getIncomingRequestsSuccessfully(requestList);
    }

    public void acceptingIncomingRequest(Request request, String userName, String mtpin, List<Request> requestList) {
        Request request1=repository.acceptingIncomingRequest(request,userName,mtpin);
        if(request1==null){
            userView.acceptingIncomingRequestFailed("mtpin you provided was Incorrect or Insuffice balance in " +
                    "your account" +
                    ".Try again");
        }else {
            userView.acceptingIncomingRequestSuccessfully(request,requestList);
        }
    }

    public void cancellingIncomingRequest(Request request, String userName, String mtpin, List<Request> requestList) {
        Request request1=repository.cancellingIncomingRequest(request,userName,mtpin);
        if(request1==null){
            userView.cancellingIncomingRequestFailed("mtpin you provided was Incorrect.Try again");
        }else {
            userView.cancellingIncomingRequestSuccessfully(request,requestList);
        }
    }
}
