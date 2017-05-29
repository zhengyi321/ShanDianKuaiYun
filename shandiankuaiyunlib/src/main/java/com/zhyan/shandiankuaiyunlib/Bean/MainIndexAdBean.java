package com.zhyan.shandiankuaiyunlib.Bean;

import java.util.List;

/**
 * Created by az on 2017/5/11.
 */

public class MainIndexAdBean {

    /**
     * status : 0
     * msg : 成功
     * content : {"left":[{"id":"50","company_name":"闪电物流4","company_name_url":"http://www.lianshiding.com","url":"http://www.lianshiding.com/client/menu_car_info_list_detail_v2.php","infoid":"5458","people":"","iphone":"","image":"http://www.lianshiding.com/Uploads/2016-12-27/586237f3e4e1c.png","content":"","type":"2","position":"2","positionid":"0"}],"rightup":[{"id":"51","company_name":"闪电物流5","company_name_url":"http://www.lianshiding.com/xiazai","url":"http://www.lianshiding.com/xiazai","infoid":null,"people":"","iphone":"","image":"http://www.lianshiding.com/Uploads/2016-12-27/586237e99c671.png","content":"","type":"1","position":"3","positionid":"0"}],"rightdown":[{"id":"52","company_name":"闪电物流6","company_name_url":"http://www.lianshiding.com","url":"http://www.lianshiding.com/client/menu_car_info_list_detail_v2.php","infoid":"5483","people":"","iphone":"","image":"http://www.lianshiding.com/Uploads/2016-12-27/586237e08d24d.png","content":"","type":"2","position":"4","positionid":"0"}],"side":[{"id":"70","company_name":"","company_name_url":"http://www.lianshiding.com/xiazai/","url":"http://www.lianshiding.com/xiazai/","infoid":null,"people":"","iphone":"","image":"http://www.lianshiding.com/Uploads/2017-02-15/58a40afeca2dd.png","content":"","type":"1","position":"1","positionid":"0"},{"id":"63","company_name":"","company_name_url":"http://dl2.unionapp.org/?code=slhyjyw","url":"http://dl2.unionapp.org/?code=slhyjyw","infoid":null,"people":"","iphone":"","image":"http://www.lianshiding.com/Uploads/2017-01-12/587745277a120.png","content":"","type":"1","position":"1","positionid":"2"}]}
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
        private List<LeftBean> left;
        private List<RightupBean> rightup;
        private List<RightdownBean> rightdown;
        private List<SideBean> side;

        public List<LeftBean> getLeft() {
            return left;
        }

        public void setLeft(List<LeftBean> left) {
            this.left = left;
        }

        public List<RightupBean> getRightup() {
            return rightup;
        }

        public void setRightup(List<RightupBean> rightup) {
            this.rightup = rightup;
        }

        public List<RightdownBean> getRightdown() {
            return rightdown;
        }

        public void setRightdown(List<RightdownBean> rightdown) {
            this.rightdown = rightdown;
        }

        public List<SideBean> getSide() {
            return side;
        }

        public void setSide(List<SideBean> side) {
            this.side = side;
        }

        public static class LeftBean {
            /**
             * id : 50
             * company_name : 闪电物流4
             * company_name_url : http://www.lianshiding.com
             * url : http://www.lianshiding.com/client/menu_car_info_list_detail_v2.php
             * infoid : 5458
             * people :
             * iphone :
             * image : http://www.lianshiding.com/Uploads/2016-12-27/586237f3e4e1c.png
             * content :
             * type : 2
             * position : 2
             * positionid : 0
             */

            private String id;
            private String company_name;
            private String company_name_url;
            private String url;
            private String infoid;
            private String people;
            private String iphone;
            private String image;
            private String content;
            private String type;
            private String position;
            private String positionid;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getCompany_name_url() {
                return company_name_url;
            }

            public void setCompany_name_url(String company_name_url) {
                this.company_name_url = company_name_url;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getInfoid() {
                return infoid;
            }

            public void setInfoid(String infoid) {
                this.infoid = infoid;
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

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getPositionid() {
                return positionid;
            }

            public void setPositionid(String positionid) {
                this.positionid = positionid;
            }
        }

        public static class RightupBean {
            /**
             * id : 51
             * company_name : 闪电物流5
             * company_name_url : http://www.lianshiding.com/xiazai
             * url : http://www.lianshiding.com/xiazai
             * infoid : null
             * people :
             * iphone :
             * image : http://www.lianshiding.com/Uploads/2016-12-27/586237e99c671.png
             * content :
             * type : 1
             * position : 3
             * positionid : 0
             */

            private String id;
            private String company_name;
            private String company_name_url;
            private String url;
            private Object infoid;
            private String people;
            private String iphone;
            private String image;
            private String content;
            private String type;
            private String position;
            private String positionid;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getCompany_name_url() {
                return company_name_url;
            }

            public void setCompany_name_url(String company_name_url) {
                this.company_name_url = company_name_url;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Object getInfoid() {
                return infoid;
            }

            public void setInfoid(Object infoid) {
                this.infoid = infoid;
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

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getPositionid() {
                return positionid;
            }

            public void setPositionid(String positionid) {
                this.positionid = positionid;
            }
        }

        public static class RightdownBean {
            /**
             * id : 52
             * company_name : 闪电物流6
             * company_name_url : http://www.lianshiding.com
             * url : http://www.lianshiding.com/client/menu_car_info_list_detail_v2.php
             * infoid : 5483
             * people :
             * iphone :
             * image : http://www.lianshiding.com/Uploads/2016-12-27/586237e08d24d.png
             * content :
             * type : 2
             * position : 4
             * positionid : 0
             */

            private String id;
            private String company_name;
            private String company_name_url;
            private String url;
            private String infoid;
            private String people;
            private String iphone;
            private String image;
            private String content;
            private String type;
            private String position;
            private String positionid;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getCompany_name_url() {
                return company_name_url;
            }

            public void setCompany_name_url(String company_name_url) {
                this.company_name_url = company_name_url;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getInfoid() {
                return infoid;
            }

            public void setInfoid(String infoid) {
                this.infoid = infoid;
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

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getPositionid() {
                return positionid;
            }

            public void setPositionid(String positionid) {
                this.positionid = positionid;
            }
        }

        public static class SideBean {
            /**
             * id : 70
             * company_name :
             * company_name_url : http://www.lianshiding.com/xiazai/
             * url : http://www.lianshiding.com/xiazai/
             * infoid : null
             * people :
             * iphone :
             * image : http://www.lianshiding.com/Uploads/2017-02-15/58a40afeca2dd.png
             * content :
             * type : 1
             * position : 1
             * positionid : 0
             */

            private String id;
            private String company_name;
            private String company_name_url;
            private String url;
            private Object infoid;
            private String people;
            private String iphone;
            private String image;
            private String content;
            private String type;
            private String position;
            private String positionid;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getCompany_name_url() {
                return company_name_url;
            }

            public void setCompany_name_url(String company_name_url) {
                this.company_name_url = company_name_url;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Object getInfoid() {
                return infoid;
            }

            public void setInfoid(Object infoid) {
                this.infoid = infoid;
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

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getPositionid() {
                return positionid;
            }

            public void setPositionid(String positionid) {
                this.positionid = positionid;
            }
        }
    }
}
