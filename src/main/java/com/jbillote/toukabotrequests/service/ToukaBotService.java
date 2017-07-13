package com.jbillote.toukabotrequests.service;

import java.util.List;

public interface ToukaBotService {
    public List<String> getCommands();
    public List<String> getImages(String command);
    public boolean addImage(String command, String url);
}
