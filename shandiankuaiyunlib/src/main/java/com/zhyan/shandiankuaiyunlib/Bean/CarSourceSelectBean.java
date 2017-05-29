package com.zhyan.shandiankuaiyunlib.Bean;

import java.util.List;

/**
 * Created by az on 2017/5/17.
 */

public class CarSourceSelectBean {

    /**
     * status : 0
     * msg : 成功
     * content : [{"id":"9658","car_title":"忘","address_set":"乐清","address_out":"邹城","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/22.jpg","iphone":"13616606573","type":2,"create_time":"02-25"},{"id":"8565","car_title":"乐清市柳市鸿盛集装箱中转服务站","address_set":"乐清","address_out":"和田","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1867094.jpg","iphone":"13867777113","type":2,"create_time":"01-12"},{"id":"8560","car_title":"乐清市柳市存央货运站","address_set":"乐清","address_out":"北京","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1862347.jpg","iphone":"13706775199","type":2,"create_time":"01-12"},{"id":"8559","car_title":"乐清市柳市旺达运输信息服务部","address_set":"乐清","address_out":"北京","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1863017.jpg","iphone":"15057518877","type":2,"create_time":"01-12"},{"id":"8557","car_title":"乐清市柳市赵丹货运站","address_set":"乐清","address_out":"乌鲁木齐","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1861121.jpg","iphone":"13706772221","type":2,"create_time":"01-12"},{"id":"8555","car_title":"乐清市联运服务有限公司","address_set":"乐清","address_out":"西安","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1868430.jpg","iphone":"13505877558","type":2,"create_time":"01-12"},{"id":"8551","car_title":"乐清市柳市宏丰联运服务部","address_set":"乐清","address_out":"北京","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1861332.jpg","iphone":"13706777126","type":2,"create_time":"01-12"},{"id":"8550","car_title":"乐清市虹东物流快运有限公司","address_set":"乐清","address_out":"北京","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1869828.jpg","iphone":"13505878092","type":2,"create_time":"01-12"},{"id":"8548","car_title":"乐清市安之捷货物托运站","address_set":"乐清","address_out":"北京","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1866189.jpg","iphone":"13868371358","type":2,"create_time":"01-12"},{"id":"8546","car_title":"乐清市科兴物流","address_set":"乐清","address_out":"芜湖","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1865972.jpg","iphone":"13806608369","type":2,"create_time":"01-12"},{"id":"8544","car_title":"乐清市虹桥鸣达货运站","address_set":"乐清","address_out":"北京","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1863221.jpg","iphone":"13868752108","type":2,"create_time":"01-12"},{"id":"8542","car_title":"乐清市一路顺货运站","address_set":"乐清","address_out":"北京","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1861682.jpg","iphone":"13868712060","type":2,"create_time":"01-12"},{"id":"8537","car_title":"乐清市虹桥天鹰托运站","address_set":"乐清","address_out":"北京","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1867664.jpg","iphone":"13325987715","type":2,"create_time":"01-12"},{"id":"8529","car_title":"乐清市合兴货运站","address_set":"乐清","address_out":"北京","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1862137.jpg","iphone":"13806860866","type":2,"create_time":"01-12"},{"id":"8526","car_title":"乐清市金云货物运输服务站","address_set":"乐清","address_out":"北京","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1864906.jpg","iphone":"15058776331","type":2,"create_time":"01-12"},{"id":"8521","car_title":"乐清市柳市路均货运站","address_set":"乐清","address_out":"北京","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1861217.jpg","iphone":"18066255011","type":2,"create_time":"01-12"},{"id":"8514","car_title":"乐清市上嘉物流有限公司","address_set":"乐清","address_out":"北京","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/22.jpg","iphone":"13587712632","type":2,"create_time":"01-12"},{"id":"8243","car_title":"乐清市柳市建伍托运站","address_set":"乐清","address_out":"江苏","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/22.jpg","iphone":"13305873377","type":2,"create_time":"01-10"},{"id":"8242","car_title":"乐清市柳市建松托运部","address_set":"乐清","address_out":"西安","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/22.jpg","iphone":"0577-62725058","type":2,"create_time":"01-10"},{"id":"8241","car_title":"乐清市柳市南丰托运站","address_set":"乐清","address_out":"菏泽","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/22.jpg","iphone":"13655772777","type":2,"create_time":"01-10"}]
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
         * id : 9658
         * car_title : 忘
         * address_set : 乐清
         * address_out : 邹城
         * img1 : http://www.lianshiding.com/Public/asset/auth/car_info/22.jpg
         * iphone : 13616606573
         * type : 2
         * create_time : 02-25
         */

        private String id;
        private String car_title;
        private String address_set;
        private String address_out;
        private String img1;
        private String iphone;
        private int type;
        private String create_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCar_title() {
            return car_title;
        }

        public void setCar_title(String car_title) {
            this.car_title = car_title;
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

        public String getImg1() {
            return img1;
        }

        public void setImg1(String img1) {
            this.img1 = img1;
        }

        public String getIphone() {
            return iphone;
        }

        public void setIphone(String iphone) {
            this.iphone = iphone;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
