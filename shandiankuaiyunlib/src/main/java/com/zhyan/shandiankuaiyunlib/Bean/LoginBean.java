package com.zhyan.shandiankuaiyunlib.Bean;

/**
 * Created by az on 2017/4/26.
 */

public class LoginBean {


    /**
     * status : 0
     * msg : 成功
     * content : {"id":"276","name":"zhyan","nickename":null,"mobile":"18767775244","password":"25f9e794323b453885f5181f1b624d0b","one_code":"273485","sex":"1","email":null,"wei_code":null,"qq_code":null,"address":null,"type":"0"}
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
         * name : zhyan
         * nickename : null
         * mobile : 18767775244
         * password : 25f9e794323b453885f5181f1b624d0b
         * one_code : 273485
         * sex : 1
         * email : null
         * wei_code : null
         * qq_code : null
         * address : null
         * type : 0
         */

        private String id;
        private String name;
        private Object nickename;
        private String mobile;
        private String password;
        private String one_code;
        private String sex;
        private Object email;
        private Object wei_code;
        private Object qq_code;
        private Object address;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getNickename() {
            return nickename;
        }

        public void setNickename(Object nickename) {
            this.nickename = nickename;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getOne_code() {
            return one_code;
        }

        public void setOne_code(String one_code) {
            this.one_code = one_code;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getWei_code() {
            return wei_code;
        }

        public void setWei_code(Object wei_code) {
            this.wei_code = wei_code;
        }

        public Object getQq_code() {
            return qq_code;
        }

        public void setQq_code(Object qq_code) {
            this.qq_code = qq_code;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
