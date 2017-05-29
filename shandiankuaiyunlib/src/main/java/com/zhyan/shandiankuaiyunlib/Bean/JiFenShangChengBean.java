package com.zhyan.shandiankuaiyunlib.Bean;

import java.util.List;

/**
 * Created by az on 2017/5/23.
 */

public class JiFenShangChengBean {

    /**
     * jifen : 315
     * list : [{"id":"7","name":"时尚潮流LED电子手表男女情侣手环手表运动果冻电子手表","jifen":"1000","image":"https://www.lianshiding.com/Uploads/2017-05-10/5912a51aec82e.png","dunhuan":"0"},{"id":"8","name":"歌丽斯G9含包装直插型手机有线耳塞式耳机","jifen":"1000","image":"https://www.lianshiding.com/Uploads/2017-05-10/5912a9d37a120.png","dunhuan":"0"},{"id":"9","name":"小米手环2腕带 智能运动手环2代替换表带防水","jifen":"1000","image":"https://www.lianshiding.com/Uploads/2017-05-10/5912aea353ec6.png","dunhuan":"0"},{"id":"10","name":"指环扣支架 手机指环支架 指环支架 车载","jifen":"800","image":"https://www.lianshiding.com/Uploads/2017-05-10/5912b4c2dd40a.png","dunhuan":"0"},{"id":"11","name":"新款礼品超薄大容量金属移动电源 手机充电宝","jifen":"1600","image":"https://www.lianshiding.com/Uploads/2017-05-10/5912bee52625a.png","dunhuan":"0"},{"id":"12","name":"苹果IPHONE7 新款360度前后硅胶全包手机壳上下盖透明软壳保护套","jifen":"1000","image":"https://www.lianshiding.com/Uploads/2017-05-10/5912bbe09c671.png","dunhuan":"0"}]
     */

    private String jifen;
    private List<ListBean> list;

    public String getJifen() {
        return jifen;
    }

    public void setJifen(String jifen) {
        this.jifen = jifen;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 7
         * name : 时尚潮流LED电子手表男女情侣手环手表运动果冻电子手表
         * jifen : 1000
         * image : https://www.lianshiding.com/Uploads/2017-05-10/5912a51aec82e.png
         * dunhuan : 0
         */

        private String id;
        private String name;
        private String jifen;
        private String image;
        private String dunhuan;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getJifen() {
            return jifen;
        }

        public void setJifen(String jifen) {
            this.jifen = jifen;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDunhuan() {
            return dunhuan;
        }

        public void setDunhuan(String dunhuan) {
            this.dunhuan = dunhuan;
        }
    }
}
