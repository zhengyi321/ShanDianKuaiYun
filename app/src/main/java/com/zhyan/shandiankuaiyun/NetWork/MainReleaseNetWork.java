package com.zhyan.shandiankuaiyun.NetWork;

import com.zhyan.shandiankuaiyun.Bean.SecondCityChangeBean;
import com.zhyan.shandiankuaiyun.NetWork.BaseFile.BaseNetWork;
import com.zhyan.shandiankuaiyunlib.Bean.CarSourceBean;
import com.zhyan.shandiankuaiyunlib.Bean.CityBean;
import com.zhyan.shandiankuaiyunlib.Bean.MainIndexAdBean;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;
import rx.Observer;

/**
 * Created by az on 2017/4/26.
 */

public class MainReleaseNetWork extends BaseNetWork {

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
        @POST("client/car_info_add_v4.php")
        /*Observable<CarSourceBean> releaseZhuanXianWuLiuToNet( @QueryMap Map<String , Object> usermaps,@Part("img1") String img1,@Part("img2") String img2,@Part("img3") String img3,@Part("img4") String img4,@Part("img5") String img5,@Part("img6") String img6,@Part("img7") String img7,@Part("img8") String img8);*/
        /*Observable<CarSourceBean> releaseZhuanXianWuLiuToNet( @FieldMap Map<String , Object> usermaps,@Part("img1")  MultipartBody.Part img1,@Part("img2")  MultipartBody.Part img2,@Part("img3")  MultipartBody.Part img3,@Part("img4")  MultipartBody.Part img4,@Part("img5")  MultipartBody.Part img5,@Part("img6")  MultipartBody.Part img6,@Part("img7")  MultipartBody.Part img7,@Part("img8")  MultipartBody.Part img8);*/
        Observable<CarSourceBean> releaseCarSourceToNet( @FieldMap Map<String , String> usermaps);
       /*发布车源信息*/

       /*发布货源*/
       @FormUrlEncoded
       @POST("client/good_info_add_v3.php")
        /*Observable<CarSourceBean> releaseZhuanXianWuLiuToNet( @QueryMap Map<String , Object> usermaps,@Part("img1") String img1,@Part("img2") String img2,@Part("img3") String img3,@Part("img4") String img4,@Part("img5") String img5,@Part("img6") String img6,@Part("img7") String img7,@Part("img8") String img8);*/
        /*Observable<CarSourceBean> releaseZhuanXianWuLiuToNet( @FieldMap Map<String , Object> usermaps,@Part("img1")  MultipartBody.Part img1,@Part("img2")  MultipartBody.Part img2,@Part("img3")  MultipartBody.Part img3,@Part("img4")  MultipartBody.Part img4,@Part("img5")  MultipartBody.Part img5,@Part("img6")  MultipartBody.Part img6,@Part("img7")  MultipartBody.Part img7,@Part("img8")  MultipartBody.Part img8);*/
       Observable<CarSourceBean> releaseGoodsSourceToNet( @FieldMap Map<String , String> usermaps);
       /*发布货源*/

    }

    /*public  void releaseZhuanXianWuLiuToNet(Map<String , Object> usermaps,String img1,String img2,String img3,String img4,String img5,String img6,String img7,String img8,Observer<CarSourceBean> observer){*/
    /*public  void releaseZhuanXianWuLiuToNet(Map<String , Object> usermaps, MultipartBody.Part img1, MultipartBody.Part img2, MultipartBody.Part img3, MultipartBody.Part img4, MultipartBody.Part img5, MultipartBody.Part img6, MultipartBody.Part img7, MultipartBody.Part img8,Observer<CarSourceBean> observer){*/
    public  void releaseCarSourceToNet(Map<String , String> usermaps,Observer<CarSourceBean> observer){
        setSubscribe(service.releaseCarSourceToNet(usermaps),observer);
    }
    public  void releaseGoodsSourceToNet(Map<String , String> usermaps,Observer<CarSourceBean> observer){
        setSubscribe(service.releaseGoodsSourceToNet(usermaps),observer);
    }


}
