package com.air.folumn.Service;

import com.air.folumn.Dao.UserDao;
import com.air.folumn.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author air
 * @create 2020-11-14-23:58
 */

public interface UserService {

    void insertUser(User user);

    List<User> findByToken(String token);

    User findById(Integer id);

    void createOrUpdate(User user);
}
