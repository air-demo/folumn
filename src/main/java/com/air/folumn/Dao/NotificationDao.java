package com.air.folumn.Dao;

import com.air.folumn.entity.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author air
 * @create 2020-11-28-13:27
 */
@Mapper
@Repository
public interface NotificationDao {
    void insert(Notification notification);

    List<Notification> list(Integer id);

    Integer unreadCount(Integer id);

    Notification selectById(Integer id);

    void update(Notification notification);
}
