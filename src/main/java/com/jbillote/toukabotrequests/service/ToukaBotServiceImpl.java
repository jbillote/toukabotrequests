package com.jbillote.toukabotrequests.service;

import com.jbillote.toukabotrequests.dao.ToukaBotDao;
import com.jbillote.toukabotrequests.dao.ToukaBotDaoImpl;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToukaBotServiceImpl implements ToukaBotService {
    @Override
    public List<String> getCommands() {
        ToukaBotDao dao = new ToukaBotDaoImpl();

        return dao.getCommands();
    }

    @Override
    public List<String> getImages(String command) {
        ToukaBotDao dao = new ToukaBotDaoImpl();

        return dao.getImages(command);
    }

    @Override
    public boolean addImage(String command, String url) {
        ToukaBotDao dao = new ToukaBotDaoImpl();

        return dao.addImage(command, url);
    }
}
