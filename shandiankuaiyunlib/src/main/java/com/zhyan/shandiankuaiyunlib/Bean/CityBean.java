package com.zhyan.shandiankuaiyunlib.Bean;

import java.util.List;

/**
 * Created by az on 2017/5/11.
 */

public class CityBean {
    /**
     * status : 0
     * msg : 成功
     * content : {"hot_province":[{"id":"1","province":"北京"},{"id":"2","province":"上海"},{"id":"3","province":"天津"},{"id":"4","province":"重庆"},{"id":"32","province":"广州"},{"id":"33","province":"深圳"},{"id":"34","province":"杭州"},{"id":"35","province":"南京"},{"id":"36","province":"武汉"}],"ordinary_province":[{"id":"1","province":"北京"},{"id":"2","province":"上海"},{"id":"3","province":"天津"},{"id":"4","province":"重庆"},{"id":"5","province":"山东"},{"id":"6","province":"江苏"},{"id":"7","province":"浙江"},{"id":"8","province":"安徽"},{"id":"9","province":"广东"},{"id":"10","province":"福建"},{"id":"11","province":"广西"},{"id":"12","province":"海南"},{"id":"13","province":"河南"},{"id":"14","province":"湖北"},{"id":"15","province":"湖南"},{"id":"16","province":"江西"},{"id":"17","province":"辽宁"},{"id":"18","province":"黑龙江"},{"id":"19","province":"吉林"},{"id":"20","province":"四川"},{"id":"21","province":"云南"},{"id":"22","province":"贵州"},{"id":"23","province":"西藏"},{"id":"24","province":"河北"},{"id":"25","province":"山西"},{"id":"26","province":"内蒙古"},{"id":"27","province":"陕西"},{"id":"28","province":"新疆"},{"id":"29","province":"甘肃"},{"id":"30","province":"宁夏"},{"id":"31","province":"青海"}]}
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
        private List<HotProvinceBean> hot_province;
        private List<OrdinaryProvinceBean> ordinary_province;

        public List<HotProvinceBean> getHot_province() {
            return hot_province;
        }

        public void setHot_province(List<HotProvinceBean> hot_province) {
            this.hot_province = hot_province;
        }

        public List<OrdinaryProvinceBean> getOrdinary_province() {
            return ordinary_province;
        }

        public void setOrdinary_province(List<OrdinaryProvinceBean> ordinary_province) {
            this.ordinary_province = ordinary_province;
        }

        public static class HotProvinceBean {
            /**
             * id : 1
             * province : 北京
             */

            private String id;
            private String province;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }
        }

        public static class OrdinaryProvinceBean {
            /**
             * id : 1
             * province : 北京
             */

            private String id;
            private String province;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }
        }
    }
}
