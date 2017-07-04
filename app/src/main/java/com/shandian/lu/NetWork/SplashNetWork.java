package com.shandian.lu.NetWork;

import com.example.mynewslayoutlib.Bean.NewGeRenXinXiSubmitBean;
import com.example.mynewslayoutlib.Bean.NewMyTuiJianRenBean;
import com.example.mynewslayoutlib.Bean.NewQrCodeSubmitBean;
import com.example.mynewslayoutlib.Bean.NewSplashBean;
import com.shandian.lu.NetWork.BaseFile.BaseNetWork;

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

public class SplashNetWork extends BaseNetWork {

    protected  final NetService service = getRetrofit().create(NetService.class);
    private interface NetService{
        //设缓存有效期为1天
        final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
        //查询缓存的Cache-Control设置，使用缓存
        final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
        //查询网络的Cache-Control设置。不使用缓存
        final String CACHE_CONTROL_NETWORK = "max-age=0";

/*        *//*二维码提交*//*
        @FormUrlEncoded
        @POST("index.php/app/chyuan/ewmsm")
        Observable<NewQrCodeSubmitBean> submitNewQrCodeToNet(@FieldMap Map<String, Object> paramMap);
        *//*二维码提交*/
        /*我的推荐人*/
        @GET("index.php/app/chyuan/kaishiimg")
        Observable<NewSplashBean> getSpalshFromNet();

        /*个人信息提交*/
        @FormUrlEncoded
        @POST("index.php/app/chyuan/gerenxinxitj")
        Observable<NewGeRenXinXiSubmitBean> submitNewGeRenXinXiToNet(@FieldMap Map<String, Object> paramMap);
        /*个人信息提交*/



    }


    public  void getSpalshFromNet(Observer<NewSplashBean> observer){
        setSubscribe(service.getSpalshFromNet(),observer);
    }
/*    public  void getMyTuiJianRenFromNet(String id,Observer<NewMyTuiJianRenBean> observer){
        setSubscribe(service.getMyTuiJianRenFromNet(id),observer);
    }*/
}
