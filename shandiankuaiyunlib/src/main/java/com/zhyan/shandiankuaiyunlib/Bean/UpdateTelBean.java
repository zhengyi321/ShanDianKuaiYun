package com.zhyan.shandiankuaiyunlib.Bean;

/**
 * Created by az on 2017/5/22.
 */

public class UpdateTelBean {

    /**
     * status : 0
     * msg : 成功
     * content : {"id":"276","mobile":"18767775244"}
     */

    private int status;
    private String msg;
    private ContentBean content;

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

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * id : 276
         * mobile : 18767775244
         */

        private String id;
        private String mobile;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
