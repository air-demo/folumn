package com.air.folumn.Controller;


import com.air.folumn.Service.CommentService;
import com.air.folumn.Service.QuestionService;
import com.air.folumn.dto.CommentDTO;
import com.air.folumn.dto.QuestionDTO;
import com.air.folumn.entity.Question;
import com.air.folumn.enums.CommentTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


/**
 * @author air
 * @create 2020-11-21-12:00
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Integer id, Model model){
        QuestionDTO questionDTO = questionService.getDTOById(id);
        List<CommentDTO> comments=commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        List<QuestionDTO> relatedQuestion=questionService.selectRelated(questionDTO);
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        model.addAttribute("relatedQuestion",relatedQuestion);
        return "question";
    }
}
