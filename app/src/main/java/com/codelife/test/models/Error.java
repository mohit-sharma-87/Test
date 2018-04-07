package com.codelife.test.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mohitsharma on 09/10/17.
 */

public class Error {

    @SerializedName("errorcode")
    private String errorCode;

    @SerializedName("message")
    private String message;


    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setMessage(String messsage) {
        this.message = messsage;
    }
}
