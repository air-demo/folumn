package com.air.folumn.Service;

import com.air.folumn.dto.NotificationDTO;
import com.air.folumn.entity.User;

import java.util.List;

/**
 * @author air
 * @create 2020-11-28-13:26
 */
public interface NotificationService {
    List<NotificationDTO> list(Integer id);

    Integer unreadCount(Integer id);

    NotificationDTO read(Integer id, User user);
}
