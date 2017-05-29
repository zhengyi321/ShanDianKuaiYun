package com.zhyan.shandiankuaiyunlib.Bean;

import java.util.List;

/**
 * Created by az on 2017/5/10.
 */

public class MyCarSourceBean {
    /**
     * status : 0
     * msg : 成功
     * content : [{"id":"9791","login_id":"217","type_name":"租车货运","car_title":"2017","set_province_id":"1","set_city_id":"1341","set_area_id":"42","out_province_id":"8","out_city_id":"1155","out_area_id":"1018","set_tid":null,"out_tid":null,"address_set":"迎宾路","address_out":"庐江","iphone":"1008611","people":"奥莉给","address":"浙江省温州市乐清市双雁路398号","car_type":"其他车型","car_lange":"20米","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/22.jpg","img2":"http://www.lianshiding.com/Public/asset/auth/car_info/23.png","img3":"0","img4":"0","img5":"0","img6":"0","img7":"0","img8":"0","is_pass":"1","status":"0","type":"0","content":"0","num":"1","create_time":"2017-05-10","city_name":"拱墅"},{"id":"9761","login_id":"217","type_name":"租车货运","car_title":"419","set_province_id":"7","set_city_id":"1531","set_area_id":"0","out_province_id":"1","out_city_id":"1340","out_area_id":"0","set_tid":null,"out_tid":null,"address_set":"浙江","address_out":"北京","iphone":"0225","people":"里","address":"浙江省温州市乐清市双雁路398号","car_type":"0","car_lange":"0","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/22.jpg","img2":"http://www.lianshiding.com/Public/asset/auth/car_info/23.png","img3":"0","img4":"0","img5":"0","img6":"0","img7":"0","img8":"0","is_pass":"1","status":"0","type":"0","content":"0","num":"2","create_time":"2017-04-19","city_name":null},{"id":"9680","login_id":"217","type_name":"租车货运","car_title":"46","set_province_id":"1","set_city_id":"1341","set_area_id":"42","out_province_id":"2","out_city_id":"1356","out_area_id":"2949","set_tid":null,"out_tid":null,"address_set":"迎宾路","address_out":"青浦工业园区","iphone":"1008611","people":"阿鲁","address":"浙江省温州市乐清市旭阳路35号","car_type":"0","car_lange":"0","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/22.jpg","img2":"http://www.lianshiding.com/Public/asset/auth/car_info/23.png","img3":"0","img4":"0","img5":"0","img6":"0","img7":"0","img8":"0","is_pass":"1","status":"0","type":"0","content":"0","num":"21","create_time":"2017-04-06","city_name":"拱墅"}]
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
         * id : 9791
         * login_id : 217
         * type_name : 租车货运
         * car_title : 2017
         * set_province_id : 1
         * set_city_id : 1341
         * set_area_id : 42
         * out_province_id : 8
         * out_city_id : 1155
         * out_area_id : 1018
         * set_tid : null
         * out_tid : null
         * address_set : 迎宾路
         * address_out : 庐江
         * iphone : 1008611
         * people : 奥莉给
         * address : 浙江省温州市乐清市双雁路398号
         * car_type : 其他车型
         * car_lange : 20米
         * img1 : http://www.lianshiding.com/Public/asset/auth/car_info/22.jpg
         * img2 : http://www.lianshiding.com/Public/asset/auth/car_info/23.png
         * img3 : 0
         * img4 : 0
         * img5 : 0
         * img6 : 0
         * img7 : 0
         * img8 : 0
         * is_pass : 1
         * status : 0
         * type : 0
         * content : 0
         * num : 1
         * create_time : 2017-05-10
         * city_name : 拱墅
         */

        private String id;
        private String login_id;
        private String type_name;
        private String car_title;
        private String set_province_id;
        private String set_city_id;
        private String set_area_id;
        private String out_province_id;
        private String out_city_id;
        private String out_area_id;
        private Object set_tid;
        private Object out_tid;
        private String address_set;
        private String address_out;
        private String iphone;
        private String people;
        private String address;
        private String car_type;
        private String car_lange;
        private String img1;
        private String img2;
        private String img3;
        private String img4;
        private String img5;
        private String img6;
        private String img7;
        private String img8;
        private String is_pass;
        private String status;
        private String type;
        private String content;
        private String num;
        private String create_time;
        private String city_name;

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

        public String getSet_province_id() {
            return set_province_id;
        }

        public void setSet_province_id(String set_province_id) {
            this.set_province_id = set_province_id;
        }

        public String getSet_city_id() {
            return set_city_id;
        }

        public void setSet_city_id(String set_city_id) {
            this.set_city_id = set_city_id;
        }

        public String getSet_area_id() {
            return set_area_id;
        }

        public void setSet_area_id(String set_area_id) {
            this.set_area_id = set_area_id;
        }

        public String getOut_province_id() {
            return out_province_id;
        }

        public void setOut_province_id(String out_province_id) {
            this.out_province_id = out_province_id;
        }

        public String getOut_city_id() {
            return out_city_id;
        }

        public void setOut_city_id(String out_city_id) {
            this.out_city_id = out_city_id;
        }

        public String getOut_area_id() {
            return out_area_id;
        }

        public void setOut_area_id(String out_area_id) {
            this.out_area_id = out_area_id;
        }

        public Object getSet_tid() {
            return set_tid;
        }

        public void setSet_tid(Object set_tid) {
            this.set_tid = set_tid;
        }

        public Object getOut_tid() {
            return out_tid;
        }

        public void setOut_tid(Object out_tid) {
            this.out_tid = out_tid;
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

        public String getPeople() {
            return people;
        }

        public void setPeople(String people) {
            this.people = people;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCar_type() {
            return car_type;
        }

        public void setCar_type(String car_type) {
            this.car_type = car_type;
        }

        public String getCar_lange() {
            return car_lange;
        }

        public void setCar_lange(String car_lange) {
            this.car_lange = car_lange;
        }

        public String getImg1() {
            return img1;
        }

        public void setImg1(String img1) {
            this.img1 = img1;
        }

        public String getImg2() {
            return img2;
        }

        public void setImg2(String img2) {
            this.img2 = img2;
        }

        public String getImg3() {
            return img3;
        }

        public void setImg3(String img3) {
            this.img3 = img3;
        }

        public String getImg4() {
            return img4;
        }

        public void setImg4(String img4) {
            this.img4 = img4;
        }

        public String getImg5() {
            return img5;
        }

        public void setImg5(String img5) {
            this.img5 = img5;
        }

        public String getImg6() {
            return img6;
        }

        public void setImg6(String img6) {
            this.img6 = img6;
        }

        public String getImg7() {
            return img7;
        }

        public void setImg7(String img7) {
            this.img7 = img7;
        }

        public String getImg8() {
            return img8;
        }

        public void setImg8(String img8) {
            this.img8 = img8;
        }

        public String getIs_pass() {
            return is_pass;
        }

        public void setIs_pass(String is_pass) {
            this.is_pass = is_pass;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }
    }
}
