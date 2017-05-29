package com.zhyan.shandiankuaiyunlib.Bean;

import java.util.List;

/**
 * Created by az on 2017/5/23.
 */

public class NearByDriverBean {

    /**
     * status : 0
     * msg :
     * nr : [{"name":"隐","cid":"9658","lat":28.122410443388,"lng":120.98156795439},{"name":"老六","cid":"9871","lat":28.122226643384,"lng":120.98145916696}]
     */

    private String status;
    private String msg;
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

    public List<NrBean> getNr() {
        return nr;
    }

    public void setNr(List<NrBean> nr) {
        this.nr = nr;
    }

    public static class NrBean {
        /**
         * name : 隐
         * cid : 9658
         * lat : 28.122410443388
         * lng : 120.98156795439
         */

        private String name;
        private String cid;
        private double lat;
        private double lng;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }
    }
}
