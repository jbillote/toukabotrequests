package com.jbillote.toukabotrequests.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SinglePageApplicationConfig {
    // Server index.html for all web pages since everything is handled by React
    @RequestMapping({
            "/",
            "/images",
            "/login",
            "/manageRequests"
    })
    public String index() {
        return "forward:/index.html";
    }
}
