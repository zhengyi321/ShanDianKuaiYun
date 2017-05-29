package com.zhyan.shandiankuaiyun.NetWork;

import com.zhyan.shandiankuaiyun.Bean.SecondCityChangeBean;
import com.zhyan.shandiankuaiyun.NetWork.BaseFile.BaseNetWork;
import com.zhyan.shandiankuaiyunlib.Bean.CityBean;
import com.zhyan.shandiankuaiyunlib.Bean.IsRegisterBean;
import com.zhyan.shandiankuaiyunlib.Bean.LoginBean;
import com.zhyan.shandiankuaiyunlib.Bean.MainIndexAdBean;
import com.zhyan.shandiankuaiyunlib.Bean.MyCarSourceBean;
import com.zhyan.shandiankuaiyunlib.Bean.MyHuoSourceBean;
import com.zhyan.shandiankuaiyunlib.Bean.MyMessageBean;
import com.zhyan.shandiankuaiyunlib.Bean.NearByDriverBean;
import com.zhyan.shandiankuaiyunlib.Bean.RegisterBean;
import com.zhyan.shandiankuaiyunlib.Bean.ThirdCityChangeBean;
import com.zhyan.shandiankuaiyunlib.Bean.UpdateResultBean;

import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;

/**
 * Created by az on 2017/4/26.
 */

public class MainIndexNetWork extends BaseNetWork {

    protected  final NetService service = getRetrofit().create(NetService.class);
    private interface NetService{
        //设缓存有效期为1天
        final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
        //查询缓存的Cache-Control设置，使用缓存
        final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
        //查询网络的Cache-Control设置。不使用缓存
        final String CACHE_CONTROL_NETWORK = "max-age=0";
        /*获取广告图片*/
        //GET请求
        @GET("client/advertise_v2.php")
        Observable<MainIndexAdBean> getAdFromNet();
       /*获取广告图片*/
       /*获取城市*/
       @GET("client/province.php")
        Observable<CityBean> getCityFromNet();
       /*获取城市*/
       /*获取二级城市*/
        @GET("client/city_had.php")
        Observable<SecondCityChangeBean> getSecondCityFromNet(@Query("province_id") String province_id);
        /*获取二级城市*/

        /*获取三级城市*/
        @GET("client/area.php")
        Observable<ThirdCityChangeBean> getThirdCityFromNet(@Query("cid") String cid);
        /*获取三级城市*/

        /*附近司机*/
        @GET("index.php/app/index/zhuayingyan")
        Observable<NearByDriverBean> getNearByDriverFromNet(@Query("lat")double lat,@Query("lng")double lng);
        /*附近司机*/
    }

    public  void getAdFromNet(Observer<MainIndexAdBean> observer){
        setSubscribe(service.getAdFromNet(),observer);
    }
    public  void getCityFromNet(Observer<CityBean> observer){
        setSubscribe(service.getCityFromNet(),observer);
    }
    public  void getSecondCityFromNet( String province_id,Observer<SecondCityChangeBean> observer){
        setSubscribe(service.getSecondCityFromNet(province_id),observer);
    }
    public  void getThirdCityFromNet( String cid,Observer<ThirdCityChangeBean> observer){
        setSubscribe(service.getThirdCityFromNet(cid),observer);
    }
    public  void getNearByDriverFromNet( double lat,double lng,Observer<NearByDriverBean> observer){
        setSubscribe(service.getNearByDriverFromNet(lat,lng),observer);
    }

}
