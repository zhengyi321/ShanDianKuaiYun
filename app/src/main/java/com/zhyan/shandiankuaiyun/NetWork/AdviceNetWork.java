package com.zhyan.shandiankuaiyun.NetWork;

import com.zhyan.shandiankuaiyun.NetWork.BaseFile.BaseNetWork;
import com.zhyan.shandiankuaiyunlib.Bean.AdviceDetailBean;
import com.zhyan.shandiankuaiyunlib.Bean.AdviceInfoListBean;
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

public class AdviceNetWork extends BaseNetWork {

    protected  final NetService service = getRetrofit().create(NetService.class);
    private interface NetService{
        //设缓存有效期为1天
        final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
        //查询缓存的Cache-Control设置，使用缓存
        final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
        //查询网络的Cache-Control设置。不使用缓存
        final String CACHE_CONTROL_NETWORK = "max-age=0";

       /*行驶证认证*/
        //POST请求
        @FormUrlEncoded
        @POST("client/license_auth.php")
        /*Observable<CarSourceBean> releaseZhuanXianWuLiuToNet( @QueryMap Map<String , Object> usermaps,@Part("img1") String img1,@Part("img2") String img2,@Part("img3") String img3,@Part("img4") String img4,@Part("img5") String img5,@Part("img6") String img6,@Part("img7") String img7,@Part("img8") String img8);*/
        /*Observable<CarSourceBean> releaseZhuanXianWuLiuToNet( @FieldMap Map<String , Object> usermaps,@Part("img1")  MultipartBody.Part img1,@Part("img2")  MultipartBody.Part img2,@Part("img3")  MultipartBody.Part img3,@Part("img4")  MultipartBody.Part img4,@Part("img5")  MultipartBody.Part img5,@Part("img6")  MultipartBody.Part img6,@Part("img7")  MultipartBody.Part img7,@Part("img8")  MultipartBody.Part img8);*/
        Observable<AuthXingShiZhengBean> authXingShiZhengToNet(@FieldMap Map<String, String> usermaps);
       /*行驶证认证*/
        /*咨询详情*/
        @GET("client/information_detail.php")
        Observable<AdviceDetailBean> getAdviceDetailFromNet(@Query("id") String id);
        /*咨询详情*/
        /*咨询列表*/
        @GET("client/information_list.php")
        Observable<AdviceInfoListBean> getAdviceListFromNet();
        /*咨询详情*/

    }


    public  void authXingShiZhengToNet(Map<String , String> usermaps,Observer<AuthXingShiZhengBean> observer){
        setSubscribe(service.authXingShiZhengToNet(usermaps),observer);
    }
    public  void getAdviceDetailFromNet(String id,Observer<AdviceDetailBean> observer){
        setSubscribe(service.getAdviceDetailFromNet(id),observer);
    }
    public  void getAdviceListFromNet(Observer<AdviceInfoListBean> observer){
        setSubscribe(service.getAdviceListFromNet(),observer);
    }



}
