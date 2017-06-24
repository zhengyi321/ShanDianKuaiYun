package com.example.mynewslayoutlib.Bean;

/**
 * Created by Administrator on 2017/6/24.
 */

public class NewAdsBean {

    /**
     * status : 0
     * msg :
     * nr : {"img":"http://u1.img.mobile.sina.cn/public/files/image/660x165_img594cb2f907f55.png","url":"http://www.baidu.com"}
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
         * img : http://u1.img.mobile.sina.cn/public/files/image/660x165_img594cb2f907f55.png
         * url : http://www.baidu.com
         */

        private String img;
        private String url;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
