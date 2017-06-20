package com.example.mynewslayoutlib.Bean;

/**
 * Created by Administrator on 2017/6/20.
 */

public class MyInvitationBean {


    /**
     * status : 0
     * msg : 成功
     * content : {"two_code":"0","three_code":"0","four_code":"0","two_points":0,"three_points":0,"four_points":0,"sumpoints":"525","sum":0,"login_id":"276","image":"http://www.lianshiding.com/Public/asset/auth/my_info/2768340.jpg"}
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
         * two_code : 0
         * three_code : 0
         * four_code : 0
         * two_points : 0
         * three_points : 0
         * four_points : 0
         * sumpoints : 525
         * sum : 0
         * login_id : 276
         * image : http://www.lianshiding.com/Public/asset/auth/my_info/2768340.jpg
         */

        private String two_code;
        private String three_code;
        private String four_code;
        private int two_points;
        private int three_points;
        private int four_points;
        private String sumpoints;
        private int sum;
        private String login_id;
        private String image;

        public String getTwo_code() {
            return two_code;
        }

        public void setTwo_code(String two_code) {
            this.two_code = two_code;
        }

        public String getThree_code() {
            return three_code;
        }

        public void setThree_code(String three_code) {
            this.three_code = three_code;
        }

        public String getFour_code() {
            return four_code;
        }

        public void setFour_code(String four_code) {
            this.four_code = four_code;
        }

        public int getTwo_points() {
            return two_points;
        }

        public void setTwo_points(int two_points) {
            this.two_points = two_points;
        }

        public int getThree_points() {
            return three_points;
        }

        public void setThree_points(int three_points) {
            this.three_points = three_points;
        }

        public int getFour_points() {
            return four_points;
        }

        public void setFour_points(int four_points) {
            this.four_points = four_points;
        }

        public String getSumpoints() {
            return sumpoints;
        }

        public void setSumpoints(String sumpoints) {
            this.sumpoints = sumpoints;
        }

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        public String getLogin_id() {
            return login_id;
        }

        public void setLogin_id(String login_id) {
            this.login_id = login_id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
