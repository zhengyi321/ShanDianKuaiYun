package com.shandian.lu.NetWork;

import com.shandian.lu.NetWork.BaseFile.BaseNetWork;
import com.zhyan.shandiankuaiyunlib.Bean.AuthCarBean;
import com.zhyan.shandiankuaiyunlib.Bean.AuthCompanyBean;
import com.zhyan.shandiankuaiyunlib.Bean.AuthInFoBean;
import com.zhyan.shandiankuaiyunlib.Bean.AuthJiaShiZhengBean;
import com.zhyan.shandiankuaiyunlib.Bean.AuthRealNameBean;
import com.zhyan.shandiankuaiyunlib.Bean.AuthXingShiZhengBean;

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

public class RenZhengNetWork extends BaseNetWork {

    protected  final NetService service = getRetrofit().create(NetService.class);
    private interface NetService{
        //设缓存有效期为1天
        final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
        //查询缓存的Cache-Control设置，使用缓存
        final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
        //查询网络的Cache-Control设置。不使用缓存
        final String CACHE_CONTROL_NETWORK = "max-age=0";
        /*车辆认证*/
        //POST请求
        @FormUrlEncoded
        @POST("client/car_auth.php")
        /*Observable<CarSourceBean> releaseZhuanXianWuLiuToNet( @QueryMap Map<String , Object> usermaps,@Part("img1") String img1,@Part("img2") String img2,@Part("img3") String img3,@Part("img4") String img4,@Part("img5") String img5,@Part("img6") String img6,@Part("img7") String img7,@Part("img8") String img8);*/
        /*Observable<CarSourceBean> releaseZhuanXianWuLiuToNet( @FieldMap Map<String , Object> usermaps,@Part("img1")  MultipartBody.Part img1,@Part("img2")  MultipartBody.Part img2,@Part("img3")  MultipartBody.Part img3,@Part("img4")  MultipartBody.Part img4,@Part("img5")  MultipartBody.Part img5,@Part("img6")  MultipartBody.Part img6,@Part("img7")  MultipartBody.Part img7,@Part("img8")  MultipartBody.Part img8);*/
        Observable<AuthCarBean> authCarToNet(@FieldMap Map<String, String> usermaps);
       /*车辆认证*/
       /*驾驶证认证*/
        //POST请求
        @FormUrlEncoded
        @POST("client/license_auth.php")
        /*Observable<CarSourceBean> releaseZhuanXianWuLiuToNet( @QueryMap Map<String , Object> usermaps,@Part("img1") String img1,@Part("img2") String img2,@Part("img3") String img3,@Part("img4") String img4,@Part("img5") String img5,@Part("img6") String img6,@Part("img7") String img7,@Part("img8") String img8);*/
        /*Observable<CarSourceBean> releaseZhuanXianWuLiuToNet( @FieldMap Map<String , Object> usermaps,@Part("img1")  MultipartBody.Part img1,@Part("img2")  MultipartBody.Part img2,@Part("img3")  MultipartBody.Part img3,@Part("img4")  MultipartBody.Part img4,@Part("img5")  MultipartBody.Part img5,@Part("img6")  MultipartBody.Part img6,@Part("img7")  MultipartBody.Part img7,@Part("img8")  MultipartBody.Part img8);*/
        Observable<AuthJiaShiZhengBean> authJiaShiZhengToNet(@FieldMap Map<String, String> usermaps);
       /*驾驶证认证*/
       /*企业认证*/
        //POST请求
        @FormUrlEncoded
        @POST("client/company_auth.php")
        /*Observable<CarSourceBean> releaseZhuanXianWuLiuToNet( @QueryMap Map<String , Object> usermaps,@Part("img1") String img1,@Part("img2") String img2,@Part("img3") String img3,@Part("img4") String img4,@Part("img5") String img5,@Part("img6") String img6,@Part("img7") String img7,@Part("img8") String img8);*/
        /*Observable<CarSourceBean> releaseZhuanXianWuLiuToNet( @FieldMap Map<String , Object> usermaps,@Part("img1")  MultipartBody.Part img1,@Part("img2")  MultipartBody.Part img2,@Part("img3")  MultipartBody.Part img3,@Part("img4")  MultipartBody.Part img4,@Part("img5")  MultipartBody.Part img5,@Part("img6")  MultipartBody.Part img6,@Part("img7")  MultipartBody.Part img7,@Part("img8")  MultipartBody.Part img8);*/
        Observable<AuthCompanyBean> authCompanyToNet(@FieldMap Map<String, String> usermaps);
       /*企业认证*/
       /*实名认证*/
        //POST请求
        @FormUrlEncoded
        @POST("client/alone_auth.php")
        /*Observable<CarSourceBean> releaseZhuanXianWuLiuToNet( @QueryMap Map<String , Object> usermaps,@Part("img1") String img1,@Part("img2") String img2,@Part("img3") String img3,@Part("img4") String img4,@Part("img5") String img5,@Part("img6") String img6,@Part("img7") String img7,@Part("img8") String img8);*/
        /*Observable<CarSourceBean> releaseZhuanXianWuLiuToNet( @FieldMap Map<String , Object> usermaps,@Part("img1")  MultipartBody.Part img1,@Part("img2")  MultipartBody.Part img2,@Part("img3")  MultipartBody.Part img3,@Part("img4")  MultipartBody.Part img4,@Part("img5")  MultipartBody.Part img5,@Part("img6")  MultipartBody.Part img6,@Part("img7")  MultipartBody.Part img7,@Part("img8")  MultipartBody.Part img8);*/
        Observable<AuthRealNameBean> authRealNameToNet(@FieldMap Map<String, String> usermaps);
       /*实名认证*/
       /*行驶证认证*/
        //POST请求
        @FormUrlEncoded
        @POST("client/license_auth.php")
        /*Observable<CarSourceBean> releaseZhuanXianWuLiuToNet( @QueryMap Map<String , Object> usermaps,@Part("img1") String img1,@Part("img2") String img2,@Part("img3") String img3,@Part("img4") String img4,@Part("img5") String img5,@Part("img6") String img6,@Part("img7") String img7,@Part("img8") String img8);*/
        /*Observable<CarSourceBean> releaseZhuanXianWuLiuToNet( @FieldMap Map<String , Object> usermaps,@Part("img1")  MultipartBody.Part img1,@Part("img2")  MultipartBody.Part img2,@Part("img3")  MultipartBody.Part img3,@Part("img4")  MultipartBody.Part img4,@Part("img5")  MultipartBody.Part img5,@Part("img6")  MultipartBody.Part img6,@Part("img7")  MultipartBody.Part img7,@Part("img8")  MultipartBody.Part img8);*/
        Observable<AuthXingShiZhengBean> authXingShiZhengToNet(@FieldMap Map<String, String> usermaps);
       /*行驶证认证*/
        /*认证情况*/
        @GET("client/auth_info.php")
        Observable<AuthInFoBean> getAuthInfoFromNet(@Query("login_id") String login_id);
        /*认证情况*/

    }

