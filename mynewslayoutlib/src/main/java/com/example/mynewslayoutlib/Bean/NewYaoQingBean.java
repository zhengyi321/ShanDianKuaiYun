package com.example.mynewslayoutlib.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 */

public class NewYaoQingBean {


    /**
     * renshu : 1
     * jifen : 100
     * nr : [{"name":"zz2","jifen":"100","time":"2017-06-12"}]
     */

    private String renshu;
    private String jifen;
    private List<NrBean> nr;

    public String getRenshu() {
        return renshu;
    }

    public void setRenshu(String renshu) {
        this.renshu = renshu;
    }

    public String getJifen() {
        return jifen;
    }

    public void setJifen(String jifen) {
        this.jifen = jifen;
    }

    public List<NrBean> getNr() {
        return nr;
    }

    public void setNr(List<NrBean> nr) {
        this.nr = nr;
    }

    public static class NrBean {
        /**
         * name : zz2
         * jifen : 100
         * time : 2017-06-12
         */

        private String name;
        private String jifen;
        private String time;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getJifen() {
            return jifen;
        }

        public void setJifen(String jifen) {
            this.jifen = jifen;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
