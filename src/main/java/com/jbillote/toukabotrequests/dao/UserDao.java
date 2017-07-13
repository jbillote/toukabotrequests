package com.jbillote.toukabotrequests.dao;

import com.jbillote.toukabotrequests.model.User;

public interface UserDao {
    User findByUsername(String username);
    boolean register(User user);
    boolean login(User user);
}
