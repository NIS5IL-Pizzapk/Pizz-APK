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

    public ArrayList<T> getResults() {
        return (ArrayList<T>) result;
    }

    public boolean getSuccess() {
        return message.equals("success");
    }
}
