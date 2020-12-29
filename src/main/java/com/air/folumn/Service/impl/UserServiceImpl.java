package com.air.folumn.Service.impl;

import com.air.folumn.Dao.UserDao;
import com.air.folumn.Service.UserService;
import com.air.folumn.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author air
 * @create 2020-11-15-0:00
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public List<User> findByToken(String token) {
        return userDao.findByToken(token);
    }

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public void createOrUpdate(User user) {
        User dbUser=userDao.findByAccountId(user.getAccountId());
        if(dbUser==null){
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            this.insertUser(user);
        }else{
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            userDao.update(dbUser);;
        }
    }
}
