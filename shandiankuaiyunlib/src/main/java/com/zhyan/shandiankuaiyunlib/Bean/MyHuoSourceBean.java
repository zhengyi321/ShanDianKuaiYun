package com.zhyan.shandiankuaiyunlib.Bean;

import java.util.List;

public class MyHuoSourceBean {

	/**
	 * status : 0
	 * msg : 成功
	 * content : [{"id":"577","login_id":"276","type_name":"建材","good_name":"测试","set_province_id":"11","set_city_id":"1006","set_area_id":"4041","out_province_id":"11","out_city_id":"1007","out_area_id":"4200","address_set":"覃塘","address_pick_up":"0","address_out":"钦北","address_pick_down":"0","address":"0","weight":"11","people":"77","iphone":"44","img1":"http://www.lianshiding.com/Public/asset/auth/good_info/2761764.jpg","img2":"http://www.lianshiding.com/Public/asset/auth/good_info/23.png","img3":"0","img4":"0","img5":"0","img6":"0","img7":"0","img8":"0","context":"","pass_status":"1","create_time":"2017-05-03","num":"1"},{"id":"576","login_id":"276","type_name":"药品","good_name":"测试","set_province_id":"14","set_city_id":"1133","set_area_id":"4109","out_province_id":"13","out_city_id":"1191","out_area_id":"3496","address_set":"孝南","address_pick_up":"0","address_out":"天坛街道","address_pick_down":"0","address":"浙江省温州市乐清市双雁路458号","weight":"11","people":"zz","iphone":"11","img1":"http://www.lianshiding.com/Public/asset/auth/good_info/2764331.jpg","img2":"http://www.lianshiding.com/Public/asset/auth/good_info/23.png","img3":"0","img4":"0","img5":"0","img6":"0","img7":"0","img8":"0","context":"","pass_status":"1","create_time":"2017-05-03","num":"2"}]
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
		 * id : 577
		 * login_id : 276
		 * type_name : 建材
		 * good_name : 测试
		 * set_province_id : 11
		 * set_city_id : 1006
		 * set_area_id : 4041
		 * out_province_id : 11
		 * out_city_id : 1007
		 * out_area_id : 4200
		 * address_set : 覃塘
		 * address_pick_up : 0
		 * address_out : 钦北
		 * address_pick_down : 0
		 * address : 0
		 * weight : 11
		 * people : 77
		 * iphone : 44
		 * img1 : http://www.lianshiding.com/Public/asset/auth/good_info/2761764.jpg
		 * img2 : http://www.lianshiding.com/Public/asset/auth/good_info/23.png
		 * img3 : 0
		 * img4 : 0
		 * img5 : 0
		 * img6 : 0
		 * img7 : 0
		 * img8 : 0
		 * context :
		 * pass_status : 1
		 * create_time : 2017-05-03
		 * num : 1
		 */

		private String id;
		private String login_id;
		private String type_name;
		private String good_name;
		private String set_province_id;
		private String set_city_id;
		private String set_area_id;
		private String out_province_id;
		private String out_city_id;
		private String out_area_id;
		private String address_set;
		private String address_pick_up;
		private String address_out;
		private String address_pick_down;
		private String address;
		private String weight;
		private String people;
		private String iphone;
		private String img1;
		private String img2;
		private String img3;
		private String img4;
		private String img5;
		private String img6;
		private String img7;
		private String img8;
		private String context;
		private String pass_status;
		private String create_time;
		private String num;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getLogin_id() {
			return login_id;
		}

		public void setLogin_id(String login_id) {
			this.login_id = login_id;
		}

		public String getType_name() {
			return type_name;
		}

		public void setType_name(String type_name) {
			this.type_name = type_name;
		}

		public String getGood_name() {
			return good_name;
		}

		public void setGood_name(String good_name) {
			this.good_name = good_name;
		}

		public String getSet_province_id() {
			return set_province_id;
		}

		public void setSet_province_id(String set_province_id) {
			this.set_province_id = set_province_id;
		}

		public String getSet_city_id() {
			return set_city_id;
		}

		public void setSet_city_id(String set_city_id) {
			this.set_city_id = set_city_id;
		}

		public String getSet_area_id() {
			return set_area_id;
		}

		public void setSet_area_id(String set_area_id) {
			this.set_area_id = set_area_id;
		}

		public String getOut_province_id() {
			return out_province_id;
		}

		public void setOut_province_id(String out_province_id) {
			this.out_province_id = out_province_id;
		}

		public String getOut_city_id() {
			return out_city_id;
		}

		public void setOut_city_id(String out_city_id) {
			this.out_city_id = out_city_id;
		}

		public String getOut_area_id() {
			return out_area_id;
		}

		public void setOut_area_id(String out_area_id) {
			this.out_area_id = out_area_id;
		}

		public String getAddress_set() {
			return address_set;
		}

		public void setAddress_set(String address_set) {
			this.address_set = address_set;
		}

		public String getAddress_pick_up() {
			return address_pick_up;
		}

		public void setAddress_pick_up(String address_pick_up) {
			this.address_pick_up = address_pick_up;
		}

		public String getAddress_out() {
			return address_out;
		}

		public void setAddress_out(String address_out) {
			this.address_out = address_out;
		}

		public String getAddress_pick_down() {
			return address_pick_down;
		}

		public void setAddress_pick_down(String address_pick_down) {
			this.address_pick_down = address_pick_down;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getWeight() {
			return weight;
		}

		public void setWeight(String weight) {
			this.weight = weight;
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

		public String getImg1() {
			return img1;
		}

		public void setImg1(String img1) {
			this.img1 = img1;
		}

		public String getImg2() {
			return img2;
		}

		public void setImg2(String img2) {
			this.img2 = img2;
		}

		public String getImg3() {
			return img3;
		}

		public void setImg3(String img3) {
			this.img3 = img3;
		}

		public String getImg4() {
			return img4;
		}

		public void setImg4(String img4) {
			this.img4 = img4;
		}

		public String getImg5() {
			return img5;
		}

		public void setImg5(String img5) {
			this.img5 = img5;
		}

		public String getImg6() {
			return img6;
		}

		public void setImg6(String img6) {
			this.img6 = img6;
		}

		public String getImg7() {
			return img7;
		}

		public void setImg7(String img7) {
			this.img7 = img7;
		}

		public String getImg8() {
			return img8;
		}

		public void setImg8(String img8) {
			this.img8 = img8;
		}

		public String getContext() {
			return context;
		}

		public void setContext(String context) {
			this.context = context;
		}

		public String getPass_status() {
			return pass_status;
		}

		public void setPass_status(String pass_status) {
			this.pass_status = pass_status;
		}

		public String getCreate_time() {
			return create_time;
		}

		public void setCreate_time(String create_time) {
			this.create_time = create_time;
		}

		public String getNum() {
			return num;
		}

		public void setNum(String num) {
			this.num = num;
		}
	}
}
