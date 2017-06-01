package com.shandian.lu.Bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by az on 2017/5/18.
 */

public class GoodsSourceDetailBean {

    /**
     * status : 0
     * msg : 成功
     * content : {"img":[{"img2":"http://www.lianshiding.com/Public/asset/auth/good_info/23.png"}],"0":{"id":"578","name":"celincal","good_name":"洗发水","type_name":"药品","address_set":"乐清","address_pick_up":"0","address_out":"苏家屯","address_pick_down":"0","address":"浙江省温州市乐清市双雁路458号","weight":"10","people":"赵先生","iphone":"13343343443","context":"","type":1,"collect":2,"count":"0","num":"8","create_time":"05-16","auth":0,"lng":"120.98143607974","lat":"28.122695014042","login_id":"327"}}
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
        /**
         * img : [{"img2":"http://www.lianshiding.com/Public/asset/auth/good_info/23.png"}]
         * 0 : {"id":"578","name":"celincal","good_name":"洗发水","type_name":"药品","address_set":"乐清","address_pick_up":"0","address_out":"苏家屯","address_pick_down":"0","address":"浙江省温州市乐清市双雁路458号","weight":"10","people":"赵先生","iphone":"13343343443","context":"","type":1,"collect":2,"count":"0","num":"8","create_time":"05-16","auth":0,"lng":"120.98143607974","lat":"28.122695014042","login_id":"327"}
         */

        @SerializedName("0")
        private _$0Bean _$0;
        private List<ImgBean> img;

        public _$0Bean get_$0() {
            return _$0;
        }

        public void set_$0(_$0Bean _$0) {
            this._$0 = _$0;
        }

        public List<ImgBean> getImg() {
            return img;
        }

        public void setImg(List<ImgBean> img) {
            this.img = img;
        }

        public static class _$0Bean {
            /**
             * id : 578
             * name : celincal
             * good_name : 洗发水
             * type_name : 药品
             * address_set : 乐清
             * address_pick_up : 0
             * address_out : 苏家屯
             * address_pick_down : 0
             * address : 浙江省温州市乐清市双雁路458号
             * weight : 10
             * people : 赵先生
             * iphone : 13343343443
             * context :
             * type : 1
             * collect : 2
             * count : 0
             * num : 8
             * create_time : 05-16
             * auth : 0
             * lng : 120.98143607974
             * lat : 28.122695014042
             * login_id : 327
             */

            private String id;
            private String name;
            private String good_name;
            private String type_name;
            private String address_set;
            private String address_pick_up;
            private String address_out;
            private String address_pick_down;
            private String address;
            private String weight;
            private String people;
            private String iphone;
            private String context;
            private int type;
            private int collect;
            private String count;
            private String num;
            private String create_time;
            private int auth;
            private String lng;
            private String lat;
            private String login_id;

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

            public String getGood_name() {
                return good_name;
            }

            public void setGood_name(String good_name) {
                this.good_name = good_name;
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

            public String getAddress_pick_up() {
                return address_pick_up;
            }

            public void setAddress_pick_up(String address_pick_up) {
                this.address_pick_up = address_pick_up;
            }

            public String getAddress_out() {
                return address_out;
            }

            public void setAddress_out(String address_out) {
                this.address_out = address_out;
            }

            public String getAddress_pick_down() {
                return address_pick_down;
            }

            public void setAddress_pick_down(String address_pick_down) {
                this.address_pick_down = address_pick_down;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
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

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
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

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
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
        }

        public static class ImgBean {
            /**
             * img2 : http://www.lianshiding.com/Public/asset/auth/good_info/23.png
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
