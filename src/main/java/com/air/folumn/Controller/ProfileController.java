package com.air.folumn.Controller;

import com.air.folumn.Service.NotificationService;
import com.air.folumn.Service.QuestionService;
import com.air.folumn.dto.NotificationDTO;
import com.air.folumn.dto.QuestionDTO;
import com.air.folumn.entity.Question;
import com.air.folumn.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author air
 * @create 2020-11-18-22:47
 */
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action")String action,
                          Model model){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
            List<QuestionDTO> questionDTOS= questionService.list(user.getId());
            model.addAttribute("questionDTOS", questionDTOS);
        } else if ("replies".equals(action)) {
            List<NotificationDTO> notificationDTOS = notificationService.list(user.getId());
            model.addAttribute("section", "replies");
            model.addAttribute("notificationDTOS", notificationDTOS);
            model.addAttribute("sectionName", "最新回复");
        }
        return "profile";
    }
}
