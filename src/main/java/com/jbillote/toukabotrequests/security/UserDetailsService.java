package com.jbillote.toukabotrequests.security;

import com.jbillote.toukabotrequests.model.User;
import com.jbillote.toukabotrequests.dao.UserDao;
import com.jbillote.toukabotrequests.dao.UserDaoImpl;

import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    public UserDetailsService() {
        super();
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        UserDao dao = new UserDaoImpl();

        final User user = dao.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new UserPrinciple(user);
    }
}
