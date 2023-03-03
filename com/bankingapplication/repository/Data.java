package com.bankingapplication.repository;

import com.bankingapplication.dto.Account;

import java.util.List;

public class Data {
    private List<Account> accounts;
    private List<String> banks;
    private List<String> accountNos;
    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<String> getBanks() {
        return banks;
    }

    public void setBanks(List<String> banks) {
        this.banks = banks;
    }

    public List<String> getAccountNos() {
        return accountNos;
    }

    public void setAccountNos(List<String> accountNos) {
        this.accountNos = accountNos;
    }
}
