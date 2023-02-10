package com.monkey999.ent.common;

public class ServerError {

    public ServerError(String status, String errorCode) {
        this.status = status;
        this.errorCode = errorCode;
    }

    String status;
    String message;

    String errorCode;


}
