package com.example.mynewslayoutlib.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public class NewWoDeCheYuanBean {


    /**
     * status : 0
     * msg :
     * nr : {"ys":"1","list":[{"id":"9949","login_id":"276","type_name":"3","car_title":"广人","set_province_id":"0","set_city_id":"0","set_area_id":"0","out_province_id":"0","out_city_id":"0","out_area_id":"0","set_tid":"0","out_tid":"0","address_set":"","address_out":"","address_set_detail":"","address_out_detail":"","iphone":"99","people":"ivh","address":"","car_type":"冷藏车","car_lange":"5","img1":"","img2":"","img3":"","img4":"","img5":"","img6":"","img7":"","img8":"","is_pass":"1","status":"0","type":"0","content":"","num":"1","create_time":"1497165345","update_time":"1497165345","imgtu":["/Uploads/2017-06-11/2761497165345VLgl.jpg"],"cfsheng":"浙江省","cfshi":"温州市","cfqu":"乐清市","cfdizhi":"中国浙江省温州市乐清市旭阳路150号 在中国建设银行(乐清旭阳分理处)附近","cfzuobiao":"28.122452,120.981792","dasheng":"浙江省","dashi":"温州市","daqu":"乐清市","dadizhi":"浙江省温州市乐清市玉箫路  双阳商务宾馆南192米","dazuobiao":"28.120111294582323,120.98040841824158","lng":"120.981792","lat":"28.122452","zt":"1","juli":"2000","zuobiaohash":"wsypcp830j48","gg":"0"}]}
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
         * list : [{"id":"9949","login_id":"276","type_name":"3","car_title":"广人","set_province_id":"0","set_city_id":"0","set_area_id":"0","out_province_id":"0","out_city_id":"0","out_area_id":"0","set_tid":"0","out_tid":"0","address_set":"","address_out":"","address_set_detail":"","address_out_detail":"","iphone":"99","people":"ivh","address":"","car_type":"冷藏车","car_lange":"5","img1":"","img2":"","img3":"","img4":"","img5":"","img6":"","img7":"","img8":"","is_pass":"1","status":"0","type":"0","content":"","num":"1","create_time":"1497165345","update_time":"1497165345","imgtu":["/Uploads/2017-06-11/2761497165345VLgl.jpg"],"cfsheng":"浙江省","cfshi":"温州市","cfqu":"乐清市","cfdizhi":"中国浙江省温州市乐清市旭阳路150号 在中国建设银行(乐清旭阳分理处)附近","cfzuobiao":"28.122452,120.981792","dasheng":"浙江省","dashi":"温州市","daqu":"乐清市","dadizhi":"浙江省温州市乐清市玉箫路  双阳商务宾馆南192米","dazuobiao":"28.120111294582323,120.98040841824158","lng":"120.981792","lat":"28.122452","zt":"1","juli":"2000","zuobiaohash":"wsypcp830j48","gg":"0"}]
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
             * id : 9949
             * login_id : 276
             * type_name : 3
             * car_title : 广人
             * set_province_id : 0
             * set_city_id : 0
             * set_area_id : 0
             * out_province_id : 0
             * out_city_id : 0
             * out_area_id : 0
             * set_tid : 0
             * out_tid : 0
             * address_set :
             * address_out :
             * address_set_detail :
             * address_out_detail :
             * iphone : 99
             * people : ivh
             * address :
             * car_type : 冷藏车
             * car_lange : 5
             * img1 :
             * img2 :
             * img3 :
             * img4 :
             * img5 :
             * img6 :
             * img7 :
             * img8 :
             * is_pass : 1
             * status : 0
             * type : 0
             * content :
             * num : 1
             * create_time : 1497165345
             * update_time : 1497165345
             * imgtu : ["/Uploads/2017-06-11/2761497165345VLgl.jpg"]
             * cfsheng : 浙江省
             * cfshi : 温州市
             * cfqu : 乐清市
             * cfdizhi : 中国浙江省温州市乐清市旭阳路150号 在中国建设银行(乐清旭阳分理处)附近
             * cfzuobiao : 28.122452,120.981792
             * dasheng : 浙江省
             * dashi : 温州市
             * daqu : 乐清市
             * dadizhi : 浙江省温州市乐清市玉箫路  双阳商务宾馆南192米
             * dazuobiao : 28.120111294582323,120.98040841824158
             * lng : 120.981792
             * lat : 28.122452
             * zt : 1
             * juli : 2000
             * zuobiaohash : wsypcp830j48
             * gg : 0
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
            private String set_tid;
            private String out_tid;
            private String address_set;
            private String address_out;
            private String address_set_detail;
            private String address_out_detail;
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
            private String update_time;
            private String cfsheng;
            private String cfshi;
            private String cfqu;
            private String cfdizhi;
            private String cfzuobiao;
            private String dasheng;
            private String dashi;
            private String daqu;
            private String dadizhi;
            private String dazuobiao;
            private String lng;
            private String lat;
            private String zt;
            private String juli;
            private String zuobiaohash;
            private String gg;
            private List<String> imgtu;

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

            public String getSet_tid() {
                return set_tid;
            }

            public void setSet_tid(String set_tid) {
                this.set_tid = set_tid;
            }

            public String getOut_tid() {
                return out_tid;
            }

            public void setOut_tid(String out_tid) {
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

            public String getAddress_set_detail() {
                return address_set_detail;
            }

            public void setAddress_set_detail(String address_set_detail) {
                this.address_set_detail = address_set_detail;
            }

            public String getAddress_out_detail() {
                return address_out_detail;
            }

            public void setAddress_out_detail(String address_out_detail) {
                this.address_out_detail = address_out_detail;
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

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public String getCfsheng() {
                return cfsheng;
            }

            public void setCfsheng(String cfsheng) {
                this.cfsheng = cfsheng;
            }

            public String getCfshi() {
                return cfshi;
            }

            public void setCfshi(String cfshi) {
                this.cfshi = cfshi;
            }

            public String getCfqu() {
                return cfqu;
            }

            public void setCfqu(String cfqu) {
                this.cfqu = cfqu;
            }

            public String getCfdizhi() {
                return cfdizhi;
            }

            public void setCfdizhi(String cfdizhi) {
                this.cfdizhi = cfdizhi;
            }

            public String getCfzuobiao() {
                return cfzuobiao;
            }

            public void setCfzuobiao(String cfzuobiao) {
                this.cfzuobiao = cfzuobiao;
            }

            public String getDasheng() {
                return dasheng;
            }

            public void setDasheng(String dasheng) {
                this.dasheng = dasheng;
            }

            public String getDashi() {
                return dashi;
            }

            public void setDashi(String dashi) {
                this.dashi = dashi;
            }

            public String getDaqu() {
                return daqu;
            }

            public void setDaqu(String daqu) {
                this.daqu = daqu;
            }

            public String getDadizhi() {
                return dadizhi;
            }

            public void setDadizhi(String dadizhi) {
                this.dadizhi = dadizhi;
            }

            public String getDazuobiao() {
                return dazuobiao;
            }

            public void setDazuobiao(String dazuobiao) {
                this.dazuobiao = dazuobiao;
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

            public String getZt() {
                return zt;
            }

            public void setZt(String zt) {
                this.zt = zt;
            }

            public String getJuli() {
                return juli;
            }

            public void setJuli(String juli) {
                this.juli = juli;
            }

            public String getZuobiaohash() {
                return zuobiaohash;
            }

            public void setZuobiaohash(String zuobiaohash) {
                this.zuobiaohash = zuobiaohash;
            }

            public String getGg() {
                return gg;
            }

            public void setGg(String gg) {
                this.gg = gg;
            }

            public List<String> getImgtu() {
                return imgtu;
            }

            public void setImgtu(List<String> imgtu) {
                this.imgtu = imgtu;
            }
        }
    }
}
