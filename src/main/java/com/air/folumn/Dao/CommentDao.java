package com.air.folumn.Dao;


import com.air.folumn.dto.CommentDTO;
import com.air.folumn.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author air
 * @create 2020-11-23-22:10
 */
@Mapper
@Repository
public interface CommentDao {

    void insert(Comment comment);

    Comment selectById(Integer parentId);

    List<CommentDTO> listByQuestionId(Integer id,Integer type);

    void incCommentCount(Comment comment);

    void updateLikeCount(Comment comment);
}
