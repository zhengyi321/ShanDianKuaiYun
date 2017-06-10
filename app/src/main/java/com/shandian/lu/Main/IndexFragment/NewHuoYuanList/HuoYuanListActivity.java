package com.shandian.lu.Main.IndexFragment.NewHuoYuanList;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.Main.ReleaseFragment.SelectAddAddress.SelectAddAddressActivity;
import com.shandian.lu.R;
import com.yanzhenjie.album.Album;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/10.
 */

public class HuoYuanListActivity extends BaseActivity {

    private final int ACTIVITY_SELECT_ADDRESS_BEGIN = 105;
    private final int ACTIVITY_SELECT_ADDRESS_END = 106;
    private String bProvince,eProvince,bCity,eCity,bArea,eArea,beginAddr,endAddr;
    private String blat,blon,elat,elon;
    @BindView(R.id.rly_new_huoyuanlist_baddr)
    RelativeLayout rlyNewHuoYuanListBAddr;
    @BindView(R.id.tv_new_huoyuanlist_baddr)
    TextView tvNewHuoYuanListBAddr;
    @OnClick(R.id.rly_new_huoyuanlist_baddr)
    public void rlyNewHuoYuanListBAddrOnclick(){
        Intent intent = new Intent(this, SelectAddAddressActivity.class);
        intent.putExtra("type","begin");
        startActivityForResult(intent,ACTIVITY_SELECT_ADDRESS_BEGIN);
    }
    @BindView(R.id.rly_new_huoyuanlist_eaddr)
    RelativeLayout rlyNewHuoYuanListEAddr;
    @BindView(R.id.tv_new_huoyuanlist_eaddr)
    TextView tvNewHuoYuanListEAddr;
    @OnClick(R.id.rly_new_huoyuanlist_eaddr)
    public void rlyNewHuoYuanListEAddrOnclick(){
        Intent intent = new Intent(this, SelectAddAddressActivity.class);
        intent.putExtra("type","end");
        startActivityForResult(intent,ACTIVITY_SELECT_ADDRESS_END);
    }



    private HuoYuanListController huoYuanListController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_huoyuanlist_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();

    }


    private void initController(){
        huoYuanListController = new HuoYuanListController(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data == null){
            return;
        }
        switch (requestCode) {

            case ACTIVITY_SELECT_ADDRESS_BEGIN:{

                Bundle begin = data.getExtras();

                bProvince = begin.getString("province");
                bCity = begin.getString("city");
                bArea = begin.getString("area");
                blat = begin.getString("lat");
                blon = begin.getString("lon");
                beginAddr = begin.getString("addr");
                tvNewHuoYuanListBAddr.setText(bProvince+bCity+bArea);
                break;
            }
            case ACTIVITY_SELECT_ADDRESS_END:{

                Bundle begin = data.getExtras();

                eProvince = begin.getString("province");
                eCity = begin.getString("city");
                eArea = begin.getString("area");
                elat = begin.getString("lat");
                elon = begin.getString("lon");
                endAddr = begin.getString("addr");
                tvNewHuoYuanListEAddr.setText(eProvince+eCity+eArea);
                break;
            }

            default:

                break;
        }
    }

    private void reFreshData(){
        if((blat != null)&&(blon != null)&&(elat!=null)&&(elon != null)){

        }
    }
}
