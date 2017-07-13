package com.jbillote.toukabotrequests.controller;

import com.jbillote.toukabotrequests.model.Request;
import com.jbillote.toukabotrequests.model.RequestResponse;
import com.jbillote.toukabotrequests.model.SuccessResponse;
import com.jbillote.toukabotrequests.service.RequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class RequestController {
    private RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @RequestMapping(value = "/api/requests", method = RequestMethod.GET)
    @ResponseBody
    public RequestResponse getRequests() {
        return new RequestResponse(requestService.getRequests());
    }

    @RequestMapping(value = "/api/requests", method = RequestMethod.POST)
    @ResponseBody
    public SuccessResponse addRequest(@RequestBody Request request) {
        return new SuccessResponse(requestService.addRequest(request));
    }

    @RequestMapping(value = "/api/requests/approve", method = RequestMethod.POST)
    @ResponseBody
    public SuccessResponse approveRequest(@RequestBody Request request) {
        return new SuccessResponse(requestService.approveRequest(request));
    }

    @RequestMapping(value = "/api/requests/decline", method = RequestMethod.DELETE)
    @ResponseBody
    public SuccessResponse declineRequest(@RequestBody Request request) {
        return new SuccessResponse(requestService.declineRequest(request));
    }
}
