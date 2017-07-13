package com.jbillote.toukabotrequests.model;

public class SuccessResponse {
    private boolean success;

    public SuccessResponse() {}

    public SuccessResponse(boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
