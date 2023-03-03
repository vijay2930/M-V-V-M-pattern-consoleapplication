package com.calculator.repository;

import com.calculator.util.Read;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.util.HashMap;
import java.util.List;

import com.calculator.dto.*;
public class Repository {
    private static Repository repository;
    private Data data;
    private HashMap<String,User> userList=new HashMap<>();
    private Repository(){
        this.readFile();
    }
    public static Repository getInstance(){
        if(repository==null){
            repository=new Repository();
        }
        return repository;
    }
    private void readFile() {
        try{
            ObjectMapper mapper=new ObjectMapper();
            data=(Data) mapper.readValue(new File("src\\com\\calculator\\repository\\data.json").getAbsoluteFile(),
                    Data.class);
            for(User user:data.getUsers()){
                this.userList.put(user.getEmail(),user);
            }
        }catch (Exception e){
            System.out.println(e);
//            e.printStackTrace();
        }
    }
    private void writeFile(){
        try{
            ObjectMapper mapper=new ObjectMapper();
            mapper.writeValue(new File("src\\com\\calculator\\repository\\data.json").getAbsoluteFile(),data);
        }catch (Exception e){e.printStackTrace();}
    }
    public void save(){this.writeFile();}
    public User getUser(String email, String password) {
        if(this.userList.containsKey(email) && this.userList.get(email).getPassword().equals(password)){
            return this.userList.get(email);
        }
        return null;
    }
    public User addUser(User user) {
        if(this.userList.containsKey(user.getEmail())){
            return null;
        }else {
            this.userList.put(user.getEmail(),user);
            data.getUsers().add(user);
            return this.userList.get(user.getEmail());
        }
    }


    public List<Expression> getMyHistory(String userName, String date) {
        for (History history:this.userList.get(userName).getHistoryList()) {
            if(history.getDate().equals(date)){
                return history.getExpressionList();
            }
        }
        return null;
    }

    public Expression addExpression(String username, String exp, String result) {
        for (History history:this.userList.get(username).getHistoryList()) {
            if(history.getDate().equals(Read.getDate())){
                Expression expression=new Expression(exp,result,Read.getTime());
                history.getExpressionList().add(expression);
                return expression;

            }
        }
        Expression expression=new Expression(exp,result,Read.getTime());
        History history=new History(Read.getDate());
        history.getExpressionList().add(expression);
        this.userList.get(username).getHistoryList().add(history);
        return expression;
    }
}
