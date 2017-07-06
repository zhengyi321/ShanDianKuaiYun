package com.example.mynewslayoutlib.Bean;

/**
 * Created by Administrator on 2017/7/5.
 */

public class NewTuiSongStatusBean {


    /**
     * status : 0
     * msg :
     * nr : {"tszt":"1"}
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
         * tszt : 1
         */

        private String tszt;

        public String getTszt() {
            return tszt;
        }

        public void setTszt(String tszt) {
            this.tszt = tszt;
        }
    }
}
