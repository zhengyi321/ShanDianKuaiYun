package com.example.mynewslayoutlib.Bean;

import java.util.List;

/**
 * Created by zhyan on 2017/6/12.
 */

public class NewBaoJiaListBean {


    /**
     * status : 0
     * msg :
     * nr : {"ys":"1","list":[{"name":"zz2","mobile":"13868737922","jiage":"111","czid":"383","touxiang":"/Uploads/2017-06-12/3831497274783ktoo.jpg"}]}
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
         * ys : 1
         * list : [{"name":"zz2","mobile":"13868737922","jiage":"111","czid":"383","touxiang":"/Uploads/2017-06-12/3831497274783ktoo.jpg"}]
         */

        private String ys;
        private List<ListBean> list;

        public String getYs() {
            return ys;
        }

        public void setYs(String ys) {
            this.ys = ys;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * name : zz2
             * mobile : 13868737922
             * jiage : 111
             * czid : 383
             * touxiang : /Uploads/2017-06-12/3831497274783ktoo.jpg
             */

            private String name;
            private String mobile;
            private String jiage;
            private String czid;
            private String touxiang;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getJiage() {
                return jiage;
            }

            public void setJiage(String jiage) {
                this.jiage = jiage;
            }

            public String getCzid() {
                return czid;
            }

            public void setCzid(String czid) {
                this.czid = czid;
            }

            public String getTouxiang() {
                return touxiang;
            }

            public void setTouxiang(String touxiang) {
                this.touxiang = touxiang;
            }
        }
    }
}
