package com.shandian.lu.Main.MineFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
/*
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.Log;*/
import com.shandian.lu.Main.BaseFragment;
import com.shandian.lu.R;
/*import CommonSharePopWindowActivity;*/
/*import CommonSharePopWindowActivity;*/

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/4/26.
 */

public class MainMineFragment extends BaseFragment {
 /*   UMImage image=new UMImage(getActivity(), R.mipmap.logo);
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();*/
    @BindView(R.id.lly_main_mine_fenxiang)
    LinearLayout llyMainMineFenXiang;
    @OnClick(R.id.lly_main_mine_fenxiang)
    public void llyMainMineFenXiangOnclick(){
       /* DemoActivity demoActivity = new DemoActivity(getActivity());*/
  /*      CommonSharePopWindowActivity.getInstance().showBottomDialog(getActivity());*//*/*
        Defaultcontent defaultcontent = new Defaultcontent();
        new ShareAction(getActivity()).setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                .withTitle(defaultcontent.title)
                .withText(defaultcontent.text)
                .withMedia(image)
                .withTargetUrl(defaultcontent.url)
                .setCallback(umShareListener)
                .open();*/
    }
    private MainMineController mainMineController;


    @Override
    public View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_mine_lly, container, false);

        return view;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this,view);
        initController(view);
    }


    private void initController(View view){
        mainMineController = new MainMineController(view);
    }

    public void onResume(){
        super.onResume();
        mainMineController.onResume();
    }

/*
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat","platform"+platform);
            if(platform.name().equals("WEIXIN_FAVORITE")){
                Toast.makeText(getActivity(),platform + " 收藏成功啦",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(),platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if(t!=null){
                Log.d("throw","throw:"+t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(),platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        *//** attention to this below ,must add this**//*
        UMShareAPI.get(getActivity()).onActivityResult(requestCode, resultCode, data);
        Log.d("result","onActivityResult");
    }*/

}
