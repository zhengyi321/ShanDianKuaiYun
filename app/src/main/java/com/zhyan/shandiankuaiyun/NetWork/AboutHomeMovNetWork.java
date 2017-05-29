package com.zhyan.shandiankuaiyun.NetWork;

import com.zhyan.shandiankuaiyun.NetWork.BaseFile.BaseNetWork;
import com.zhyan.shandiankuaiyunlib.Bean.CarSourceDetailBean;
import com.zhyan.shandiankuaiyunlib.Bean.CarSourceSelectBean;
import com.zhyan.shandiankuaiyunlib.Bean.HomeMovingBean;
import com.zhyan.shandiankuaiyunlib.Bean.StreetListBean;
import com.zhyan.shandiankuaiyunlib.Bean.ZhuanXianWuliuCarSourceBean;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;
import rx.Observer;

/**
 * Created by az on 2017/4/26.
 */

public class AboutHomeMovNetWork extends BaseNetWork {

    protected  final NetService service = getRetrofit().create(NetService.class);
    private interface NetService{
        //设缓存有效期为1天
        final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
        //查询缓存的Cache-Control设置，使用缓存
        final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
        //查询网络的Cache-Control设置。不使用缓存
        final String CACHE_CONTROL_NETWORK = "max-age=0";

   /*     *//*获取搬家信息*//*
        @GET("client/home_moving_v2.php")
        Observable<HomeMovingBean> getHomeMovingDataFromNet(@Query("name") String name, @Query("type") String type, @Query("city_name") String city_name, @Query("type_name") String type_name, @Query("page") String page);
        *//*获取搬家信息*/
        /*获取搬家信息*/
        @GET("client/home_moving_v2.php")
        Observable<HomeMovingBean> getHomeMovingDataFromNet(@QueryMap Map<String,String> paramMap);
        /*获取搬家信息*/

        /*获取街道信息*/
        @GET("client/street_list_v2.php")
        Observable<StreetListBean> getStreetListFromNet(@Query("type") String type, @Query("name") String name);
        /*获取街道信息*/

        /*获取附近地址*/
        @GET("client/car_info_nearby_v3.php")
        Observable<HomeMovingBean> getNearByFromNet(@Query("lng") String lng,@Query("lat") String lat,@Query("type_name") String type_name,@Query("type") String type);
        /*获取附近地址*/

    }



    public  void getStreetListFromNet(String type, String name,Observer<StreetListBean> observer){
        setSubscribe(service.getStreetListFromNet(type,name),observer);
    }


   /* public  void getHomeMovingDataFromNet(String name, String type, String city_name, String type_name, String page,Observer<HomeMovingBean> observer){
        setSubscribe(service.getHomeMovingDataFromNet(name,city_name,type_name,type,page),observer);
    }*/
    public  void getHomeMovingDataFromNet(Map<String,String> paramMap,Observer<HomeMovingBean> observer){
        setSubscribe(service.getHomeMovingDataFromNet(paramMap),observer);
    }

    public  void getNearByFromNet(String lng, String lat, String type_name, String type,Observer<HomeMovingBean> observer){
        setSubscribe(service.getNearByFromNet(lng,lat,type_name,type),observer);
    }
}
