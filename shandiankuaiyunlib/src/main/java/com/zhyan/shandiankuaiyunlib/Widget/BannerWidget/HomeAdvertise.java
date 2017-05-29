package com.zhyan.shandiankuaiyunlib.Widget.BannerWidget;

import java.io.Serializable;

/**
 * ��ҳ�Ĺ���ֲ�ͼ
 * @author Administrator
 *
 */
public class HomeAdvertise implements Serializable{
	// ���ŵ� id
    private String id;
    // �������ͼƬ url
    private String imageUrl;
    //��������
    private String type;
    //������ϢId
    private String infoId;
    //��˾��ַ
    private String url;
    //�������Ź��ͼ
    private String leftimageUrl;
    private String leftUrl;
    private String lefttype;
    private String leftinfoId;
    private String rightUpimageUrl;
    private String rightUpUrl;
    private String rightUptype;
    private String rightUpfoId;
    private String rightDownimageUrl;
    private String rightDownUrl;
    private String rightDowntype;
    private String rightDowninfoId;
    public HomeAdvertise() {
		super();
	}

	public HomeAdvertise(String index, String imageUrl,String type,String infoId,String url) {
        this.id = index;
        this.imageUrl = imageUrl;
        this.type=type;
        this.infoId=infoId;
        this.url=url;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String index) {
        this.id = index;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLeftimageUrl() {
		return leftimageUrl;
	}

	public void setLeftimageUrl(String leftimageUrl) {
		this.leftimageUrl = leftimageUrl;
	}

	public String getLefttype() {
		return lefttype;
	}

	public void setLefttype(String lefttype) {
		this.lefttype = lefttype;
	}

	public String getLeftinfoId() {
		return leftinfoId;
	}

	public void setLeftinfoId(String leftinfoId) {
		this.leftinfoId = leftinfoId;
	}

	public String getRightUpimageUrl() {
		return rightUpimageUrl;
	}

	public void setRightUpimageUrl(String rightUpimageUrl) {
		this.rightUpimageUrl = rightUpimageUrl;
	}

	public String getRightUptype() {
		return rightUptype;
	}

	public void setRightUptype(String rightUptype) {
		this.rightUptype = rightUptype;
	}

	public String getRightUpfoId() {
		return rightUpfoId;
	}

	public void setRightUpfoId(String rightUpfoId) {
		this.rightUpfoId = rightUpfoId;
	}

	public String getRightDownimageUrl() {
		return rightDownimageUrl;
	}

	public void setRightDownimageUrl(String rightDownimageUrl) {
		this.rightDownimageUrl = rightDownimageUrl;
	}

	public String getRightDowntype() {
		return rightDowntype;
	}

	public void setRightDowntype(String rightDowntype) {
		this.rightDowntype = rightDowntype;
	}

	public String getRightDowninfoId() {
		return rightDowninfoId;
	}

	public void setRightDowninfoId(String rightDowninfoId) {
		this.rightDowninfoId = rightDowninfoId;
	}

	public String getLeftUrl() {
		return leftUrl;
	}

	public void setLeftUrl(String leftUrl) {
		this.leftUrl = leftUrl;
	}

	public String getRightUpUrl() {
		return rightUpUrl;
	}

	public void setRightUpUrl(String rightUpUrl) {
		this.rightUpUrl = rightUpUrl;
	}

	public String getRightDownUrl() {
		return rightDownUrl;
	}

	public void setRightDownUrl(String rightDownUrl) {
		this.rightDownUrl = rightDownUrl;
	}
    
}
