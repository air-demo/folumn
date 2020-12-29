package com.air.folumn.dto;

import com.air.folumn.entity.User;

/**
 * @author air
 * @create 2020-11-23-22:20
 */
public class CommentCreateDTO {
    private Integer parentId;
    private Integer type;
    private String content;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
