package com.example.mynewslayoutlib.Bean;

/**
 * Created by Administrator on 2017/6/8.
 */

public class NewHongBaoBean {

    /**
     *
     * http://www.lianshiding.com/index.php/App/Index/hongbaolingqu
     请求方式 POST
     参数1  id (用户id)276
     http://www.lianshiding.com/index.php/App/Index/hongbaofenxiang
     请求方式 POST
     参数1  id (用户id)

     http://www.lianshiding.com/index.php/App/Index/wodeqianbao
     请求方式 POST
     参数1  id (用户id)
     收支明细接口
     http://www.lianshiding.com/index.php/App/Index/shouzhimingxi
     请求方式 POST
     参数1  id (用户id)
     *
     * status : 0
     * msg : 恭喜您领取成功！
     * nr : {"jine":"0.08"}
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
         * jine : 0.08
         */

        private String jine;

        public String getJine() {
            return jine;
        }

        public void setJine(String jine) {
            this.jine = jine;
        }
    }
}
