package com.zhyan.shandiankuaiyunlib.Bean;

/**
 * Created by az on 2017/5/20.
 */

public class AuthInFoBean {

    /**
     * status : 0
     * msg : 成功
     * content : {"id":"276","type":0,"drive_status":"0","travel_status":"0","user_is_auth":0,"company_is_auth":0,"car_status":0}
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
         * type : 0
         * drive_status : 0
         * travel_status : 0
         * user_is_auth : 0
         * company_is_auth : 0
         * car_status : 0
         */

        private String id;
        private int type;
        private String drive_status;
        private String travel_status;
        private int user_is_auth;
        private int company_is_auth;
        private int car_status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getDrive_status() {
            return drive_status;
        }

        public void setDrive_status(String drive_status) {
            this.drive_status = drive_status;
        }

        public String getTravel_status() {
            return travel_status;
        }

        public void setTravel_status(String travel_status) {
            this.travel_status = travel_status;
        }

        public int getUser_is_auth() {
            return user_is_auth;
        }

        public void setUser_is_auth(int user_is_auth) {
            this.user_is_auth = user_is_auth;
        }

        public int getCompany_is_auth() {
            return company_is_auth;
        }

        public void setCompany_is_auth(int company_is_auth) {
            this.company_is_auth = company_is_auth;
        }

        public int getCar_status() {
            return car_status;
        }

        public void setCar_status(int car_status) {
            this.car_status = car_status;
        }
    }
}
