package com.zhyan.shandiankuaiyunlib.Bean;

import java.util.List;

/**
 * Created by az on 2017/5/18.
 */

public class StreetListBean {

    /**
     * status : 0
     * msg : 成功
     * content : {"city":[{"tid":0,"town":"全温州","trea":[{"street_id":0,"street":"全温州"}]},{"tid":"678","town":"洞头","trea":[{"street_id":"678","street":"全洞头"}]},{"tid":"679","town":"永嘉","trea":[{"street_id":"679","street":"全永嘉"}]},{"tid":"680","town":"平阳","trea":[{"street_id":"680","street":"全平阳"}]},{"tid":"681","town":"苍南","trea":[{"street_id":"681","street":"全苍南"}]},{"tid":"682","town":"文成","trea":[{"street_id":"682","street":"全文成"}]},{"tid":"683","town":"泰顺","trea":[{"street_id":"683","street":"全泰顺"}]},{"tid":"684","town":"瑞安","trea":[{"street_id":"684","street":"全瑞安"}]},{"tid":"685","town":"乐清","trea":[{"street_id":"1","street":"全乐清"},{"street_id":"2","street":"乐成"},{"street_id":"3","street":"柳市"},{"street_id":"4","street":"磐石"},{"street_id":"5","street":"黄华"},{"street_id":"6","street":"白石"},{"street_id":"7","street":"象阳"},{"street_id":"8","street":"翁垟"},{"street_id":"9","street":"虹桥"},{"street_id":"10","street":"蒲岐"},{"street_id":"11","street":"南岳"},{"street_id":"12","street":"清江"},{"street_id":"13","street":"芙蓉"},{"street_id":"14","street":"南塘"},{"street_id":"15","street":"雁荡"},{"street_id":"16","street":"大荆"},{"street_id":"17","street":"仙溪"},{"street_id":"18","street":"湖雾"},{"street_id":"19","street":"石帆"},{"street_id":"20","street":"淡溪"},{"street_id":"21","street":"七里港"},{"street_id":"28","street":"北白象"},{"street_id":"176","street":"乐清周边"}]},{"tid":"3845","town":"鹿城","trea":[{"street_id":"3845","street":"全鹿城"}]},{"tid":"3846","town":"龙湾","trea":[{"street_id":"30","street":"全龙湾"},{"street_id":"31","street":"海滨"},{"street_id":"32","street":"海城"},{"street_id":"33","street":"灵昆"}]},{"tid":"3847","town":"瓯海","trea":[{"street_id":"3847","street":"全瓯海"}]}]}
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
        private List<CityBean> city;

        public List<CityBean> getCity() {
            return city;
        }

        public void setCity(List<CityBean> city) {
            this.city = city;
        }

        public static class CityBean {
            /**
             * tid : 0
             * town : 全温州
             * trea : [{"street_id":0,"street":"全温州"}]
             */

            private int tid;
            private String town;
            private List<TreaBean> trea;

            public int getTid() {
                return tid;
            }

            public void setTid(int tid) {
                this.tid = tid;
            }

            public String getTown() {
                return town;
            }

            public void setTown(String town) {
                this.town = town;
            }

            public List<TreaBean> getTrea() {
                return trea;
            }

            public void setTrea(List<TreaBean> trea) {
                this.trea = trea;
            }

            public static class TreaBean {
                /**
                 * street_id : 0
                 * street : 全温州
                 */

                private int street_id;
                private String street;

                public int getStreet_id() {
                    return street_id;
                }

                public void setStreet_id(int street_id) {
                    this.street_id = street_id;
                }

                public String getStreet() {
                    return street;
                }

                public void setStreet(String street) {
                    this.street = street;
                }
            }
        }
    }
}
