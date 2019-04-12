package com.cao.nang.duan.chatgroup.modelgroup;

public class Message {
    String message;
    private String date;
    private String  email;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Message(String message,String email ,String date) {
        this.message = message;
        this.email=email;
        this.date=date;
    }
}
