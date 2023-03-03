package com.bankingapplication.repository;

import com.bankingapplication.dto.Account;
import com.bankingapplication.dto.Request;
import com.bankingapplication.dto.Transactions;
import com.bankingapplication.util.Read;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Repository {
    private static Repository repository;
    private Data data;
    private final HashMap<String, Account> userList=new HashMap<>();
    private Repository() {
        this.readFile();
    }

    public  Account getTheirAccount(String accountId) {
        Account theirAccount=null;
        if(this.data.getAccountNos().contains(accountId)){
            for (Account account:this.data.getAccounts()) {
                if(account.getAccountNumber().equals(accountId)){
                    theirAccount=new Account(account.getFirstName(),account.getLastName(),account.getUserName(),
                            account.getDob(), account.getBankName(), account.getMobile());
                    break;
                }
            }

        }else if(this.userList.containsKey(accountId)) {
            Account account=this.userList.get(accountId);
            theirAccount=new Account(account.getFirstName(),account.getLastName(),account.getUserName(),account.getDob(), account.getBankName(), account.getMobile());
        }
        return theirAccount;
    }

    private void readFile() {
        try{
            ObjectMapper mapper=new ObjectMapper();
            data=(Data) mapper.readValue(new File("src\\com\\bankingapplication" +
                            "\\repository\\data.json").getAbsoluteFile(),
                    Data.class);
            for(Account account:data.getAccounts()){
                this.userList.put(account.getUserName(),account);
            }
        }catch (Exception e){
//            System.out.println(e);
            System.out.println("Problem in reading file");
//            e.printStackTrace();
        }
    }
    private void writeFile(){
        try{
            ObjectMapper mapper=new ObjectMapper();
            mapper.writeValue(new File("src\\com\\bankingapplication\\repository\\data.json").getAbsoluteFile(),data);
        }catch (Exception e){e.printStackTrace();}
    }
    public static Repository getInstance() {
        if(repository==null){
            repository=new Repository();
        }
        return repository;
    }

    public List<String> getBankNames() {
        return data.getBanks();
    }

    public Account createAccount(Account account) {
        String accountNo= Read.generateAccountNumber(account.getFirstName(),account.getLastName(),account.getDob(),account.getMobile());
        String userName=Read.generateUsername(account.getFirstName());
        while (true){
            if(!this.userList.containsKey(userName) && !this.data.getAccountNos().contains(accountNo)){
                account.setAccountNumber(accountNo);
                account.setUserName(userName);
                this.userList.put(userName,account);
                this.data.getAccounts().add(account);
                this.data.getAccountNos().add(accountNo);
                return account;
            }
            accountNo=Read.generateAccountNumber(account.getFirstName(),account.getLastName(),userName, accountNo);
            userName=Read.generateUsername(account.getFirstName());
        }
    }

    public Account getAccount(String userName, String mPin) {
        if(this.userList.containsKey(userName) && this.userList.get(userName).getmPin().equals(mPin)){
            return this.userList.get(userName);
        }
        return null;
    }

    public void save() {
        this.writeFile();
    }

    public Transactions addTransaction(String userName, String mtPin, float amount, Account theirAccount1) {
        if(this.userList.get(userName).getMtPin().equals(mtPin)){
            Account myAccount=this.userList.get(userName);
            Account theirAccount=this.userList.get(theirAccount1.getUserName());
            String date=Read.getCurrentDateAndTime();
            Transactions myTransaction=new Transactions(myAccount.getAccountNumber(),theirAccount.getAccountNumber(), myAccount.getBankName(),theirAccount.getBankName(),false,amount,date);
            Transactions theirTransaction=new Transactions(theirAccount.getAccountNumber(),
                    myAccount.getAccountNumber(), theirAccount.getBankName(),myAccount.getBankName(),true,amount,
                    date);
            this.userList.get(userName).getTransactionsList().add(myTransaction);
            this.userList.get(userName).updateBalance(amount*-1);
            this.userList.get(theirAccount.getUserName()).getTransactionsList().add(theirTransaction);
            this.userList.get(theirAccount.getUserName()).updateBalance(amount);
            return myTransaction;
        }
        return null;
    }

    public float getBalance(String userName, String mtPin) {
        if(this.userList.get(userName).getMtPin().equals(mtPin)){
            return this.userList.get(userName).getBalance();
        }else {
            return -1.0f;
        }
    }

    public List<Transactions> getTransactionHistory(String userName, String mtpin) {
        if(this.userList.get(userName).getMtPin().equals(mtpin)){
            return this.userList.get(userName).getTransactionsList();
        }
        return null;
    }

    public Request addGiveRequestPayment(String userName, String mtpin, float amount, String account) {
        if(this.userList.get(userName).getMtPin().equals(mtpin)){
            Account myAccount=this.userList.get(userName);
            Account theirAccount=this.userList.get(account);
            Request myRequest=new Request(myAccount.getAccountNumber(),theirAccount.getAccountNumber(), myAccount.getBankName(),theirAccount.getBankName(),myAccount.getFirstName(),amount, Read.getCurrentDateAndTime());
            Request theirRequest=new Request(theirAccount.getAccountNumber(),myAccount.getAccountNumber(),
                    theirAccount.getBankName(),myAccount.getBankName(),myAccount.getFirstName(),amount,
                    Read.getCurrentDateAndTime());
            myAccount.getOutGoingRequestList().add(myRequest);
            theirAccount.getInComingRequestList().add(theirRequest);
            return myRequest;
        }
        return null;
    }

    public List<Request> getMyRequests(String userName, String mtpin) {
        if(this.userList.get(userName).getMtPin().equals(mtpin)){
            return this.userList.get(userName).getOutGoingRequestList();
        }
        return null;
    }

    public List<Request> getIncomingRequests(String userName, String mtpin) {
        List<Request> requestList=new ArrayList<>();
        if(this.userList.get(userName).getMtPin().equals(mtpin)){
            for (Request request:this.userList.get(userName).getInComingRequestList()) {
                if(request.isRequestPending()){
                    requestList.add(request);
                }
            }
            return requestList;
        }
        return null;
    }

    public Request cancellingIncomingRequest(Request request, String userName, String mtpin) {
        if(this.userList.get(userName).getMtPin().equals(mtpin)){
            for (Request request1:this.userList.get(userName).getInComingRequestList()) {
                if(request.getDate().equals(request1.getDate())){
                    request1.setRequestPending(false);
                    request1.setRequestSuccess(false);
                }
            }
            for (Account account:this.data.getAccounts()) {
                if(account.getAccountNumber().equals(request.getTheirAccountNo())){
                    for (Request request1:account.getOutGoingRequestList()) {
                        if(request1.getDate().equals(request.getDate())){
                            request1.setRequestSuccess(false);
                            request1.setRequestPending(false);
                            return request1;
                        }
                    }
                }
            }
        }
        return null;
    }

    public Request acceptingIncomingRequest(Request request, String userName, String mtpin) {
        if(this.userList.get(userName).getMtPin().equals(mtpin)){
            for (Request request1:this.userList.get(userName).getInComingRequestList()) {
                if(request.getDate().equals(request1.getDate())){
                    request1.setRequestPending(false);
                    request1.setRequestSuccess(true);
                }
            }
            for (Account account:this.data.getAccounts()) {
                if(account.getAccountNumber().equals(request.getTheirAccountNo())){
                    for (Request request1:account.getOutGoingRequestList()) {
                        if(request1.getDate().equals(request.getDate())){
                            request1.setRequestSuccess(true);
                            request1.setRequestPending(false);
                            Transactions transactions=this.addTransaction(userName,mtpin,request1.getAmount(),account);
                            if(transactions==null){
                                return null;
                            }
                            return request1;
                        }
                    }
                }
            }
        }
        return null;
    }
}
