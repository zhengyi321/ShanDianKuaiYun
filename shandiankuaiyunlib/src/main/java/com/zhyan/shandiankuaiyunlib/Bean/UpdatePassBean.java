package com.zhyan.shandiankuaiyunlib.Bean;

/**
 * Created by az on 2017/5/22.
 */

public class UpdatePassBean {

    /**
     * status : 0
     * msg : 修改成功
     * password : null
     */

    private int status;
    private String msg;
    private Object password;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }
}
