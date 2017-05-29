package com.zhyan.shandiankuaiyunlib.Bean;

import java.util.List;

/**
 * Created by az on 2017/5/17.
 */

public class CarSourceDetailBean {


    /**
     * status : 0
     * msg : 成功
     * content : {"list":[{"id":"9855","name":"123","car_title":"北京-重庆","type_name":"专线物流","address_set":"北京","address_out":"重庆","iphone":"15920015918","people":"何先生","address":"0","car_type":"0","car_lange":"0","type":2,"collect":2,"count":"0","num":"7","auth":0,"lng":"116.39564503788","lat":"39.92998577808","login_id":"290","content":"0","create_time":"05-12"}],"img":[{"img2":"http://www.lianshiding.com/Public/asset/auth/car_info/23.png"}]}
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
        private List<ListBean> list;
        private List<ImgBean> img;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<ImgBean> getImg() {
            return img;
        }

        public void setImg(List<ImgBean> img) {
            this.img = img;
        }

        public static class ListBean {
            /**
             * id : 9855
             * name : 123
             * car_title : 北京-重庆
             * type_name : 专线物流
             * address_set : 北京
             * address_out : 重庆
             * iphone : 15920015918
             * people : 何先生
             * address : 0
             * car_type : 0
             * car_lange : 0
             * type : 2
             * collect : 2
             * count : 0
             * num : 7
             * auth : 0
             * lng : 116.39564503788
             * lat : 39.92998577808
             * login_id : 290
             * content : 0
             * create_time : 05-12
             */

            private String id;
            private String name;
            private String car_title;
            private String type_name;
            private String address_set;
            private String address_out;
            private String iphone;
            private String people;
            private String address;
            private String car_type;
            private String car_lange;
            private int type;
            private int collect;
            private String count;
            private String num;
            private int auth;
            private String lng;
            private String lat;
            private String login_id;
            private String content;
            private String create_time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCar_title() {
                return car_title;
            }

            public void setCar_title(String car_title) {
                this.car_title = car_title;
            }

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
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

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getCollect() {
                return collect;
            }

            public void setCollect(int collect) {
                this.collect = collect;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public int getAuth() {
                return auth;
            }

            public void setAuth(int auth) {
                this.auth = auth;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLogin_id() {
                return login_id;
            }

            public void setLogin_id(String login_id) {
                this.login_id = login_id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }

        public static class ImgBean {
            /**
             * img2 : http://www.lianshiding.com/Public/asset/auth/car_info/23.png
             */

            private String img2;

            public String getImg2() {
                return img2;
            }

            public void setImg2(String img2) {
                this.img2 = img2;
            }
        }
    }
}
