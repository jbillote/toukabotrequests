package com.jbillote.toukabotrequests.service;

import com.jbillote.toukabotrequests.dao.UserDao;
import com.jbillote.toukabotrequests.dao.UserDaoImpl;
import com.jbillote.toukabotrequests.model.User;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public boolean register(User user) {
        UserDao dao = new UserDaoImpl();

        return dao.register(user);
    }
}
