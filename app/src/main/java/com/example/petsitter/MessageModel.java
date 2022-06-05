package com.example.petsitter;

public class MessageModel {
    String userName;
    String lastMessage;

    public MessageModel(String userName, String lastMessage) {
        this.userName = userName;
        this.lastMessage = lastMessage;
    }

    public String getUserName() {
        return userName;
    }

    public String getLastMessage() {
        return lastMessage;
    }
}
