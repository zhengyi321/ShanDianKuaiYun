package com.shandian.lu.NetWork;

import com.shandian.lu.NetWork.BaseFile.BaseNetWork;
import com.zhyan.shandiankuaiyunlib.Bean.AllGoodsBean;
import com.zhyan.shandiankuaiyunlib.Bean.CompanyIntroduceBean;
import com.zhyan.shandiankuaiyunlib.Bean.JiFenDuiHuanBean;
import com.zhyan.shandiankuaiyunlib.Bean.JiFenGoodsDetailBean;
import com.zhyan.shandiankuaiyunlib.Bean.JiFenShangChengBean;
import com.zhyan.shandiankuaiyunlib.Bean.ShangPinXiangQingAddAddressBean;
import com.zhyan.shandiankuaiyunlib.Bean.YiJianFanKuiBean;

import java.util.List;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;

/**
 * Created by az on 2017/4/26.
 */

public class CompanyNetWork extends BaseNetWork {

    protected  final NetService service = getRetrofit().create(NetService.class);
    private interface NetService{
        //设缓存有效期为1天
        final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
        //查询缓存的Cache-Control设置，使用缓存
        final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
        //查询网络的Cache-Control设置。不使用缓存
        final String CACHE_CONTROL_NETWORK = "max-age=0";
        /*公司简介*/
        //GET请求
        @GET("client/my_company.php")
        Observable<CompanyIntroduceBean> companyIntroduceFromNet();
       /*公司简介*/

       /*意见反馈*/
        //GET请求
        @GET("client/feedback.php")
        Observable<YiJianFanKuiBean> submitYiJianToNet(@Query("id") String id,@Query("content") String content);
       /*意见反馈*/

       /*积分商城*/
       @GET("index.php/app/index/jifenshop")
        Observable<JiFenShangChengBean> getJiFenShangChengFromNet(@Query("id") String id);
       /*积分商城*/

       /*积分商城 所有积分商城*/
       @GET("index.php/app/index/jifenshopsy")
       Observable<List<AllGoodsBean>> getJiFenShangChengAllGoodsFromNet(@Query("id") String id);
       /*积分商城 所有积分商城*/
       /*商品详情*/
       @GET("index.php/app/index/jifenshopdg")
       Observable<JiFenGoodsDetailBean> getJiFenShangChengGoodsDetailFromNet(@Query("id") String id,@Query("spid") String spid);
       /*商品详情*/

       /*填写收货地址*/
        @FormUrlEncoded
        @POST("index.php/app/index/jifendizhi")
        Observable<ShangPinXiangQingAddAddressBean> addAddressToNet(@Query("id") String id,@FieldMap Map<String,String> paramMap);
       /*填写收货地址*/

       /*积分兑换*/
       @FormUrlEncoded
       @POST("index.php/app/index/jifenduihuan")
       Observable<JiFenDuiHuanBean> duiHuanJIFenFromNet( @FieldMap Map<String,String> paramMap);
       /*积分兑换*/
    }

    public  void companyIntroduceFromNet( Observer<CompanyIntroduceBean> observer){
        setSubscribe(service.companyIntroduceFromNet(),observer);
    }
    public  void submitYiJianToNet( String id, String content, Observer<YiJianFanKuiBean> observer){
        setSubscribe(service.submitYiJianToNet(id,content),observer);
    }
    public  void getJiFenShangChengFromNet( String id, Observer<JiFenShangChengBean> observer){
        setSubscribe(service.getJiFenShangChengFromNet(id),observer);
    }
    public  void getJiFenShangChengAllGoodsFromNet( String id, Observer<List<AllGoodsBean>> observer){
        setSubscribe(service.getJiFenShangChengAllGoodsFromNet(id),observer);
    }
    public  void getJiFenShangChengGoodsDetailFromNet( String id,String spid, Observer<JiFenGoodsDetailBean> observer){
        setSubscribe(service.getJiFenShangChengGoodsDetailFromNet(id,spid),observer);
    }

    public  void addAddressToNet( String id,Map<String,String> paramMap, Observer<ShangPinXiangQingAddAddressBean> observer){
        setSubscribe(service.addAddressToNet(id,paramMap),observer);
    }

    public  void duiHuanJIFenFromNet( Map<String,String> paramMap, Observer<JiFenDuiHuanBean> observer){
        setSubscribe(service.duiHuanJIFenFromNet(paramMap),observer);
    }


}
