package com.air.folumn.Service.impl;

import com.air.folumn.Dao.CommentDao;
import com.air.folumn.Dao.NotificationDao;
import com.air.folumn.Dao.QuestionDao;
import com.air.folumn.Dao.UserDao;
import com.air.folumn.Exception.CustomSizeException;
import com.air.folumn.Service.CommentService;
import com.air.folumn.dto.CommentCreateDTO;
import com.air.folumn.dto.CommentDTO;
import com.air.folumn.entity.Comment;
import com.air.folumn.entity.Notification;
import com.air.folumn.entity.Question;
import com.air.folumn.entity.User;
import com.air.folumn.enums.CommentTypeEnum;
import com.air.folumn.enums.CustomSizeErrorCode;
import com.air.folumn.enums.NotificationStatusEnum;
import com.air.folumn.enums.NotificationTypeEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author air
 * @create 2020-11-23-22:10
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;

    @Autowired
    QuestionDao questionDao;

    @Autowired
    UserDao userDao;

    @Autowired
    NotificationDao notificationDao;

    @Transactional
    public void insert(CommentCreateDTO commentCreateDTO, User user) {

        Comment comment=new Comment();
        BeanUtils.copyProperties(commentCreateDTO,comment);
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        comment.setCommentCount(0);

        if(comment.getParentId()==null||comment.getParentId()==0){
            throw new CustomSizeException(CustomSizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType()==null|| !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomSizeException(CustomSizeErrorCode.TYPE_PARAM_WRONG);
        }



        if(comment.getType()==CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment=commentDao.selectById(comment.getParentId());
            if(dbComment==null){
                throw new CustomSizeException(CustomSizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentDao.insert(comment);

            //回复问题
            Question question=questionDao.getById(dbComment.getParentId());
            System.out.println(question);
            if(question==null){
                throw new CustomSizeException(CustomSizeErrorCode.QUESTION_NOT_FOUND);
            }

            Comment parentComment=new Comment();
            parentComment.setId(comment.getParentId());
            parentComment.setCommentCount(1);

            commentDao.incCommentCount(parentComment);
            createNotify(comment,dbComment.getCommentator(), user.getName(), question.getTitle(),NotificationTypeEnum.REPLY_COMMENT, question.getId());
        }else {
            //回复问题
            Question question=questionDao.getById(comment.getParentId());
            if(question==null){
                throw new CustomSizeException(CustomSizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentDao.insert(comment);
            question.setCommentCount(1);
            questionDao.incCommentCount(question);

            createNotify(comment,question.getCreator(),user.getName(), question.getTitle(),NotificationTypeEnum.REPLY_QUESTION, question.getId());
        }
    }

    @Override
    public List<CommentDTO> listByTargetId(Integer id,CommentTypeEnum type) {
        return commentDao.listByQuestionId(id,type.getType());
    }

    @Override
    public void updateLikeCount(Integer id) {
        Comment comment=commentDao.selectById(id);
        comment.setLikeCount(1L);
        commentDao.updateLikeCount(comment);
    }

    private void createNotify(Comment comment, Integer receiver, String notifierName, String outTitle, NotificationTypeEnum notificationTypeEnum, Integer outerId){
        if(receiver == comment.getCommentator()){
            return;
        }
        Notification notification=new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(notificationTypeEnum.getType());
        notification.setOuterId(outerId);
        notification.setNotifier(comment.getCommentator());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outTitle);

        notificationDao.insert(notification);
    }

}
