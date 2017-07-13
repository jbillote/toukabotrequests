package com.jbillote.toukabotrequests.model;

import java.util.List;

public class RequestResponse {
    private List<Request> requests;

    public RequestResponse() {}

    public RequestResponse(List<Request> requests) {
        this.requests = requests;
    }

    public List<Request> getRequests() {
        return this.requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}
