package com.air.folumn.entity;

/**
 * @author air
 * @create 2020-11-28-13:24
 */
public class Notification {
    private Integer id;
    private Integer notifier;
    private Integer receiver;
    private Integer outerId;
    private Integer type;
    private Integer status;
    private Long gmtCreate;
    private String notifierName;
    private String outerTitle;

    public String getOuterTitle() {
        return outerTitle;
    }

    public void setOuterTitle(String outerTitle) {
        this.outerTitle = outerTitle;
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

    public Integer getNotifier() {
        return notifier;
    }

    public void setNotifier(Integer notifier) {
        this.notifier = notifier;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public Integer getOuterId() {
        return outerId;
    }

    public void setOuterId(Integer outerId) {
        this.outerId = outerId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
}
