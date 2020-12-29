package com.air.folumn.Controller;

import com.air.folumn.Service.NotificationService;
import com.air.folumn.dto.NotificationDTO;
import com.air.folumn.dto.ResultDTO;
import com.air.folumn.entity.User;
import com.air.folumn.enums.CustomSizeErrorCode;
import com.air.folumn.enums.NotificationTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @author air
 * @create 2020-11-28-15:33
 */
@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name="id")Integer id){
        User user=(User)request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:/";
        }
        NotificationDTO notificationDTO=notificationService.read(id,user);

        if (NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType()
                || NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDTO.getType()) {
            return "redirect:/question/" + notificationDTO.getOuterId();
        } else {
            return "redirect:/";
        }
    }
}
