package com.air.folumn.Controller;


import com.air.folumn.Service.QuestionService;
import com.air.folumn.cache.TagCache;
import com.air.folumn.entity.Question;
import com.air.folumn.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author air
 * @create 2020-11-15-21:58
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,Model model){
        Question question=questionService.getByID(id);

        //回显
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("tags", TagCache.get());

        return "publish";
    }

    @GetMapping("/publish")
    public String publish(Model model){
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(HttpServletRequest request,
                            Question question,
                            Model model){

        //用来验证和回显
        String title=question.getTitle();
        String description=question.getDescription();
        String tag=question.getTag();

        //回显
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        model.addAttribute("tags", TagCache.get());

        if(title==null||title.trim()==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description==null||description.trim()==""){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }
        if(tag==null||tag.trim()==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }


        String invalid = TagCache.filterInvalid(tag);
        if (StringUtils.isNotBlank(invalid)) {
            model.addAttribute("error", "输入非法标签:" + invalid);
            return "publish";
        }

        User user=(User)request.getSession().getAttribute("user");

        if(user==null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        questionService.createOrUpdate(question,user);

        return "redirect:/";
    }
}
