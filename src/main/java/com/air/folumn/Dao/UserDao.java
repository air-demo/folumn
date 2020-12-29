package com.air.folumn.Dao;

import com.air.folumn.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author air
 * @create 2020-11-14-23:31
 */
@Mapper
@Repository
public interface UserDao {

    User findById(@Param("id") Integer id);

    void insertUser(User user);

    List<User> findByToken(String token);

    void update(User user);

    User findByAccountId(String accountId);
}
