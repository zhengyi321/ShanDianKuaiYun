package com.example.mynewslayoutlib.Bean;

/**
 * Created by Administrator on 2017/7/3.
 */

public class NewMyRenZhengDetailBean {


    /**
     * status : 0
     * msg :
     * nr : {"id":"129","login_id":"276","name":"测试","id_number":"330382198911165542","address":"测试测试","photo":"2769284.jpg","cards_just":"2769477.jpg","cards_back":"2763792.jpg","path":null,"is_auth":"1","create_time":"2017-06-21 09:16:13","sfzzm":"http://www.lianshiding.com","sfzfm":"http://www.lianshiding.com","scsfz":"http://www.lianshiding.com","yyzz":"http://www.lianshiding.com","jsz":"http://www.lianshiding.com","xsz":"http://www.lianshiding.com","cltu":"","time":"","zt":"0"}
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
         * id : 129
         * login_id : 276
         * name : 测试
         * id_number : 330382198911165542
         * address : 测试测试
         * photo : 2769284.jpg
         * cards_just : 2769477.jpg
         * cards_back : 2763792.jpg
         * path : null
         * is_auth : 0为未审核或者待审核1为通过 2为未通过
         * create_time : 2017-06-21 09:16:13
         * sfzzm : http://www.lianshiding.com
         * sfzfm : http://www.lianshiding.com
         * scsfz : http://www.lianshiding.com
         * yyzz : http://www.lianshiding.com
         * jsz : http://www.lianshiding.com
         * xsz : http://www.lianshiding.com
         * cltu :
         * time :
         * zt : 0
         */

        private String id;
        private String login_id;
        private String name;
        private String id_number;
        private String address;
        private String photo;
        private String cards_just;
        private String cards_back;
        private Object path;
        private String is_auth;
        private String create_time;
        private String sfzzm;
        private String sfzfm;
        private String scsfz;
        private String yyzz;
        private String jsz;
        private String xsz;
        private String cltu;
        private String time;
        private String zt;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId_number() {
            return id_number;
        }

        public void setId_number(String id_number) {
            this.id_number = id_number;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getCards_just() {
            return cards_just;
        }

        public void setCards_just(String cards_just) {
            this.cards_just = cards_just;
        }

        public String getCards_back() {
            return cards_back;
        }

        public void setCards_back(String cards_back) {
            this.cards_back = cards_back;
        }

        public Object getPath() {
            return path;
        }

        public void setPath(Object path) {
            this.path = path;
        }

        public String getIs_auth() {
            return is_auth;
        }

        public void setIs_auth(String is_auth) {
            this.is_auth = is_auth;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getSfzzm() {
            return sfzzm;
        }

        public void setSfzzm(String sfzzm) {
            this.sfzzm = sfzzm;
        }

        public String getSfzfm() {
            return sfzfm;
        }

        public void setSfzfm(String sfzfm) {
            this.sfzfm = sfzfm;
        }

        public String getScsfz() {
            return scsfz;
        }

        public void setScsfz(String scsfz) {
            this.scsfz = scsfz;
        }

        public String getYyzz() {
            return yyzz;
        }

        public void setYyzz(String yyzz) {
            this.yyzz = yyzz;
        }

        public String getJsz() {
            return jsz;
        }

        public void setJsz(String jsz) {
            this.jsz = jsz;
        }

        public String getXsz() {
            return xsz;
        }

        public void setXsz(String xsz) {
            this.xsz = xsz;
        }

        public String getCltu() {
            return cltu;
        }

        public void setCltu(String cltu) {
            this.cltu = cltu;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getZt() {
            return zt;
        }

        public void setZt(String zt) {
            this.zt = zt;
        }
    }
}
