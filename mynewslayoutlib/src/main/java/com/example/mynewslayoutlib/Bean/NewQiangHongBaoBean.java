package com.example.mynewslayoutlib.Bean;

/**
 * Created by Administrator on 2017/6/29.
 */

public class NewQiangHongBaoBean {

    /**
     * status : 0
     * msg : 恭喜您领取成功！
     * nr : {"jine":"0.09"}
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
         * jine : 0.09
         */

        private String jine;

        public String getJine() {
            return jine;
        }

        public void setJine(String jine) {
            this.jine = jine;
        }
    }
}
