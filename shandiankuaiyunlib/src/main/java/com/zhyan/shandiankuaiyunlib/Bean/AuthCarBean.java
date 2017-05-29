package com.zhyan.shandiankuaiyunlib.Bean;

/**
 * Created by az on 2017/5/19.
 */

public class AuthCarBean {

    /**
     * status : 0
     * message : 资料已提交,待审核
     */

    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
