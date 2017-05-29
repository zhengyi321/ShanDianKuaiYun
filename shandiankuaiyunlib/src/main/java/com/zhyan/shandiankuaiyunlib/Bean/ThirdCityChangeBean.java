package com.zhyan.shandiankuaiyunlib.Bean;

import java.util.List;

/**
 * Created by az on 2017/5/15.
 */

public class ThirdCityChangeBean {
    /**
     * status : 0
     * msg : 成功
     * content : {"list":[{"aid":"1335","area":"全青岛"},{"aid":"1336","area":"胶州"},{"aid":"1337","area":"即墨"},{"aid":"1338","area":"平度"},{"aid":"1339","area":"胶南"},{"aid":"1340","area":"莱西"},{"aid":"3956","area":"市南"},{"aid":"3957","area":"市北"},{"aid":"3958","area":"城阳"},{"aid":"3959","area":"四方"},{"aid":"3960","area":"李沧"},{"aid":"3961","area":"黄岛"},{"aid":"3962","area":"崂山"}]}
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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * aid : 1335
             * area : 全青岛
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
