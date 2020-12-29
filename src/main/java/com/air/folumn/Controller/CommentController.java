package com.air.folumn.Controller;

import com.air.folumn.Service.CommentService;
import com.air.folumn.dto.CommentCreateDTO;
import com.air.folumn.dto.CommentDTO;
import com.air.folumn.dto.ResultDTO;
import com.air.folumn.entity.Comment;
import com.air.folumn.entity.User;
import com.air.folumn.enums.CommentTypeEnum;
import com.air.folumn.enums.CustomSizeErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.management.ValueExp;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author air
 * @create 2020-11-23-22:01
 */
@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @ResponseBody
    @PostMapping(value = "/comment")
    public Object post(CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        if(user==null){
            return ResultDTO.errorOf(CustomSizeErrorCode.NO_LOGIN);
        }
        if(commentCreateDTO==null||commentCreateDTO.getContent().trim()==null){
            return ResultDTO.errorOf(CustomSizeErrorCode.CONTENT_IS_EMPTY);
        }
        commentService.insert(commentCreateDTO,user);
        return ResultDTO.okOf();

    }

    @ResponseBody
    @GetMapping(value = "/comment/{id}")
    public ResultDTO comments(@PathVariable(name = "id")Integer id){
        List<CommentDTO> commentDTOS=commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }



}
