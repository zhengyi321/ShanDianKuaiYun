package com.shandian.lu.NetWork;

import com.example.mynewslayoutlib.Bean.NewCheYuanDetailBean;
import com.example.mynewslayoutlib.Bean.NewCheYuanListBean;
import com.example.mynewslayoutlib.Bean.NewHuoYuanListBean;
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

public class NewCheHuoListNetWork extends BaseNetWork {

    protected  final NetService service = getRetrofit().create(NetService.class);
    private interface NetService{
        //设缓存有效期为1天
        final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
        //查询缓存的Cache-Control设置，使用缓存
        final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
        //查询网络的Cache-Control设置。不使用缓存
        final String CACHE_CONTROL_NETWORK = "max-age=0";

        /*货源列表*/
        @GET("index.php/app/chyuan/huoyuan")
        Observable<NewHuoYuanListBean> getHuoListFromNet(@Query("type_name") String type_name, @Query("lat") String lat, @Query("lng") String lng, @Query("p") String p);
        /*货源列表*/
        /*车源列表*/
        @GET("index.php/app/chyuan/cheyuan")
        Observable<NewCheYuanListBean> getCheListFromNet(@Query("type_name") String type_name, @Query("lat") String lat, @Query("lng") String lng, @Query("p") String p);
        /*车源列表*/
        /*车源详情*/
        @GET("index.php/app/chyuan/cheyuanxq")
        Observable<NewCheYuanDetailBean> getCheYuanDetailFromNet(@Query("cyid") String cyid);
        /*车源列表*/

    }

    /*public  void releaseZhuanXianWuLiuToNet(Map<String , Object> usermaps,String img1,String img2,String img3,String img4,String img5,String img6,String img7,String img8,Observer<CarSourceBean> observer){*/
    /*public  void releaseZhuanXianWuLiuToNet(Map<String , Object> usermaps, MultipartBody.Part img1, MultipartBody.Part img2, MultipartBody.Part img3, MultipartBody.Part img4, MultipartBody.Part img5, MultipartBody.Part img6, MultipartBody.Part img7, MultipartBody.Part img8,Observer<CarSourceBean> observer){*/



    public  void getHuoListFromNet(String type_name, String lat, String lng, String p,Observer<NewHuoYuanListBean> observer){
        setSubscribe(service.getHuoListFromNet(type_name,lat,lng,p),observer);
    }
    public  void getCheListFromNet(String type_name, String lat, String lng, String p,Observer<NewCheYuanListBean> observer){
        setSubscribe(service.getCheListFromNet(type_name,lat,lng,p),observer);
    }
    public  void getCheYuanDetailFromNet(String cyid,Observer<NewCheYuanDetailBean> observer){
        setSubscribe(service.getCheYuanDetailFromNet(cyid),observer);
    }

}
