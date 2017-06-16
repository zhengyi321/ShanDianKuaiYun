package com.example.mynewslayoutlib.Bean;

import java.util.List;

/**
 * 收支明细
 * Created by Administrator on 2017/6/16.
 */

public class NewMyWalletHistoryListBean {


    /**
     * status : 0
     * msg :
     * zhjine : 1.55
     * nr : [{"id":"60","login_id":"276","lx":"2","jiage":"-1","time":""},{"id":"57","login_id":"276","lx":"1","jiage":"+1","time":""},{"id":"54","login_id":"276","lx":"2","jiage":"-1","time":""},{"id":"52","login_id":"276","lx":"2","jiage":"-1","time":""},{"id":"50","login_id":"276","lx":"2","jiage":"-1","time":""},{"id":"48","login_id":"276","lx":"2","jiage":"-1","time":""},{"id":"46","login_id":"276","lx":"2","jiage":"-1","time":""},{"id":"35","login_id":"276","lx":"4","jiage":"+0.08","time":"2017-06-08"},{"id":"9","login_id":"276","lx":"3","jiage":"-1","time":"2017-05-24"},{"id":"8","login_id":"276","lx":"3","jiage":"-1","time":"2017-05-24"}]
     */

    private String status;
    private String msg;
    private String zhjine;
    private List<NrBean> nr;

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

    public String getZhjine() {
        return zhjine;
    }

    public void setZhjine(String zhjine) {
        this.zhjine = zhjine;
    }

    public List<NrBean> getNr() {
        return nr;
    }

    public void setNr(List<NrBean> nr) {
        this.nr = nr;
    }

    public static class NrBean {
        /**
         * id : 60
         * login_id : 276
         * lx : 2
         * jiage : -1
         * time :
         */

        private String id;
        private String login_id;
        private String lx;
        private String jiage;
        private String time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLogin_id() {
            return login_id;
        }

        public void setLogin_id(String login_id) {
            this.login_id = login_id;
        }

        public String getLx() {
            return lx;
        }

        public void setLx(String lx) {
            this.lx = lx;
        }

        public String getJiage() {
            return jiage;
        }

        public void setJiage(String jiage) {
            this.jiage = jiage;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
