package com.jbillote.toukabotrequests.dao;

import com.jbillote.toukabotrequests.model.Request;

import java.util.List;

public interface RequestDao {
    List<Request> getRequests();
    boolean addRequest(com.jbillote.toukabotrequests.model.Request request);
    boolean approveRequest(com.jbillote.toukabotrequests.model.Request request);
    boolean declineRequest(com.jbillote.toukabotrequests.model.Request request);
}
