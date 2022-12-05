package com.example.pizz_apk.models;

public class RetroFitResponse<T> {
    private String message;
    private T result;

    public String getMessage() {
        return message;
    }

    public T getResult() {
        return result;
    }
}
