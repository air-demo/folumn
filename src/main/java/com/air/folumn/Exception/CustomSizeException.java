package com.air.folumn.Exception;

/**
 * @author air
 * @create 2020-11-23-10:55
 */
public class CustomSizeException extends RuntimeException{
    private String message;
    private Integer code;

    public CustomSizeException(CustomSizeCode errorCode){
        this.code=errorCode.getCode();
        this.message=errorCode.getMessage();
    }


    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
