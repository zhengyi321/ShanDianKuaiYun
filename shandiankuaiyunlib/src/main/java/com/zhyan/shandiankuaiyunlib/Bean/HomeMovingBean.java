package com.zhyan.shandiankuaiyunlib.Bean;

import java.util.List;

/**
 * Created by az on 2017/5/18.
 */

public class HomeMovingBean {

    /**
     * status : 0
     * msg : 成功
     * content : {"car_info":[{"id":"2061","type_name":"居民搬家","car_title":"振东物流、家具装拆、维护","address_set":"磐石","address_out":"蒲岐","people":"刘师傅","iphone":"18222260767","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/22.jpg","create_time":"11-29"},{"id":"1802","type_name":"搬家搬场","car_title":"浙江长途搬家,家电托运,异地搬迁,整车零担大件运输","address_set":"白石","address_out":"象阳","people":"荣德刚","iphone":"13521256095","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/22.jpg","create_time":"11-26"},{"id":"1801","type_name":"公司搬家","car_title":"北京承接全国公路运输 长途搬家 行李托运 仓储物流","address_set":"乐成","address_out":"乐成","people":"熊经理","iphone":"18618482322","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/22.jpg","create_time":"11-26"},{"id":"1800","type_name":"居民搬家","car_title":"全国各地整车零担大件运输长途搬家物品货运汽车托运","address_set":"乐成","address_out":"象阳","people":"阿宇","iphone":"17316297331","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/22.jpg","create_time":"11-26"},{"id":"1799","type_name":"公司搬家","car_title":"货运物流 轿车托运 长途搬家 整车零担","address_set":"乐成","address_out":"象阳","people":"金雨物流","iphone":"13371647979","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/22.jpg","create_time":"11-26"},{"id":"1798","type_name":"居民搬家","car_title":"专业长途搬家,汽车托运,整车零担、大件运输、专线等","address_set":"乐清","address_out":"文成","people":"刘经理","iphone":"18500799978","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/22.jpg","create_time":"11-26"}]}
     */

    private int status;
    private String msg;
    private ContentBean content;

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

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        private List<CarInfoBean> car_info;

        public List<CarInfoBean> getCar_info() {
            return car_info;
        }

        public void setCar_info(List<CarInfoBean> car_info) {
            this.car_info = car_info;
        }

        public static class CarInfoBean {
            /**
             * id : 2061
             * type_name : 居民搬家
             * car_title : 振东物流、家具装拆、维护
             * address_set : 磐石
             * address_out : 蒲岐
             * people : 刘师傅
             * iphone : 18222260767
             * img1 : http://www.lianshiding.com/Public/asset/auth/car_info/22.jpg
             * create_time : 11-29
             */

            private String id;
            private String type_name;
            private String car_title;
            private String address_set;
            private String address_out;
            private String people;
            private String iphone;
            private String img1;
            private String create_time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
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

            public String getPeople() {
                return people;
            }

            public void setPeople(String people) {
                this.people = people;
            }

            public String getIphone() {
                return iphone;
            }

            public void setIphone(String iphone) {
                this.iphone = iphone;
            }

            public String getImg1() {
                return img1;
            }

            public void setImg1(String img1) {
                this.img1 = img1;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }
    }
}
