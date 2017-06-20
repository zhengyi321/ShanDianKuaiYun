package com.zhyan.shandiankuaiyunlib.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/20.
 */

public class QianDaoInitBean {

    /**
     * status : 0
     * msg :
     * nr : ["2017/06/01","2017/06/16"]
     * zjifen : 525
     * lianxuqd : 0
     * lianxu : 0
     */

    private String status;
    private String msg;
    private String zjifen;
    private String lianxuqd;
    private String lianxu;
    private List<String> nr;

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

    public String getZjifen() {
        return zjifen;
    }

    public void setZjifen(String zjifen) {
        this.zjifen = zjifen;
    }

    public String getLianxuqd() {
        return lianxuqd;
    }

    public void setLianxuqd(String lianxuqd) {
        this.lianxuqd = lianxuqd;
    }

    public String getLianxu() {
        return lianxu;
    }

    public void setLianxu(String lianxu) {
        this.lianxu = lianxu;
    }

    public List<String> getNr() {
        return nr;
    }

    public void setNr(List<String> nr) {
        this.nr = nr;
    }
}
