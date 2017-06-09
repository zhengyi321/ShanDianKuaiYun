package com.example.mynewslayoutlib.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 */

public class NewHuoYuanListBean {


    /**
     * status : 0
     * msg :
     * nr : {"ys":0,"list":[]}
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
         * ys : 0
         * list : []
         */

        private int ys;
        private List<?> list;

        public int getYs() {
            return ys;
        }

        public void setYs(int ys) {
            this.ys = ys;
        }

        public List<?> getList() {
            return list;
        }

        public void setList(List<?> list) {
            this.list = list;
        }
    }
}
