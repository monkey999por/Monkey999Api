package com.monkey999.utils.ent;

public class ServerError {

    public ServerError(String status, String errorCode) {
        this.status = status;
        this.errorCode = errorCode;
    }

    String status;
    String message;

    String errorCode;



}
