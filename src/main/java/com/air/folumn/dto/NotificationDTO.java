package com.air.folumn.dto;

import com.air.folumn.entity.User;

/**
 * @author air
 * @create 2020-11-28-14:16
 */
public class NotificationDTO {
    private Integer id;
    private Integer status;
    private Long gmtCreate;
    private Integer notifier;
    private String notifierName;
    private String outerTitle;
    private Integer outerId;
    private String typeName;
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOuterId() {
        return outerId;
    }

    public void setOuterId(Integer outerId) {
        this.outerId = outerId;
    }

    public Integer getNotifier() {
        return notifier;
    }

    public void setNotifier(Integer notifier) {
        this.notifier = notifier;
    }

    public String getNotifierName() {
        return notifierName;
    }

    public void setNotifierName(String notifierName) {
        this.notifierName = notifierName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getOuterTitle() {
        return outerTitle;
    }

    public void setOuterTitle(String outerTitle) {
        this.outerTitle = outerTitle;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
