package com.shandian.lu.NetWork;

import com.example.mynewslayoutlib.Bean.JiFenBean;
import com.example.mynewslayoutlib.Bean.ResetPassBean;
import com.shandian.lu.NetWork.BaseFile.BaseNetWork;
import com.zhyan.shandiankuaiyunlib.Bean.QianDaoInitBean;
import com.zhyan.shandiankuaiyunlib.Bean.QianDaoSubmitResultBean;
import com.zhyan.shandiankuaiyunlib.Bean.UpdatePassBean;

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

public class JiFenShangChengNetWork extends BaseNetWork {

    protected  final NetService service = getRetrofit().create(NetService.class);
    private interface NetService{
        //设缓存有效期为1天
        final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
        //查询缓存的Cache-Control设置，使用缓存
        final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
        //查询网络的Cache-Control设置。不使用缓存
        final String CACHE_CONTROL_NETWORK = "max-age=0";
        /*获取积分*/
        //POST请求
        @FormUrlEncoded
        @POST("index.php/app/index/tixian")
        Observable<JiFenBean> getJiFenFromNet(@FieldMap Map<String,String> paramMap);
       /*获取积分*/

       /*签到初始化*/

       @GET("index.php/app/index/jifenqiandao")
        Observable<QianDaoInitBean> qianDaoInitFromNet(@Query("id")String id);
       /*签到初始化*/
        /*签到  sign 参数*/
        @FormUrlEncoded
        @POST("index.php/app/index/jifenqiandao")
        Observable<QianDaoSubmitResultBean> qianDaoToNet(@Query("id")String id, @FieldMap Map<String, String> paramMap);
        /*签到*/
    }

    public  void getJiFenFromNet(Map<String,String> paramMap, Observer<JiFenBean> observer){
        setSubscribe(service.getJiFenFromNet(paramMap),observer);
    }
    public  void qianDaoInitFromNet(String id, Observer<QianDaoInitBean> observer){
        setSubscribe(service.qianDaoInitFromNet(id),observer);
    }

    public  void qianDaoToNet(String id,Map<String,String> paramMap, Observer<QianDaoSubmitResultBean> observer){
        setSubscribe(service.qianDaoToNet(id,paramMap),observer);
    }

}
