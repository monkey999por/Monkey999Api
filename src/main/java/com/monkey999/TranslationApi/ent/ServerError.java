package com.monkey999.TranslationApi.ent;

public class ServerError {

    public ServerError(String status, String errorCode) {
        this.status = status;
        this.errorCode = errorCode;
    }

    String status;
    String message;

    String errorCode;



}
