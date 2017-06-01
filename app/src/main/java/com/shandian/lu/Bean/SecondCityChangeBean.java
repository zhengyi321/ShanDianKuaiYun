package com.shandian.lu.Bean;

import java.util.List;

/**
 * Created by az on 2017/5/12.
 */

public class SecondCityChangeBean {

    /**
     * status : 0
     * msg : 成功
     * content : {"1":1,"2":2,"3":3,"list":[{"aid":"1449","area":"闸北"},{"aid":"1448","area":"普陀"},{"aid":"1447","area":"杨浦"},{"aid":"1446","area":"虹口"},{"aid":"1445","area":"长宁"},{"aid":"1444","area":"浦东"},{"aid":"1443","area":"徐汇"},{"aid":"1442","area":"静安"},{"aid":"1441","area":"卢湾"},{"aid":"1440","area":"黄浦"},{"aid":"1360","area":"崇明"},{"aid":"1359","area":"宝山"},{"aid":"1358","area":"奉贤"},{"aid":"1357","area":"南汇"},{"aid":"1356","area":"青浦"},{"aid":"1355","area":"松江"},{"aid":"1354","area":"金山"},{"aid":"1353","area":"嘉定"},{"aid":"1352","area":"全上海"}]}
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
         * 1 : 1
         * 2 : 2
         * 3 : 3
         * list : [{"aid":"1449","area":"闸北"},{"aid":"1448","area":"普陀"},{"aid":"1447","area":"杨浦"},{"aid":"1446","area":"虹口"},{"aid":"1445","area":"长宁"},{"aid":"1444","area":"浦东"},{"aid":"1443","area":"徐汇"},{"aid":"1442","area":"静安"},{"aid":"1441","area":"卢湾"},{"aid":"1440","area":"黄浦"},{"aid":"1360","area":"崇明"},{"aid":"1359","area":"宝山"},{"aid":"1358","area":"奉贤"},{"aid":"1357","area":"南汇"},{"aid":"1356","area":"青浦"},{"aid":"1355","area":"松江"},{"aid":"1354","area":"金山"},{"aid":"1353","area":"嘉定"},{"aid":"1352","area":"全上海"}]
         */

        @com.google.gson.annotations.SerializedName("1")
        private int _$1;
        @com.google.gson.annotations.SerializedName("2")
        private int _$2;
        @com.google.gson.annotations.SerializedName("3")
        private int _$3;
        private List<ListBean> list;

        public int get_$1() {
            return _$1;
        }

        public void set_$1(int _$1) {
            this._$1 = _$1;
        }

        public int get_$2() {
            return _$2;
        }

        public void set_$2(int _$2) {
            this._$2 = _$2;
        }

        public int get_$3() {
            return _$3;
        }

        public void set_$3(int _$3) {
            this._$3 = _$3;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * aid : 1449
             * area : 闸北
             */

            private String aid;
            private String area;

            public String getAid() {
                return aid;
            }

            public void setAid(String aid) {
                this.aid = aid;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }
        }
    }
}
