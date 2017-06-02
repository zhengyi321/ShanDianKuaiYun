package com.shandian.lu.Main.MineFragment.MengYou;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/4.
 */

public class MengYouActivity extends BaseActivity {

    private MengYouController mengYouController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_mengyou_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        mengYouController = new MengYouController(this);
    }
}
