package com.jbillote.toukabotrequests.service;

import java.util.List;

public interface RequestService {
    public List<com.jbillote.toukabotrequests.model.Request> getRequests();
    public boolean addRequest(com.jbillote.toukabotrequests.model.Request request);
    public boolean approveRequest(com.jbillote.toukabotrequests.model.Request request);
    public boolean declineRequest(com.jbillote.toukabotrequests.model.Request request);
}
