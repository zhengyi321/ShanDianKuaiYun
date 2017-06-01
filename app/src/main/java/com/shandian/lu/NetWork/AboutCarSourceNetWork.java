package com.shandian.lu.NetWork;

import com.shandian.lu.NetWork.BaseFile.BaseNetWork;
import com.zhyan.shandiankuaiyunlib.Bean.CarSourceDetailBean;
import com.zhyan.shandiankuaiyunlib.Bean.CarSourceSelectBean;
import com.zhyan.shandiankuaiyunlib.Bean.MyCarSourcesDeleteResultBean;
import com.zhyan.shandiankuaiyunlib.Bean.ZhuanXianWuliuCarSourceBean;

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

public class AboutCarSourceNetWork extends BaseNetWork {

    protected  final NetService service = getRetrofit().create(NetService.class);
    private interface NetService{
        //设缓存有效期为1天
        final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
        //查询缓存的Cache-Control设置，使用缓存
        final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
        //查询网络的Cache-Control设置。不使用缓存
        final String CACHE_CONTROL_NETWORK = "max-age=0";
        /*发布车源信息*/
        //POST请求
        @FormUrlEncoded
        @POST("client/menu_car_info_list.php")
        /*Observable<CarSourceBean> releaseZhuanXianWuLiuToNet( @QueryMap Map<String , Object> usermaps,@Part("img1") String img1,@Part("img2") String img2,@Part("img3") String img3,@Part("img4") String img4,@Part("img5") String img5,@Part("img6") String img6,@Part("img7") String img7,@Part("img8") String img8);*/
        /*Observable<CarSourceBean> releaseZhuanXianWuLiuToNet( @FieldMap Map<String , Object> usermaps,@Part("img1")  MultipartBody.Part img1,@Part("img2")  MultipartBody.Part img2,@Part("img3")  MultipartBody.Part img3,@Part("img4")  MultipartBody.Part img4,@Part("img5")  MultipartBody.Part img5,@Part("img6")  MultipartBody.Part img6,@Part("img7")  MultipartBody.Part img7,@Part("img8")  MultipartBody.Part img8);*/
        Observable<CarSourceSelectBean> getCarSourceFromNet(@FieldMap Map<String, String> usermaps);
       /*发布车源信息*/
        /*获取车源信息附近地址*/
        @GET("client/car_info_nearby_v3.php")
        Observable<CarSourceSelectBean> getCarSourceNearByFromNet(@Query("lng") String lng,@Query("lat") String lat,@Query("type_name") String type_name,@Query("type") String type);
        /*获取车源信息附近地址*/

        /*获取车辆信息*/
        @GET("client/menu_car_info_list_detail_v2.php")
        Observable<CarSourceDetailBean> getCarSourceDetailFromNet(@Query("id") String id,@Query("login_id") String login_id);
        /*获取车辆信息*/

        /*专线物流*/
        @FormUrlEncoded
        @POST("client/menu_car_info_list_type.php")
        /*Observable<CarSourceBean> releaseZhuanXianWuLiuToNet( @QueryMap Map<String , Object> usermaps,@Part("img1") String img1,@Part("img2") String img2,@Part("img3") String img3,@Part("img4") String img4,@Part("img5") String img5,@Part("img6") String img6,@Part("img7") String img7,@Part("img8") String img8);*/
        /*Observable<CarSourceBean> releaseZhuanXianWuLiuToNet( @FieldMap Map<String , Object> usermaps,@Part("img1")  MultipartBody.Part img1,@Part("img2")  MultipartBody.Part img2,@Part("img3")  MultipartBody.Part img3,@Part("img4")  MultipartBody.Part img4,@Part("img5")  MultipartBody.Part img5,@Part("img6")  MultipartBody.Part img6,@Part("img7")  MultipartBody.Part img7,@Part("img8")  MultipartBody.Part img8);*/
        Observable<ZhuanXianWuliuCarSourceBean> getZhuanXianWuLiuCarSourceFromNet(@FieldMap Map<String, String> usermaps);
        /*专线物流*/

        /*获取专线物流附近地址*/
        @GET("client/car_info_nearby_v3.php")
        Observable<ZhuanXianWuliuCarSourceBean> getZhuanXianWuLiuCarSourceNearByFromNet(@Query("lng") String lng,@Query("lat") String lat,@Query("type_name") String type_name,@Query("type") String type);
        /*获取专线物流附近地址*/

        /*删除车源*/
        @GET("client/delete_my_car_info.php")
        Observable<MyCarSourcesDeleteResultBean> deleteCarResourceToNet(@Query("id") String id);
        /*删除车源*/
    }

    /*public  void releaseZhuanXianWuLiuToNet(Map<String , Object> usermaps,String img1,String img2,String img3,String img4,String img5,String img6,String img7,String img8,Observer<CarSourceBean> observer){*/
    /*public  void releaseZhuanXianWuLiuToNet(Map<String , Object> usermaps, MultipartBody.Part img1, MultipartBody.Part img2, MultipartBody.Part img3, MultipartBody.Part img4, MultipartBody.Part img5, MultipartBody.Part img6, MultipartBody.Part img7, MultipartBody.Part img8,Observer<CarSourceBean> observer){*/
    public  void getCarSourceFromNet(Map<String , String> usermaps,Observer<CarSourceSelectBean> observer){
        setSubscribe(service.getCarSourceFromNet(usermaps),observer);
    }


    public  void getCarSourceNearByFromNet(String lng, String lat, String type_name, String type,Observer<CarSourceSelectBean> observer){
        setSubscribe(service.getCarSourceNearByFromNet(lng,lat,type_name,type),observer);
    }
    public  void getCarSourceDetailFromNet(String id, String login_id,Observer<CarSourceDetailBean> observer){
        setSubscribe(service.getCarSourceDetailFromNet(id,login_id),observer);
    }

    public  void getZhuanXianWuLiuCarSourceFromNet(Map<String , String> usermaps,Observer<ZhuanXianWuliuCarSourceBean> observer){
        setSubscribe(service.getZhuanXianWuLiuCarSourceFromNet(usermaps),observer);
    }

    public  void getZhuanXianWuLiuCarSourceNearByFromNet(String lng, String lat, String type_name, String type,Observer<ZhuanXianWuliuCarSourceBean> observer){
        setSubscribe(service.getZhuanXianWuLiuCarSourceNearByFromNet(lng,lat,type_name,type),observer);
    }


    public  void deleteCarResourceToNet(String id,Observer<MyCarSourcesDeleteResultBean> observer){
        setSubscribe(service.deleteCarResourceToNet(id),observer);
    }
}