    /*public  void releaseZhuanXianWuLiuToNet(Map<String , Object> usermaps,String img1,String img2,String img3,String img4,String img5,String img6,String img7,String img8,Observer<CarSourceBean> observer){*/
    /*public  void releaseZhuanXianWuLiuToNet(Map<String , Object> usermaps, MultipartBody.Part img1, MultipartBody.Part img2, MultipartBody.Part img3, MultipartBody.Part img4, MultipartBody.Part img5, MultipartBody.Part img6, MultipartBody.Part img7, MultipartBody.Part img8,Observer<CarSourceBean> observer){*/
    public  void authCarToNet(Map<String , String> usermaps,Observer<AuthCarBean> observer){
        setSubscribe(service.authCarToNet(usermaps),observer);
    }
    public  void authJiaShiZhengToNet(Map<String , String> usermaps,Observer<AuthJiaShiZhengBean> observer){
        setSubscribe(service.authJiaShiZhengToNet(usermaps),observer);
    }
    public  void authCompanyToNet(Map<String , String> usermaps,Observer<AuthCompanyBean> observer){
        setSubscribe(service.authCompanyToNet(usermaps),observer);
    }
    public  void authRealNameToNet(Map<String , String> usermaps,Observer<AuthRealNameBean> observer){
        setSubscribe(service.authRealNameToNet(usermaps),observer);
    }
    public  void authXingShiZhengToNet(Map<String , String> usermaps,Observer<AuthXingShiZhengBean> observer){
        setSubscribe(service.authXingShiZhengToNet(usermaps),observer);
    }
    public  void getAuthInfoFromNet(String login_id,Observer<AuthInFoBean> observer){
        setSubscribe(service.getAuthInfoFromNet(login_id),observer);
    }



}
