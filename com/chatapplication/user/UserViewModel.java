package com.chatapplication.user;

import com.chatapplication.dto.Message;
import com.chatapplication.dto.User;
import com.chatapplication.repository.Repository;

import java.util.List;

public class UserViewModel {
    private UserView userView;
    private Repository repository;
    public UserViewModel(UserView userView) {
        this.userView = userView;
        repository=Repository.getInstance();
    }

    public void getAllUserName() {
        List<User> userList=repository.getAllUsername();
        this.userView.searchUserByName(userList);
    }

    public void getChat(User user, User user1) {
        List<Message> outgoing=repository.getMessages(user,user1);
        List<Message> incoming=repository.getMessages(user1,user);
        this.userView.startChat(outgoing,incoming);

    }

    public void sendMsg(String msg, User user, User user2) {
        Message message=repository.addNewMessage(new Message(user.getId(),user2.getId(),msg));
        if(message==null){
            userView.sendMsgFailed("Something went wrong");
        }else {
            userView.sendMsgSuccessfully(user,user2);
        }
    }
}
