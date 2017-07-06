package com.shandian.lu.NetWork;

import com.example.mynewslayoutlib.Bean.NewGeRenXinXiBean;
import com.example.mynewslayoutlib.Bean.NewGeRenXinXiSubmitBean;
import com.example.mynewslayoutlib.Bean.NewLoginBean;
import com.example.mynewslayoutlib.Bean.NewRegisterBean;
import com.example.mynewslayoutlib.Bean.NewSiJiLocBean;
import com.example.mynewslayoutlib.Bean.NewTuiSongStatusBean;
import com.example.mynewslayoutlib.Bean.NewTuiSongStatusSubmitBean;
import com.shandian.lu.NetWork.BaseFile.BaseNetWork;
import com.zhyan.shandiankuaiyunlib.Bean.IsRegisterBean;
import com.zhyan.shandiankuaiyunlib.Bean.LoginBean;
import com.zhyan.shandiankuaiyunlib.Bean.MyCarSourceBean;
import com.zhyan.shandiankuaiyunlib.Bean.MyHuoSourceBean;
import com.zhyan.shandiankuaiyunlib.Bean.MyMessageBean;
import com.zhyan.shandiankuaiyunlib.Bean.RegisterBean;
import com.zhyan.shandiankuaiyunlib.Bean.UpdateResultBean;

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

public class UserNetWork extends BaseNetWork {

    protected  final NetService service = getRetrofit().create(NetService.class);
    private interface NetService{
        //设缓存有效期为1天
        final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
        //查询缓存的Cache-Control设置，使用缓存
        final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
        //查询网络的Cache-Control设置。不使用缓存
        final String CACHE_CONTROL_NETWORK = "max-age=0";
        /*用户登录*/
        //GET请求
        @GET("client/denglu.php")
        Observable<LoginBean> userLogin(@Query("mobile") String mobile, @Query("password") String password);
       /*用户登录*/

       /*用户登录新接口*/
        @FormUrlEncoded
        @POST("index.php/app/chyuan/denglu")
        Observable<NewLoginBean> userLoginToNetNew(@FieldMap Map<String,String> paramMap);
       /*用户登录新接口*/
        /*检测用户名是否已经被注册*/
       @GET("client/mobile.php")
       Observable<IsRegisterBean> isRegisterByNet(@Query("mobile") String mobile);
        /*检测用户名是否已经被注册*/

        /*注册*/
        @GET("client/register.php")
        Observable<RegisterBean> registerToNet(@Query("mobile") String mobile, @Query("password") String password, @Query("code") String code, @Query("name") String name);
        /*注册*/
       /*新注册*/
        @FormUrlEncoded
        @POST("index.php/app/chyuan/zhuce")
        Observable<NewRegisterBean> newRegisterToNet(@FieldMap Map<String,String> paramMap);
       /*新注册*/

        /*获取头像*/
        @GET("client/my_info_list.php")
        Observable<MyMessageBean> getMyMessageFromNet(@Query("login_id") String login_id);
        /*获取头像*/
        /*修改头像*/
        @FormUrlEncoded
        @POST("client/update_info.php")
        Observable<UpdateResultBean> updateMyMessageToNet(@FieldMap Map<String,String> paramMap);
        /*修改头像*/

        /*我的货源*/
        @GET("client/my_goods_info.php")
        Observable<MyHuoSourceBean> getMyGoodsSourceFromNet(@Query("login_id") String login_id);
        /*我的货源*/
        /*我的车源*/
        @GET("client/my_car_info.php")
        Observable<MyCarSourceBean> getMyCarSourceFromNet(@Query("login_id") String login_id);
        /*我的车源*/



