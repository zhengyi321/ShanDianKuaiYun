package com.example.mynewslayoutlib.Bean;

/**
 * Created by Administrator on 2017/6/20.
 */

public class JiFenBean {

    /**
     * status : 0
     * msg :
     * nr : {"jifen":"525","jine":5.25,"name":"","zhanghao":""}
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
         * jifen : 525
         * jine : 5.25
         * name :
         * zhanghao :
         */

        private String jifen;
        private double jine;
        private String name;
        private String zhanghao;

        public String getJifen() {
            return jifen;
        }

        public void setJifen(String jifen) {
            this.jifen = jifen;
        }

        public double getJine() {
            return jine;
        }

        public void setJine(double jine) {
            this.jine = jine;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getZhanghao() {
            return zhanghao;
        }

        public void setZhanghao(String zhanghao) {
            this.zhanghao = zhanghao;
        }
    }
}
