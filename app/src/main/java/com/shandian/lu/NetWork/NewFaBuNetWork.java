package com.shandian.lu.NetWork;

import com.example.mynewslayoutlib.Bean.NewFaBuCheYuanBean;
import com.example.mynewslayoutlib.Bean.NewFaBuHuoYuanBean;
import com.example.mynewslayoutlib.Bean.NewFaBuPicBean;
import com.shandian.lu.Bean.GoodsSourceDetailBean;
import com.shandian.lu.NetWork.BaseFile.BaseNetWork;
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

public class NewFaBuNetWork extends BaseNetWork {

    protected  final NetService service = getRetrofit().create(NetService.class);
    private interface NetService{
        //设缓存有效期为1天
        final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
        //查询缓存的Cache-Control设置，使用缓存
        final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
        //查询网络的Cache-Control设置。不使用缓存
        final String CACHE_CONTROL_NETWORK = "max-age=0";
        /*上传图片*/
        //POST请求
        @FormUrlEncoded
        @POST("index.php/app/gonggong/tupianup")
      Observable<NewFaBuPicBean> upPicToNet(@FieldMap Map<String, String> usermaps);
       /*上传图片*/
       /*发布货源*/
        //POST请求
        @FormUrlEncoded
        @POST("index.php/app/baojia/huoyuanfabuxiugai")
      Observable<NewFaBuHuoYuanBean> faBuHuoYuanToNet(@FieldMap Map<String, Object> usermaps);
       /*发布货源*/
       /*修改货源*/
        //POST请求
        @FormUrlEncoded
        @POST("index.php/app/baojia/huoyuanfabuxiugai")
        Observable<NewFaBuHuoYuanBean> updateHuoYuanToNet(@Query("id") String id,@FieldMap Map<String, Object> usermaps);
       /*修改货源*/
       /*发布车源*/
        //POST请求
        @FormUrlEncoded
        @POST("index.php/app/baojia/cheyuanfabuxiugai")
      Observable<NewFaBuCheYuanBean> faBuOrUpdateCheYuanToNet(@FieldMap Map<String, Object> usermaps);
       /*发布车源*/

    }

    /*public  void releaseZhuanXianWuLiuToNet(Map<String , Object> usermaps,String img1,String img2,String img3,String img4,String img5,String img6,String img7,String img8,Observer<CarSourceBean> observer){*/
    /*public  void releaseZhuanXianWuLiuToNet(Map<String , Object> usermaps, MultipartBody.Part img1, MultipartBody.Part img2, MultipartBody.Part img3, MultipartBody.Part img4, MultipartBody.Part img5, MultipartBody.Part img6, MultipartBody.Part img7, MultipartBody.Part img8,Observer<CarSourceBean> observer){*/
    public  void upPicToNet(Map<String , String> usermaps,Observer<NewFaBuPicBean> observer){
        setSubscribe(service.upPicToNet(usermaps),observer);
    }
    public  void faBuHuoYuanToNet(Map<String , Object> usermaps,Observer<NewFaBuHuoYuanBean> observer){
        setSubscribe(service.faBuHuoYuanToNet(usermaps),observer);
    }
    public  void updateHuoYuanToNet(String id,Map<String , Object> usermaps,Observer<NewFaBuHuoYuanBean> observer){
        setSubscribe(service.updateHuoYuanToNet(id,usermaps),observer);
    }
    public  void faBuOrUpdateCheYuanToNet(Map<String , Object> usermaps,Observer<NewFaBuCheYuanBean> observer){
        setSubscribe(service.faBuOrUpdateCheYuanToNet(usermaps),observer);
    }


}
