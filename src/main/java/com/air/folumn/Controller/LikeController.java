package com.air.folumn.Controller;

import com.air.folumn.Service.CommentService;
import com.air.folumn.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author air
 * @create 2020-11-29-14:05
 */
@Controller
public class LikeController {

    @Autowired
    CommentService commentService;

    @ResponseBody
    @RequestMapping(value="/likecomment",method = RequestMethod.GET)
    public ResultDTO commentlike(String id){
        commentService.updateLikeCount(Integer.valueOf(id));
        return ResultDTO.okOf();
    }
}
