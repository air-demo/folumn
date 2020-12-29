package com.air.folumn.Service;

import com.air.folumn.dto.CommentCreateDTO;
import com.air.folumn.dto.CommentDTO;
import com.air.folumn.entity.Comment;
import com.air.folumn.entity.User;
import com.air.folumn.enums.CommentTypeEnum;

import java.util.List;

/**
 * @author air
 * @create 2020-11-23-22:10
 */
public interface CommentService {

    void insert(CommentCreateDTO commentCreateDTO, User user);

    List<CommentDTO> listByTargetId(Integer id, CommentTypeEnum type);

    void updateLikeCount(Integer id);
}
