package com.air.folumn.enums;

import com.air.folumn.Exception.CustomSizeCode;

/**
 * @author air
 * @create 2020-11-23-11:13
 */
public enum CustomSizeErrorCode implements CustomSizeCode {

    QUESTION_NOT_FOUND(2019,"你找的问题不存在了，要不换一个试试"),
    TARGET_PARAM_NOT_FOUND(2020,"未选中任何问题或评论进行回复"),
    NO_LOGIN(2021,"当前操作需要登陆，请重试"),
    SYSTEM_ERROR(2022,"服务器抽风了，要不然一会再来试试"),
    TYPE_PARAM_WRONG(2023,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2024,"回复的评论不存在了，要不换一个试试？"),
    CONTENT_IS_EMPTY(2025,"输入内容不能为空"),
    READ_NOTIFICATION_FAIL(2024,"禁止读别人的信息"),
    NOTIFICATION_NOT_FOUND(2025,"消息不翼而飞了")
    ;
    private Integer code;
    private String message;


    @Override
    public String getMessage(){
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    CustomSizeErrorCode(Integer code,String message){
        this.code=code;
        this.message=message;

    }
}
