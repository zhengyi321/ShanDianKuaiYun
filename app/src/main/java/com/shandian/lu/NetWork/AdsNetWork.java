package com.shandian.lu.NetWork;

import com.example.mynewslayoutlib.Bean.NewAdsBean;
import com.shandian.lu.NetWork.BaseFile.BaseNetWork;
import com.zhyan.shandiankuaiyunlib.Bean.AdviceDetailBean;
import com.zhyan.shandiankuaiyunlib.Bean.AdviceInfoListBean;
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

public class AdsNetWork extends BaseNetWork {

    protected  final NetService service = getRetrofit().create(NetService.class);
    private interface NetService{
        //设缓存有效期为1天
        final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
        //查询缓存的Cache-Control设置，使用缓存
        final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
        //查询网络的Cache-Control设置。不使用缓存
        final String CACHE_CONTROL_NETWORK = "max-age=0";


        /*获取广告*/
        @GET("index.php/app/guanggao/gg")
        Observable<NewAdsBean> getAdsFromNet(@Query("lx") String lx);
        /*获取广告*/


    }


    public  void getAdsFromNet(String lx,Observer<NewAdsBean> observer){
        setSubscribe(service.getAdsFromNet(lx),observer);
    }



}
