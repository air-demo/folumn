package com.air.folumn.Service;

import com.air.folumn.dto.QuestionDTO;
import com.air.folumn.entity.Question;
import com.air.folumn.entity.User;

import java.util.List;

/**
 * @author air
 * @create 2020-11-15-23:21
 */
public interface QuestionService {

    void create(Question question, User user);

    List<QuestionDTO> getList(String search);

    QuestionDTO getDTOById(Integer id);

    Question getByID(Integer id);

    void createOrUpdate(Question question, User user);

    void incView(Integer id);

    List<QuestionDTO> selectRelated(QuestionDTO questionDTO);

    List<QuestionDTO> list(Integer id);
}
