package com.study.android.firebasechat;

public class ChatDTO {
    private String userName;
    private String Message;

    public  ChatDTO(){ }

    public ChatDTO(String userName, String message) {
        this.userName = userName;
        this.Message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
