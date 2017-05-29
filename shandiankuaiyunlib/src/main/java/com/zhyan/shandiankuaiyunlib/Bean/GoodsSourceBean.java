package com.zhyan.shandiankuaiyunlib.Bean;

import java.util.List;

/**
 * Created by az on 2017/5/18.
 */

public class GoodsSourceBean {

    /**
     * status : 0
     * msg : 成功
     * content : [{"id":"578","login_id":"327","good_name":"洗发水","address_set":"乐清","address_out":"苏家屯","iphone":"13343343443","create_time":"05-16","img1":"http://www.lianshiding.com/Public/asset/auth/good_info/22.jpg","type":1},{"id":"572","login_id":"197","good_name":"1111","address_set":"乐清","address_out":"北京","iphone":"13611022117","create_time":"03-08","img1":"http://www.lianshiding.com/Public/asset/auth/good_info/22.jpg","type":1},{"id":"448","login_id":"111","good_name":"空集装箱","address_set":"乐清","address_out":"洞头","iphone":"18204806999","create_time":"11-29","img1":"http://www.lianshiding.com/Public/asset/auth/good_info/22.jpg","type":1}]
     */

    private int status;
    private String msg;
    private List<ContentBean> content;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * id : 578
         * login_id : 327
         * good_name : 洗发水
         * address_set : 乐清
         * address_out : 苏家屯
         * iphone : 13343343443
         * create_time : 05-16
         * img1 : http://www.lianshiding.com/Public/asset/auth/good_info/22.jpg
         * type : 1
         */

        private String id;
        private String login_id;
        private String good_name;
        private String address_set;
        private String address_out;
        private String iphone;
        private String create_time;
        private String img1;
        private int type;

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

        public String getGood_name() {
            return good_name;
        }

        public void setGood_name(String good_name) {
            this.good_name = good_name;
        }

        public String getAddress_set() {
            return address_set;
        }

        public void setAddress_set(String address_set) {
            this.address_set = address_set;
        }

        public String getAddress_out() {
            return address_out;
        }

        public void setAddress_out(String address_out) {
            this.address_out = address_out;
        }

        public String getIphone() {
            return iphone;
        }

        public void setIphone(String iphone) {
            this.iphone = iphone;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getImg1() {
            return img1;
        }

        public void setImg1(String img1) {
            this.img1 = img1;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
