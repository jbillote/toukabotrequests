package com.jbillote.toukabotrequests.service;

import com.jbillote.toukabotrequests.dao.RequestDao;
import com.jbillote.toukabotrequests.dao.RequestDaoImpl;
import com.jbillote.toukabotrequests.model.Request;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    @Override
    public List<Request> getRequests() {
        RequestDao dao = new RequestDaoImpl();

        return dao.getRequests();
    }

    @Override
    public boolean addRequest(Request request) {
        RequestDao dao = new RequestDaoImpl();

        return dao.addRequest(request);
    }

    @Override
    public boolean approveRequest(Request request) {
        RequestDao dao = new RequestDaoImpl();

        return dao.approveRequest(request);
    }

    @Override
    public boolean declineRequest(Request request) {
        RequestDao dao = new RequestDaoImpl();

        return dao.declineRequest(request);
    }
}
