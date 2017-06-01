package com.shandian.lu.NetWork;

import com.shandian.lu.NetWork.BaseFile.BaseNetWork;
import com.shandian.lu.Bean.GoodsSourceDetailBean;
import com.zhyan.shandiankuaiyunlib.Bean.GoodsSourceBean;
import com.zhyan.shandiankuaiyunlib.Bean.MyGoodsSourcesDeleteResultBean;

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

public class AboutGoodsSourceNetWork extends BaseNetWork {

    protected  final NetService service = getRetrofit().create(NetService.class);
    private interface NetService{
        //设缓存有效期为1天
        final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
        //查询缓存的Cache-Control设置，使用缓存
        final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
        //查询网络的Cache-Control设置。不使用缓存
        final String CACHE_CONTROL_NETWORK = "max-age=0";
        /*查看货源信息*/
        //POST请求
        @FormUrlEncoded
        @POST("client/menu_good_info_list.php")
        /*Observable<CarSourceBean> releaseZhuanXianWuLiuToNet( @QueryMap Map<String , Object> usermaps,@Part("img1") String img1,@Part("img2") String img2,@Part("img3") String img3,@Part("img4") String img4,@Part("img5") String img5,@Part("img6") String img6,@Part("img7") String img7,@Part("img8") String img8);*/
        /*Observable<CarSourceBean> releaseZhuanXianWuLiuToNet( @FieldMap Map<String , Object> usermaps,@Part("img1")  MultipartBody.Part img1,@Part("img2")  MultipartBody.Part img2,@Part("img3")  MultipartBody.Part img3,@Part("img4")  MultipartBody.Part img4,@Part("img5")  MultipartBody.Part img5,@Part("img6")  MultipartBody.Part img6,@Part("img7")  MultipartBody.Part img7,@Part("img8")  MultipartBody.Part img8);*/
        Observable<GoodsSourceBean> getGoodsSourceFromNet(@FieldMap Map<String, String> usermaps);
       /*查看货源信息*/
        /*查看附件货源*/
       @GET("client/good_info_nearby_v2.php")
       Observable<GoodsSourceBean> getGoodsSourceNearByFromNet(@Query("lng") String lng,@Query("lat") String lat);
        /*查看附件货源*/

        /*查看货源详细信息*/
        @GET("client/good_info_detail_v2.php")
        Observable<GoodsSourceDetailBean> getGoodsSourceDetailFromNet(@Query("id") String id, @Query("login_id") String login_id);
        /*查看货源详细信息*/


        /*删除货源*/
        @GET("client/delete_my_goods_info.php")
        Observable<MyGoodsSourcesDeleteResultBean> deleteGoodsResourceToNet(@Query("id") String id);
        /*删除货源*/
    }

    /*public  void releaseZhuanXianWuLiuToNet(Map<String , Object> usermaps,String img1,String img2,String img3,String img4,String img5,String img6,String img7,String img8,Observer<CarSourceBean> observer){*/
    /*public  void releaseZhuanXianWuLiuToNet(Map<String , Object> usermaps, MultipartBody.Part img1, MultipartBody.Part img2, MultipartBody.Part img3, MultipartBody.Part img4, MultipartBody.Part img5, MultipartBody.Part img6, MultipartBody.Part img7, MultipartBody.Part img8,Observer<CarSourceBean> observer){*/
    public  void getGoodsSourceFromNet(Map<String , String> usermaps,Observer<GoodsSourceBean> observer){
        setSubscribe(service.getGoodsSourceFromNet(usermaps),observer);
    }

    public  void getGoodsSourceNearByFromNet( String lng, String lat,Observer<GoodsSourceBean> observer){
        setSubscribe(service.getGoodsSourceNearByFromNet(lng,lat),observer);
    }
    public  void getGoodsSourceDetailFromNet( String id, String login_id,Observer<GoodsSourceDetailBean> observer){
        setSubscribe(service.getGoodsSourceDetailFromNet(id,login_id),observer);
    }

    public  void deleteGoodsResourceToNet(String id,Observer<MyGoodsSourcesDeleteResultBean> observer){
        setSubscribe(service.deleteGoodsResourceToNet(id),observer);
    }
}
