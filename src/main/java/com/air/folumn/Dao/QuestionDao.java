package com.air.folumn.Dao;

import com.air.folumn.dto.QuestionDTO;
import com.air.folumn.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author air
 * @create 2020-11-15-23:20
 */
@Mapper
@Repository
public interface QuestionDao {
    //发起问题
    void create(Question question);

    //前端展示问题列表页面
    List<QuestionDTO> getListAll();

    //前端查询展示详细问题
    QuestionDTO getByDTOId(Integer id);

    //前端查询展示发起问题页面,查询评论回复的问题
    Question getById(Integer id);

    //前端用户更新自己问题
    int update(Question question);

    //增加阅读数
    int incView(Question record);

    //更新评论数
    int incCommentCount(Question record);

    //相关问题展示
    List<Question> selectRelate(Question question);

    //消息中心中我的提问展示列表
    List<Question> list(Integer id);

    //前端展示搜索问题
    List<QuestionDTO> selectBySearch(String search);
}
