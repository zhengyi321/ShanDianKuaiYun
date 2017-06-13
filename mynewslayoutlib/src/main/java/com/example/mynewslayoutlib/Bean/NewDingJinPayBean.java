package com.example.mynewslayoutlib.Bean;

/**
 * Created by Administrator on 2017/6/13.
 */

public class NewDingJinPayBean {

    /**
     * status : 0
     * msg :
     * nr : {"zjine":"111","dingjin":22.2}
     */

    private String status;
    private String msg;
    private NrBean nr;

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

    public NrBean getNr() {
        return nr;
    }

    public void setNr(NrBean nr) {
        this.nr = nr;
    }

    public static class NrBean {
        /**
         * zjine : 111
         * dingjin : 22.2
         */

        private String zjine;
        private double dingjin;

        public String getZjine() {
            return zjine;
        }

        public void setZjine(String zjine) {
            this.zjine = zjine;
        }

        public double getDingjin() {
            return dingjin;
        }

        public void setDingjin(double dingjin) {
            this.dingjin = dingjin;
        }
    }
}
