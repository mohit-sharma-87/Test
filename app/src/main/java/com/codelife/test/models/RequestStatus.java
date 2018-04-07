package com.codelife.test.models;

/**
 * Created by Ashish on 20/02/16.
 */
public enum RequestStatus {

    OK("OK"),
    SERVER_ERROR("SERVER_ERROR"), UNEXPECTED("Unexpected");

    String value;

    RequestStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
