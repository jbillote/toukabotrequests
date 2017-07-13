package com.jbillote.toukabotrequests.dao;

import java.util.List;

public interface ToukaBotDao {
    List<String> getCommands();
    List<String> getImages(String command);
    boolean addImage(String command, String url);
}
