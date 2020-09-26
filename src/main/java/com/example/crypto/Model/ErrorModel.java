package com.example.crypto.Model;

public class ErrorModel {
    private String errorMsg;

    public ErrorModel(String e){
        errorMsg = e;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
