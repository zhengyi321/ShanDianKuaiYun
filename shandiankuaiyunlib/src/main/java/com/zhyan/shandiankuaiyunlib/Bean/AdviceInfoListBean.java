package com.zhyan.shandiankuaiyunlib.Bean;

import java.util.List;

/**
 * Created by az on 2017/5/20.
 */

public class AdviceInfoListBean {

    /**
     * status : 0
     * msg : 成功
     * content : [{"id":"20","title":"国际海运条例实施细则》修订 航运领域推进\u201c先照后证\u201d 改革便民利企"},{"id":"19","title":"温州港首条至东南亚集装箱航线盛装首航"},{"id":"18","title":"商务部：物流成本占GDP比重降1%，可节约7500亿元"},{"id":"17","title":"全国政协委员郭振家建议\u2014\u2014以创新驱动为引领降低物流成本"},{"id":"16","title":"快递业出台禁递品新规从58种禁递物品增加到188种"},{"id":"15","title":"两会直通车：将区位优势转化为西向物流优势"},{"id":"13","title":"物流迎来发展机遇 行业短板制约物流发展"},{"id":"12","title":"政策红利下的中俄跨境电商物流"},{"id":"10","title":"现代化经济发展推动物流走向智能化"},{"id":"9","title":"加快实现物流业降本增效 助力经济转型发展"},{"id":"8","title":"我国电商发展亟须解决物流\u201c痛点\u201d问题"}]
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
         * id : 20
         * title : 国际海运条例实施细则》修订 航运领域推进“先照后证” 改革便民利企
         */

        private String id;
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