        /*司机实时定位*/
        @FormUrlEncoded
        @POST("index.php/app/chyuan/huoqusiji")
        Observable<NewSiJiLocBean> getSiJiLocFromNet(@FieldMap Map<String,String> paramMap);
        /*司机实时定位*/
        /*获取个人信息*/
        @FormUrlEncoded
        @POST("index.php/app/chyuan/gerenxinxi")
        Observable<NewGeRenXinXiBean> getNewGeRenXinXiFromNet(@FieldMap Map<String,String> paramMap);
        /*获取个人信息*/
        /*个人信息提交*/
        @FormUrlEncoded
        @POST("index.php/app/chyuan/gerenxinxitj")
        Observable<NewGeRenXinXiSubmitBean> submitNewGeRenXinXiToNet(@FieldMap Map<String,Object> paramMap);
        /*个人信息提交*/
        /*获取消息推送设置*/
        @FormUrlEncoded
        @POST("index.php/app/baojia/tszt")
        Observable<NewTuiSongStatusBean> getTuiSongStatusFromNet(@FieldMap Map<String,Object> paramMap);
        /*获取消息推送设置*/
        /*消息推送设置提交*/
        @FormUrlEncoded
        @POST("index.php/app/baojia/tszt")
        Observable<NewTuiSongStatusSubmitBean> submitTuiSongStatusToNet(@FieldMap Map<String,Object> paramMap);
        /*消息推送设置提交*/



    }

    public  void userLogin(String mobile, String password, Observer<LoginBean> observer){
        setSubscribe(service.userLogin(mobile, password),observer);
    }

    public  void userLoginToNetNew(Map<String,String> paramMap, Observer<NewLoginBean> observer){
        setSubscribe(service.userLoginToNetNew(paramMap),observer);
    }


    public  void isRegisterByNet(String mobile ,Observer<IsRegisterBean> observer){
        setSubscribe(service.isRegisterByNet(mobile),observer);
    }
    public  void registerToNet(String mobile ,String password,String code,String name,Observer<RegisterBean> observer){
        setSubscribe(service.registerToNet(mobile,password,code,name),observer);
    }
    public  void newRegisterToNet(Map<String,String> paramMap, Observer<NewRegisterBean> observer){
        setSubscribe(service.newRegisterToNet(paramMap),observer);
    }
    public  void getMyMessageFromNet(String login_id,Observer<MyMessageBean> observer){
        setSubscribe(service.getMyMessageFromNet(login_id),observer);
    }
    public  void updateMyMessageToNet(Map<String,String> paramMap,Observer<UpdateResultBean> observer){
        setSubscribe(service.updateMyMessageToNet(paramMap),observer);
    }

    public  void getMyGoodsSourceFromNet(String login_id,Observer<MyHuoSourceBean> observer){
        setSubscribe(service.getMyGoodsSourceFromNet(login_id),observer);
    }
    public  void getMyCarSourceFromNet(String login_id,Observer<MyCarSourceBean> observer){
        setSubscribe(service.getMyCarSourceFromNet(login_id),observer);
    }
    public  void getSiJiLocFromNet(Map<String,String> paramMap,Observer<NewSiJiLocBean> observer){
        setSubscribe(service.getSiJiLocFromNet(paramMap),observer);
    }
    public  void getNewGeRenXinXiFromNet(Map<String,String> paramMap,Observer<NewGeRenXinXiBean> observer){
        setSubscribe(service.getNewGeRenXinXiFromNet(paramMap),observer);
    }
    public  void submitNewGeRenXinXiToNet(Map<String,Object> paramMap,Observer<NewGeRenXinXiSubmitBean> observer){
        setSubscribe(service.submitNewGeRenXinXiToNet(paramMap),observer);
    }
    public  void getTuiSongStatusFromNet(Map<String,Object> paramMap,Observer<NewTuiSongStatusBean> observer){
        setSubscribe(service.getTuiSongStatusFromNet(paramMap),observer);
    }
    public  void submitTuiSongStatusToNet(Map<String,Object> paramMap,Observer<NewTuiSongStatusSubmitBean> observer){
        setSubscribe(service.submitTuiSongStatusToNet(paramMap),observer);
    }
}
