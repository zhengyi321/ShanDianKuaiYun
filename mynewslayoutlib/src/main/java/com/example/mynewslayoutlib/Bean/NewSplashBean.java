package com.example.mynewslayoutlib.Bean;

/**
 * Created by Administrator on 2017/7/3.
 */

public class NewSplashBean {

    /**
     * status : 0
     * msg :
     * nr : {"id":"6","img":"http://www.lianshiding.com/Uploads/2017-03-03/58b8e0518d24d.png","list":"2","type":"1","infoid":null,"url":"http://www.lianshiding.com/xiazai/","create_time":"2017-07-03 17:03:52","miaoshu":"2","fwsj":"1499079212"}
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
         * id : 6
         * img : http://www.lianshiding.com/Uploads/2017-03-03/58b8e0518d24d.png
         * list : 2
         * type : 1
         * infoid : null
         * url : http://www.lianshiding.com/xiazai/
         * create_time : 2017-07-03 17:03:52
         * miaoshu : 2
         * fwsj : 1499079212
         */

        private String id;
        private String img;
        private String list;
        private String type;
        private Object infoid;
        private String url;
        private String create_time;
        private String miaoshu;
        private String fwsj;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getList() {
            return list;
        }

        public void setList(String list) {
            this.list = list;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getInfoid() {
            return infoid;
        }

        public void setInfoid(Object infoid) {
            this.infoid = infoid;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getMiaoshu() {
            return miaoshu;
        }

        public void setMiaoshu(String miaoshu) {
            this.miaoshu = miaoshu;
        }

        public String getFwsj() {
            return fwsj;
        }

        public void setFwsj(String fwsj) {
            this.fwsj = fwsj;
        }
    }
}
