package com.chatapplication.dto;

public class Message {
    private int id;
    private int fromId;
    private int toId;
    private String message;
    private String status;

    public Message( int fromId, int toId, String message) {
        this.fromId = fromId;
        this.toId = toId;
        this.message = message;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", fromId=" + fromId +
                ", toId=" + toId +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
