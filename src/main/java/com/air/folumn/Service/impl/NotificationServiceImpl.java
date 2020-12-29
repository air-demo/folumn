package com.air.folumn.Service.impl;

import com.air.folumn.Dao.NotificationDao;
import com.air.folumn.Dao.UserDao;
import com.air.folumn.Exception.CustomSizeException;
import com.air.folumn.Service.NotificationService;
import com.air.folumn.dto.NotificationDTO;
import com.air.folumn.dto.QuestionDTO;
import com.air.folumn.entity.Notification;
import com.air.folumn.entity.Question;
import com.air.folumn.entity.User;
import com.air.folumn.enums.CustomSizeErrorCode;
import com.air.folumn.enums.NotificationStatusEnum;
import com.air.folumn.enums.NotificationTypeEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author air
 * @create 2020-11-28-13:26
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationDao notificationDao;

    @Autowired
    UserDao userDao;

    @Override
    public List<NotificationDTO> list(Integer id) {
        List<Notification> notifications = notificationDao.list(id);
        List<NotificationDTO> notificationDTOS = new ArrayList<>();

        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }
        return notificationDTOS;
    }

    @Override
    public Integer unreadCount(Integer id) {
        return notificationDao.unreadCount(id);
    }

    @Override
    public NotificationDTO read(Integer id, User user) {
        Notification notification=notificationDao.selectById(id);
        if(notification.getReceiver()!=user.getId()){
            throw new CustomSizeException(CustomSizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        if(!Objects.equals(notification.getReceiver(),user.getId())){
            throw new CustomSizeException(CustomSizeErrorCode.NOTIFICATION_NOT_FOUND);
        }

        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationDao.update(notification);

        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;
    }
}
