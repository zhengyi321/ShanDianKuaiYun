package com.zhyan.shandiankuaiyunlib.Bean;

import java.util.List;

/**
 * Created by az on 2017/5/18.
 */

public class ZhuanXianWuliuCarSourceBean {

    /**
     * status : 0
     * msg : 成功
     * content : [{"id":"9802","car_title":"温州-广州","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/22.jpg","iphone":"13560182822","address_set":"温州","address_out":"广州","create_time":"05-10"},{"id":"9801","car_title":"温州-广州","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/8364.jpg","iphone":"13760387685","address_set":"温州","address_out":"广州","create_time":"05-11"},{"id":"9800","car_title":"温州-广州","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/22.jpg","iphone":"15961842113","address_set":"温州","address_out":"广州","create_time":"05-10"},{"id":"8698","car_title":"温州安吉物流有限公司(支持全国配货)","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1864797.jpg","iphone":"13868397364","address_set":"温州","address_out":"西安","create_time":"01-12"},{"id":"8697","car_title":"温州市鑫谊物流有限公司(支持全国配货","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1867169.jpg","iphone":"13505770663","address_set":"温州","address_out":"重庆","create_time":"01-12"},{"id":"8694","car_title":"温州市瓯海迅发托运部(支持全国配货)","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1868704.jpg","iphone":"13906645592","address_set":"温州","address_out":"石家庄","create_time":"01-12"},{"id":"8692","car_title":"温州惠诚物流有限公司(支持全国配货)","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1863833.jpg","iphone":"18757751988","address_set":"温州","address_out":"石家庄","create_time":"01-12"},{"id":"8689","car_title":"温州盛桥托运部(支持全国配货)","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1867684.jpg","iphone":"13777776976","address_set":"温州","address_out":"石家庄","create_time":"01-12"},{"id":"8686","car_title":"温州市路通货运部(支持全国配货)","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1867613.jpg","iphone":"18969728783","address_set":"温州","address_out":"石家庄","create_time":"01-12"},{"id":"8685","car_title":"温州嘉敏物流货运部(支持全国配货)","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1867507.jpg","iphone":"18958855535","address_set":"温州","address_out":"石家庄","create_time":"01-12"},{"id":"8684","car_title":"温州市金球物流有限公司(支持全国配货)","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1862767.jpg","iphone":"13957726701","address_set":"温州","address_out":"石家庄","create_time":"01-12"},{"id":"8683","car_title":"平安物流信息开发有限公司(支持全国配货)","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1863089.jpg","iphone":"13857713570","address_set":"温州","address_out":"石家庄","create_time":"01-12"},{"id":"8682","car_title":"温州铭钖物流有限公司(支持全国配货)","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1865352.jpg","iphone":"18957769270","address_set":"温州","address_out":"石家庄","create_time":"01-12"},{"id":"8681","car_title":"温州亿诚物流货运部(支持全国配货)","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1863225.jpg","iphone":"15205887581","address_set":"温州","address_out":"石家庄","create_time":"01-12"},{"id":"8680","car_title":"温州欧丽贝丝物流服务中心(支持全国配货)","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1861351.jpg","iphone":"15888271045","address_set":"温州","address_out":"石家庄","create_time":"01-12"},{"id":"8679","car_title":"温州万润物流有限公司(支持全国配货)","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1869073.jpg","iphone":"13806558933","address_set":"温州","address_out":"石家庄","create_time":"01-12"},{"id":"8678","car_title":"华侨物流有限公司(支持全国配货)","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1863584.jpg","iphone":"13968813277","address_set":"温州","address_out":"石家庄","create_time":"01-12"},{"id":"8676","car_title":"温州市安安物流有限公司(支持全国配货)","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1867518.jpg","iphone":"13566221291","address_set":"温州","address_out":"石家庄","create_time":"01-12"},{"id":"8675","car_title":"温州红荣物流有限公司(支持全国配货)","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1866365.jpg","iphone":"15967425410","address_set":"温州","address_out":"石家庄","create_time":"01-12"},{"id":"8671","car_title":"温州安联物流有限公司全国货运","img1":"http://www.lianshiding.com/Public/asset/auth/car_info/1863762.jpg","iphone":"13057812662","address_set":"温州","address_out":"遵义","create_time":"01-12"}]
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
         * id : 9802
         * car_title : 温州-广州
         * img1 : http://www.lianshiding.com/Public/asset/auth/car_info/22.jpg
         * iphone : 13560182822
         * address_set : 温州
         * address_out : 广州
         * create_time : 05-10
         */

        private String id;
        private String car_title;
        private String img1;
        private String iphone;
        private String address_set;
        private String address_out;
        private String create_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCar_title() {
            return car_title;
        }

        public void setCar_title(String car_title) {
            this.car_title = car_title;
        }

        public String getImg1() {
            return img1;
        }

        public void setImg1(String img1) {
            this.img1 = img1;
        }

        public String getIphone() {
            return iphone;
        }

        public void setIphone(String iphone) {
            this.iphone = iphone;
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

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
