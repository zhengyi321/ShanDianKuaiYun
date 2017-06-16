package com.shandian.lu.NetWork;

import com.example.mynewslayoutlib.Bean.NewFaBuCheYuanBean;
import com.example.mynewslayoutlib.Bean.NewFaBuHuoYuanBean;
import com.example.mynewslayoutlib.Bean.NewFaBuPicBean;
import com.example.mynewslayoutlib.Bean.NewMyWalletHistoryListBean;
import com.example.mynewslayoutlib.Bean.NewTiXianBean;
import com.shandian.lu.NetWork.BaseFile.BaseNetWork;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;

/**
 * Created by az on 2017/4/26.
 */

public class MyWalletNetWork extends BaseNetWork {

    protected  final NetService service = getRetrofit().create(NetService.class);
    private interface NetService{
        //设缓存有效期为1天
        final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
        //查询缓存的Cache-Control设置，使用缓存
        final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
        //查询网络的Cache-Control设置。不使用缓存
        final String CACHE_CONTROL_NETWORK = "max-age=0";
        /*收支明细*/
        //POST请求
        @FormUrlEncoded
        @POST("index.php/app/index/shouzhimingxi")
      Observable<NewMyWalletHistoryListBean> getMyWalletHistoryListFromNet(@FieldMap Map<String, String> usermaps);
       /*收支明细*/
       /*钱包提现*/
        //POST请求
        @FormUrlEncoded
        @POST("index.php/app/index/qianbaotixian")
      Observable<NewTiXianBean> tiXianFromNet(@FieldMap Map<String, String> usermaps);
       /*钱包提现*/
       /*修改货源*/
        //POST请求
        @FormUrlEncoded
        @POST("index.php/app/baojia/huoyuanfabuxiugai")
        Observable<NewFaBuHuoYuanBean> updateHuoYuanToNet(@Query("id") String id, @FieldMap Map<String, Object> usermaps);
       /*修改货源*/
       /*修改车源*/
        //POST请求
        @FormUrlEncoded
        @POST("index.php/app/baojia/cheyuanfabuxiugai")
        Observable<NewFaBuCheYuanBean> updateCheYuanToNet(@Query("id") String id, @FieldMap Map<String, Object> usermaps);
       /*修改车源*/
       /*发布车源*/
        //POST请求
        @FormUrlEncoded
        @POST("index.php/app/baojia/cheyuanfabuxiugai")
      Observable<NewFaBuCheYuanBean> faBuOrUpdateCheYuanToNet(@FieldMap Map<String, Object> usermaps);
       /*发布车源*/

    }

    /*public  void releaseZhuanXianWuLiuToNet(Map<String , Object> usermaps,String img1,String img2,String img3,String img4,String img5,String img6,String img7,String img8,Observer<CarSourceBean> observer){*/
    /*public  void releaseZhuanXianWuLiuToNet(Map<String , Object> usermaps, MultipartBody.Part img1, MultipartBody.Part img2, MultipartBody.Part img3, MultipartBody.Part img4, MultipartBody.Part img5, MultipartBody.Part img6, MultipartBody.Part img7, MultipartBody.Part img8,Observer<CarSourceBean> observer){*/
    public  void getMyWalletHistoryListFromNet(Map<String , String> usermaps,Observer<NewMyWalletHistoryListBean> observer){
        setSubscribe(service.getMyWalletHistoryListFromNet(usermaps),observer);
    }
    public  void tiXianFromNet(Map<String , String> usermaps,Observer<NewTiXianBean> observer){
        setSubscribe(service.tiXianFromNet(usermaps),observer);
    }
    public  void updateHuoYuanToNet(String id,Map<String , Object> usermaps,Observer<NewFaBuHuoYuanBean> observer){
        setSubscribe(service.updateHuoYuanToNet(id,usermaps),observer);
    }
    public  void updateCheYuanToNet(String id,Map<String , Object> usermaps,Observer<NewFaBuCheYuanBean> observer){
        setSubscribe(service.updateCheYuanToNet(id,usermaps),observer);
    }
    public  void faBuOrUpdateCheYuanToNet(Map<String , Object> usermaps,Observer<NewFaBuCheYuanBean> observer){
        setSubscribe(service.faBuOrUpdateCheYuanToNet(usermaps),observer);
    }


}
