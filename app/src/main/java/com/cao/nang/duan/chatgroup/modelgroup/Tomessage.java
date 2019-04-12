package com.cao.nang.duan.chatgroup.modelgroup;

public class Tomessage {
    private String message;
    private String date;
    private String  email;
    public Tomessage(){}

    public Tomessage(String message,String date,String email) {
        this.message = message;
        this.date=date;
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
