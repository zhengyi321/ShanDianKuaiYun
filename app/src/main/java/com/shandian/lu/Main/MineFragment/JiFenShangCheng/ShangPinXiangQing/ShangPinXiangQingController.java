package com.shandian.lu.Main.MineFragment.JiFenShangCheng.ShangPinXiangQing;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.MineFragment.JiFenShangCheng.ShangPinXiangQing.TianXieShouHuoDiZhi.TianXieShouHuoDiZhiActivity;
import com.shandian.lu.NetWork.CompanyNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.JiFenDuiHuanBean;
import com.zhyan.shandiankuaiyunlib.Bean.JiFenGoodsDetailBean;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by az on 2017/5/5.
 */

public class ShangPinXiangQingController extends BaseController {

    private String spid = "";

    @BindView(R.id.rly_main_mine_jifenshangcheng_shangpinxiangqing_back)
    RelativeLayout rlyMainMineJiFenShangChengShangPinXiangQingBack;
    @OnClick(R.id.rly_main_mine_jifenshangcheng_shangpinxiangqing_back)
    public void rlyMainMineJiFenShangChengShangPinXiangQingBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.lly_main_mine_jifenshangcheng_shangpinxiangqing_tianxieshouhuodizhi)
    LinearLayout llyMainMineJiFenShangChengShangPinXiangQingTianXieShouHuoDiZhi;
    @OnClick(R.id.lly_main_mine_jifenshangcheng_shangpinxiangqing_tianxieshouhuodizhi)
    public void llyMainMineJiFenShangChengShangPinXiangQingTianXieShouHuoDiZhiOnclick(){
        Intent intent = new Intent(activity, TianXieShouHuoDiZhiActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("tel",tel);
        intent.putExtra("addr",addr);
        activity.startActivity(intent);
    }

    @BindView(R.id.iv_main_mine_jifenshangcheng_shangpinxiangqing)
    ImageView ivMainMineJiFenShangChengShangPinXiangQing;
    @BindView(R.id.tv_main_mine_jifenshangcheng_shangpinxiangqing_title)
    TextView tvMainMineJiFenShangChengShangPinXiangQingTitle;

    @BindView(R.id.tv_main_mine_jifenshangcheng_shangpinxiangqing_jifen)
    TextView tvMainMineJiFenShangChengShangPinXiangQingJiFen;
    @BindView(R.id.tv_main_mine_jifenshangcheng_shangpinxiangqing_yuanjifen)
    TextView tvMainMineJiFenShangChengShangPinXiangQingYuanJiFen;
    @BindView(R.id.tv_main_mine_jifenshangcheng_shangpinxiangqing_yunfei)
    TextView tvMainMineJiFenShangChengShangPinXiangQingYuanYunFei;
    @BindView(R.id.tv_main_mine_jifenshangcheng_shangpinxiangqing_addr)
    TextView tvMainMineJiFenShangChengShangPinXiangQingAddr;
    @BindView(R.id.tv_main_mine_jifenshangcheng_shangpinxiangqing_pp)
    TextView tvMainMineJiFenShangChengShangPinXiangQingPP;
    @BindView(R.id.tv_main_mine_jifenshangcheng_shangpinxiangqing_xh)
    TextView tvMainMineJiFenShangChengShangPinXiangQingXH;
    @BindView(R.id.tv_main_mine_jifenshangcheng_shangpinxiangqing_color)
    TextView tvMainMineJiFenShangChengShangPinXiangQingColor;
    @BindView(R.id.tv_main_mine_jifenshangcheng_shangpinxiangqing_cz)
    TextView tvMainMineJiFenShangChengShangPinXiangQingCZ;
    @BindView(R.id.tv_main_mine_jifenshangcheng_shangpinxiangqing_fg)
    TextView tvMainMineJiFenShangChengShangPinXiangQingFG;
    @BindView(R.id.tv_main_mine_jifenshangcheng_shangpinxiangqing_hh)
    TextView tvMainMineJiFenShangChengShangPinXiangQingHH;
    @BindView(R.id.tv_main_mine_jifenshangcheng_shangpinxiangqing_cc)
    TextView tvMainMineJiFenShangChengShangPinXiangQingCC;
    @BindView(R.id.bt_main_mine_jifenshangcheng_shangpinxiangqing_submit)
    Button btMainMineJiFenShangChengShangPinXiangQingSubmit;
    @OnClick(R.id.bt_main_mine_jifenshangcheng_shangpinxiangqing_submit)
    public void btMainMineJiFenShangChengShangPinXiangQingSubmitOnclick(){
        submitJiFenDuiHuanToNet();
    }
    private String addr="",tel="",name="";
    public ShangPinXiangQingController(Activity activity1){
        activity = activity1;
        init();

    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        getGoodsDetailFromNet();
    }


    private String getId(){
        String id = activity.getIntent().getStringExtra("id");
        if(id == null){
            id = "";
        }
        return  id;
    }

    public void getGoodsDetailFromNet(){
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if(login_id == null){
            return;
        }
        CompanyNetWork companyNetWork = new CompanyNetWork();
        companyNetWork.getJiFenShangChengGoodsDetailFromNet(login_id, getId(), new Observer<JiFenGoodsDetailBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(JiFenGoodsDetailBean jiFenGoodsDetailBean) {
                initDetail(jiFenGoodsDetailBean);
            }
        });
    }

    private void initDetail(JiFenGoodsDetailBean jiFenGoodsDetailBean){
        tvMainMineJiFenShangChengShangPinXiangQingTitle.setText(jiFenGoodsDetailBean.getName());
        tvMainMineJiFenShangChengShangPinXiangQingJiFen.setText(jiFenGoodsDetailBean.getJifen());
        tvMainMineJiFenShangChengShangPinXiangQingYuanJiFen.setText(jiFenGoodsDetailBean.getYuanjifen());
        tvMainMineJiFenShangChengShangPinXiangQingYuanYunFei.setText(jiFenGoodsDetailBean.getYunfen());
        tvMainMineJiFenShangChengShangPinXiangQingPP.setText(jiFenGoodsDetailBean.getPinpai());
        tvMainMineJiFenShangChengShangPinXiangQingXH.setText(jiFenGoodsDetailBean.getXinghao());
        tvMainMineJiFenShangChengShangPinXiangQingColor.setText(jiFenGoodsDetailBean.getYanse());
        tvMainMineJiFenShangChengShangPinXiangQingCZ.setText(jiFenGoodsDetailBean.getCaizhi());
        tvMainMineJiFenShangChengShangPinXiangQingFG.setText(jiFenGoodsDetailBean.getFengge());
        tvMainMineJiFenShangChengShangPinXiangQingHH.setText(jiFenGoodsDetailBean.getHuohao());
        tvMainMineJiFenShangChengShangPinXiangQingCC.setText(jiFenGoodsDetailBean.getChicui());
        tvMainMineJiFenShangChengShangPinXiangQingAddr.setText(jiFenGoodsDetailBean.getShr_dizhi());
        ImageLoader.getInstance().displayImage(jiFenGoodsDetailBean.getImage(),ivMainMineJiFenShangChengShangPinXiangQing, ImageLoaderUtils.options1);
        addr = jiFenGoodsDetailBean.getShr_dizhi();
        tel = jiFenGoodsDetailBean.getShr_tel();
        name = jiFenGoodsDetailBean.getShr_name();
        spid = jiFenGoodsDetailBean.getId();

    }
    private Map<String,String> getParamMap(){
        Map<String,String> paramMap = new HashMap<>();
        if(addr == null){
            addr = "";
        }else{
            addr = addr.replaceAll(" ","");
        }
        paramMap.put("shr_dizhi",addr);
        if(tel == null){
            tel = "";
        }else{
            tel = tel.replaceAll(" ","");
        }
        paramMap.put("shr_tel",tel);
        if(name == null){
            name = "";
        }else{
            name = name.replaceAll(" ","");
        }
        paramMap.put("shr_name",name);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if(login_id == null){
            login_id = "";
        }else{
            login_id = login_id.replaceAll(" ","");
        }
        paramMap.put("id",login_id);
        if(spid == null){
            spid = "";
        }else{
            spid = spid.replaceAll(" ","");
        }
        paramMap.put("spid",spid);
        return paramMap;
    }

    private void submitJiFenDuiHuanToNet(){
        CompanyNetWork companyNetWork = new CompanyNetWork();
        companyNetWork.duiHuanJIFenFromNet(getParamMap(), new Observer<JiFenDuiHuanBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(JiFenDuiHuanBean jiFenDuiHuanBean) {
                Toast.makeText(activity,jiFenDuiHuanBean.getMsg(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
