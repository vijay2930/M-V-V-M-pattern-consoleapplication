package com.chatapplication.repository;

import com.chatapplication.dto.Message;
import com.chatapplication.dto.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    public static Repository repository;
    public static Repository getInstance(){
        if(repository==null){
            repository=new Repository();
        }
        return repository;
    }
    private Repository(){
        startConnect();
    }
//    Get Connection
    private Connection conn;
    private void startConnect(){
        String url = "jdbc:mysql://localhost:3306/chatapp";
        String username = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected successfully");
//            String query = "INSERT INTO user (username, email,password) VALUES (?, ?, ?)";

//            PreparedStatement stmt = conn.prepareStatement(query);
//
//            stmt.setString(1,"vijay");
//            stmt.setString(2, "vijay@gmail.com");
//            stmt.setString(3, "Vijay@30");
//
//            int rowsInserted = stmt.executeUpdate();
//
//            if (rowsInserted > 0) {
//                System.out.println("A new user was inserted successfully!");
//            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void endConnection(){
        try{
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        System.out.println(Repository.getInstance().getUser("vijay@gmail.com","Vijay@30"));
//    }
    public User addNewUser(User user){
        List<User> userList=this.getAllUsername();
        for (User user1:userList) {
            if(user1.getUserName().equals(user.getUserName()) && user1.getEmail().equals(user.getEmail())){
                return null;
            }
        }
        String query = "INSERT INTO user (username, email,password) VALUES (?, ?, ?)";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(query);
                stmt.setString(1, user.getUserName());
                stmt.setString(2, user.getEmail());
                stmt.setString(3, user.getPassword());
                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
//                    System.out.println("A new user was inserted successfully!");
                    return user;
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        return null;
    }
    public List<User> getAllUsername(){
        String query = "SELECT id,username,email FROM user";
        List<User> userNameList=new ArrayList<>();
        try{
            PreparedStatement stmt = this.conn.prepareStatement(query);
            ResultSet rs=stmt.executeQuery();
            while (rs.next()) {
                User temp=new User(rs.getString("username"), rs.getString("email"),"");
                temp.setId(rs.getInt("Id"));
                userNameList.add(temp);

            }
            return userNameList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public Message addNewMessage(Message message){
        String query = "INSERT INTO messages (fromID, toID,message) VALUES (?, ?, ?)";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, message.getFromId());
            stmt.setInt(2, message.getToId());
            stmt.setString(3, message.getMessage());
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                return message;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public List<Message> getMessages(User user1, User user2){
        List<Message> messageList=new ArrayList<>();
        try {
            String sql = "SELECT * FROM messages WHERE fromID = ? AND toID = ?  ORDER BY id ASC";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user1.getId());
            pstmt.setInt(2, user2.getId());
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                Message message=new Message(rs.getInt("fromID"),rs.getInt("toID"),rs.getString("message"));
                message.setId(rs.getInt("id"));
                messageList.add(message);
            }
            return messageList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void save() {
        this.endConnection();
    }

    public User getUser(String email, String password) {
        String query = "SELECT id,username,email,password FROM user WHERE email = ? AND password =?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1,email);
            stmt.setString(2,password);
            ResultSet rs=stmt.executeQuery();
            while (rs.next()) {
                User user=new User(rs.getString("username"),rs.getString("email"),"");
                user.setId(rs.getInt("id"));
                return user;
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
