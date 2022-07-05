package com.example.wj.service;

import com.example.wj.dao.UserDAO;
import com.example.wj.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    UserDAO userDAO;


    public boolean isExist(String username){
        User user = userDAO.findByUsername(username);
        return null!=user;
    }
    public User get(String username, String password){
        return userDAO.getByUsernameAndPassword(username, password);
    }

    public void add(User user) {
        userDAO.save(user);
    }
}
