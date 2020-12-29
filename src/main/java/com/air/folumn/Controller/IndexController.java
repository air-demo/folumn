package com.air.folumn.Controller;


import com.air.folumn.Service.QuestionService;
import com.air.folumn.Service.UserService;
import com.air.folumn.dto.QuestionDTO;
import com.air.folumn.entity.Question;
import com.air.folumn.entity.User;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author air
 * @create 2020-11-14-11:50
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                        @RequestParam( name= "search",required = false)String search,
                        Model model){
        PageHelper.startPage(pageNum,10);
        List<QuestionDTO> questionDTOList=questionService.getList(search);
        PageInfo<QuestionDTO> questionDTOPageInfo = new PageInfo<>(questionDTOList,5);

        model.addAttribute("pageInfo",questionDTOPageInfo);

        return "index";
    }
}
