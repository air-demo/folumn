package com.air.folumn.Service.impl;

import com.air.folumn.Dao.QuestionDao;
import com.air.folumn.Dao.UserDao;
import com.air.folumn.enums.CustomSizeErrorCode;
import com.air.folumn.Exception.CustomSizeException;
import com.air.folumn.Service.QuestionService;
import com.air.folumn.dto.QuestionDTO;
import com.air.folumn.entity.Question;
import com.air.folumn.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author air
 * @create 2020-11-15-23:21
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private UserDao userDao;

    public void create(Question question, User user){
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionDao.create(question);
}

    @Override
    public List<QuestionDTO> getList(String search) {
        List<QuestionDTO> questionList=null;
        if(search==null||search.trim()==""){
            questionList=questionDao.getListAll();
        }else{
            questionList=questionDao.selectBySearch(search);
        }
        return questionList;
    }

    @Override
    public QuestionDTO getDTOById(Integer id) {
        QuestionDTO questionDTO = questionDao.getByDTOId(id);
        if(questionDTO==null){
            throw new CustomSizeException(CustomSizeErrorCode.QUESTION_NOT_FOUND);
        }
        return questionDTO;
    }

    @Override
    public Question getByID(Integer id) {
        return questionDao.getById(id);
    }

    @Override
    public void createOrUpdate(Question question, User user) {
        if(question.getId()==null){
            //创建
            this.create(question,user);
        }else{
            //更新
            Question question1=questionDao.getById(question.getId());
            question.setGmtModified(question1.getGmtCreate());
            int update=questionDao.update(question);
            if(update!=1){
                throw new CustomSizeException(CustomSizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    @Override
    public void incView(Integer id) {
        Question question=new Question();
        question.setId(id);
        question.setViewCount(1);
        questionDao.incView(question);
    }

    @Override
    public List<QuestionDTO> selectRelated(QuestionDTO queryDTO) {
        String[] tags=StringUtils.split(queryDTO.getTag(),",");
        String regexpTag= Arrays.stream(tags).collect(Collectors.joining("|"));
        Question question=new Question();
        question.setId(queryDTO.getId());
        question.setTag(regexpTag);

        List<Question> questions=questionDao.selectRelate(question);
        List<QuestionDTO> questionDTOS=questions.stream().map(q->{
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(q,questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOS;
    }

    @Override
    public List<QuestionDTO> list(Integer id) {
        List<Question> questions = questionDao.list(id);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userDao.findById(id);
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
