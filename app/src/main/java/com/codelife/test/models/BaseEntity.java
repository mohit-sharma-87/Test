package com.codelife.test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohit Sharma on 09/02/16.
 */
public abstract class BaseEntity {

    @Expose(deserialize = false, serialize = false)
    private RequestStatus mStatus;

    @Expose
    private int mRequestCode = -1;

    @SerializedName("ErrorCode")
    private String responseCode;

    @SerializedName("result")
    private String message;

    public BaseEntity() {
        mStatus = RequestStatus.OK;
        responseCode = "";
        message = "";
    }

    public RequestStatus getRequestStatus() {
        return mStatus;
    }

    public void setRequestStatus(RequestStatus mStatus) {
        this.mStatus = mStatus;
    }

    public int getRequestCode() {
        return mRequestCode;
    }

    public void setRequestCode(int requestCode) {
        mRequestCode = requestCode;
    }

    public int getResponseCode() {
        return Integer.parseInt(responseCode);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
