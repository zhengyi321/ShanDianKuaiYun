package com.example.mynewslayoutlib.Bean;

/**
 * Created by Administrator on 2017/6/13.
 */

public class NewDingJinPayBean {

    /**
     * status : 0
     * msg :
     * nr : {"zjine":"11","dingjin":2.2,"weikuan":8.8,"zfcs":"appId=2017022205818471&biz_content=%7B%22dingdanid%22%3A%2237%22%2C%22lx%22%3A1%7D&body=%E5%AE%9A%E9%87%91%E6%94%AF%E4%BB%98&charset=utf-8&format=json&gatewayUrl=http%3A%2F%2Fwww.lianshiding.com%2Findex.php%2Fapp%2Fzhifu%2Fzhifubao&method=alipay.trade.app.pay&product_code=QUICK_MSECURITY_PAY&signType=RSA2&sign=C7z1Ha0zCG%2Bjp%2FeTkzG%2BQrg71Mm2WiB2%2FpdCxSKK1RVKvSgQejYkBXgO%2BEYsv46JvxLurK7ZH5OyT3Inp5%2FXkkYgPdk7EfYhAwytkqHcj5YbBFxumTADnsgRY8zzi1rIsMr4cfSxEoTXxt5IpoAtqu8t9UQYdTSIE0ILEdiBFcVyxmnyBx9LpDppz%2FWEBQ1yn7%2BpdxMPDY0qucO4UxGNVCsA74mUeIORKOa55PUhn%2F%2FxPC1TNn3iQUXluTC%2BC3%2BnkmcRf6fUwDuloEsAAI0R37y%2FbucV0ejOrlorjpi298t4yAeCpoZROI0sQT%2BbQ8l8VFpV6seDz1TrF%2FZlCFK%2BzA%3D%3D"}
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
         * zjine : 11
         * dingjin : 2.2
         * weikuan : 8.8
         * zfcs : appId=2017022205818471&biz_content=%7B%22dingdanid%22%3A%2237%22%2C%22lx%22%3A1%7D&body=%E5%AE%9A%E9%87%91%E6%94%AF%E4%BB%98&charset=utf-8&format=json&gatewayUrl=http%3A%2F%2Fwww.lianshiding.com%2Findex.php%2Fapp%2Fzhifu%2Fzhifubao&method=alipay.trade.app.pay&product_code=QUICK_MSECURITY_PAY&signType=RSA2&sign=C7z1Ha0zCG%2Bjp%2FeTkzG%2BQrg71Mm2WiB2%2FpdCxSKK1RVKvSgQejYkBXgO%2BEYsv46JvxLurK7ZH5OyT3Inp5%2FXkkYgPdk7EfYhAwytkqHcj5YbBFxumTADnsgRY8zzi1rIsMr4cfSxEoTXxt5IpoAtqu8t9UQYdTSIE0ILEdiBFcVyxmnyBx9LpDppz%2FWEBQ1yn7%2BpdxMPDY0qucO4UxGNVCsA74mUeIORKOa55PUhn%2F%2FxPC1TNn3iQUXluTC%2BC3%2BnkmcRf6fUwDuloEsAAI0R37y%2FbucV0ejOrlorjpi298t4yAeCpoZROI0sQT%2BbQ8l8VFpV6seDz1TrF%2FZlCFK%2BzA%3D%3D
         */

        private String zjine;
        private double dingjin;
        private double weikuan;
        private String zfcs;

        public String getZjine() {
            return zjine;
        }

        public void setZjine(String zjine) {
            this.zjine = zjine;
        }

        public double getDingjin() {
            return dingjin;
        }

        public void setDingjin(double dingjin) {
            this.dingjin = dingjin;
        }

        public double getWeikuan() {
            return weikuan;
        }

        public void setWeikuan(double weikuan) {
            this.weikuan = weikuan;
        }

        public String getZfcs() {
            return zfcs;
        }

        public void setZfcs(String zfcs) {
            this.zfcs = zfcs;
        }
    }
}
