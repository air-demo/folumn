package com.air.folumn.enums;

/**
 * @author air
 * @create 2020-11-28-13:39
 */
public enum NotificationStatusEnum {
    UNREAD(0),
    READ(1)
    ;
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
