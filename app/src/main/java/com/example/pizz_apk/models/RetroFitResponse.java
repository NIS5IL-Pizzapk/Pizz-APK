package com.example.pizz_apk.models;

import java.util.ArrayList;

public class RetroFitResponse<T> {
    private String message;
    private T result;

    public String getMessage() {
        return message;
    }


    public T getResult() {
        return result;
    }

    //get user
    public User getUser() {
        return (User) result;
    }

    public User getResults() {
        return (User) result;
    }

    public boolean getSuccess() {
        return message.equals("success");
    }
}
