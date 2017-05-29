package com.zhyan.shandiankuaiyunlib.Bean;

/**
 * Created by az on 2017/5/13.
 */

public class CarSourceBean {
    /**
     * status : 1000
     * msg : 上行参数错误
     * errorList : {"key":"login_id","value":"null"}
     */

    private String status;
    private String msg;
    private ErrorListBean errorList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ErrorListBean getErrorList() {
        return errorList;
    }

    public void setErrorList(ErrorListBean errorList) {
        this.errorList = errorList;
    }

    public static class ErrorListBean {
        /**
         * key : login_id
         * value : null
         */

        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
