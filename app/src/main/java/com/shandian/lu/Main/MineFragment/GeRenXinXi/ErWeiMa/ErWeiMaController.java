package com.shandian.lu.Main.MineFragment.GeRenXinXi.ErWeiMa;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.shandian.lu.BaseController;
import com.shandian.lu.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by az on 2017/5/10.
 */

public class ErWeiMaController extends BaseController {
    PopupWindow pop;
    String inviteCode;

    private Dialog dialog;
    @BindView(R.id.rly_main_mine_gerenxinxi_erweima_topbar_back)
    RelativeLayout rlyMainMineGeRenXinXiErWeiMaTopBarBack;
    @OnClick(R.id.rly_main_mine_gerenxinxi_erweima_topbar_back)
    public void rlyMainMineGeRenXinXiErWeiMaTopBarBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.rly_main_mine_gerenxinxi_erweima_topbar_add)
    RelativeLayout rlyMainMineGeRenXinXiErWeiMaTopBarAdd;
    @OnClick(R.id.rly_main_mine_gerenxinxi_erweima_topbar_add)
    public void rlyMainMineGeRenXinXiErWeiMaTopBarAddOnclick(){
        initRightAdd();
    }
    @BindView(R.id.bt_main_mine_gerenxinxi_erweima_yq)
    Button btMainMineGeRenXinXiErWeiMaYQ;
    @OnClick(R.id.bt_main_mine_gerenxinxi_erweima_yq)
    public void btMainMineGeRenXinXiErWeiMaYQOnClick(){
        Uri smsToUri = Uri.parse("smsto:");
        Intent intent = new Intent( android.content.Intent.ACTION_SENDTO, smsToUri );
        intent.putExtra("sms_body", "我发现了闪电物流APP,感觉不错,你也来体验一下吧!http://www.lianshiding.com");
        activity.startActivity( intent );
    }
    @BindView(R.id.tv_main_mine_gerenxinxi_erweima_yqm)
    TextView tvMainMineGeRenXinXiErWeiMaYQM;
    @BindView(R.id.iv_main_mine_gerenxinxi_erweima_ewm)
    ImageView ivMainMineGeRenXinXiErWeiMaEEWM;
    @OnLongClick(R.id.iv_main_mine_gerenxinxi_erweima_ewm)
    public boolean ivMainMineGeRenXinXiErWeiMaEEWM(){
        showDialog();
        return false;
    }
    private ImageLoader loader;
    public ErWeiMaController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initInviteCode();
    }

    private void initInviteCode(){
        String inviteCode = activity.getIntent().getStringExtra("inviteCode");
        tvMainMineGeRenXinXiErWeiMaYQM.setText("我的邀请码:"+inviteCode);
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();

        String ewm = xcCacheManager.readCache(xcCacheSaveName.userEWMUrl);
        loader = ImageLoader.getInstance();
        if(ewm != null){
            loader.displayImage(ewm,ivMainMineGeRenXinXiErWeiMaEEWM);
        }
    }

    private void initRightAdd(){

        View view= LayoutInflater.from(activity).inflate(R.layout.item_copy_erweima,null);
        RightAdd rightAdd = new RightAdd(view);
        pop=new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);
        if(pop.isShowing()){
            pop.dismiss();
        }else{
            pop.showAsDropDown(rlyMainMineGeRenXinXiErWeiMaTopBarAdd, 0,0);
        }
    }

    protected void showDialog() {
        dialog = new AlertDialog.Builder(activity).create();//创建一个AlertDialog对象
        dialog.show();//一定要先show出来再设置dialog的参数，不然就不会改变dialog的大小了
        View view = activity.getLayoutInflater().inflate(R.layout.item_myerweima_longclick, null);//自定义布局
        dialog.setContentView(view);//把自定义的布局设置到dialog中，注意，布局设置一定要在show之前。从第二个参数分别填充内容与边框之间左、上、右、下、的像素
        dialog.setCanceledOnTouchOutside(true);
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();//得到当前显示设备的宽度，单位是像素
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();//得到这个dialog界面的参数对象
        params.width = width-(width/6);//设置dialog的界面宽度
        params.height =  WindowManager.LayoutParams.WRAP_CONTENT;//设置dialog高度为包裹内容
        params.gravity = Gravity.CENTER;//设置dialog的重心
        //dialog.getWindow().setLayout(width-(width/6),  LayoutParams.WRAP_CONTENT);//用这个方法设置dialog大小也可以，但是这个方法不能设置重心之类的参数，推荐用Attributes设置
        dialog.getWindow().setAttributes(params);//最后把这个参数对象设置进去，即与dialog绑定
        Button btnSave=(Button) view.findViewById(R.id.button1);
        Button btnCopy=(Button) view.findViewById(R.id.button2);
        Button btnDismiss=(Button) view.findViewById(R.id.button3);
        MyDialogClicklistener dialogClicklistener=new MyDialogClicklistener();
        btnSave.setOnClickListener(dialogClicklistener);
        btnCopy.setOnClickListener(dialogClicklistener);
        btnDismiss.setOnClickListener(dialogClicklistener);

    }

    class MyDialogClicklistener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button1:
                    Bitmap bm = ((BitmapDrawable) ((ImageView) ivMainMineGeRenXinXiErWeiMaEEWM).getDrawable()).getBitmap();
                    File file = new File(Environment.getExternalStorageDirectory(), "erweima.png");
                    try {
                        file.createNewFile();
                        FileOutputStream fos = new FileOutputStream(file);
                        bm.compress(Bitmap.CompressFormat.PNG, 100, fos);
                        fos.flush();
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    Uri uri = Uri.fromFile(file);
                    intent.setData(uri);
                    activity.sendBroadcast(intent);//这个广播的目的就是更新图库，发了这个广播进入相册就可以找到你保存的图片了！，记得要传你更新的file哦
                    dialog.dismiss();
                    Toast.makeText(activity, "保存成功", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button2:
                    ClipboardManager cbm = (ClipboardManager) activity.getSystemService(CLIPBOARD_SERVICE);
                    XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
                    XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
                    String myQr_code = xcCacheManager.readCache(xcCacheSaveName.userEWMUrl);
                    ClipData data = ClipData.newPlainText("复制图片链接", myQr_code);
                    cbm.setPrimaryClip(data);
                    dialog.dismiss();
                    Toast.makeText(activity, "复制成功", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button3:
                    dialog.dismiss();
                    break;
            }
        }
    }
        public class RightAdd{
        @BindView(R.id.tvCopy_inviteaddress)
        TextView tvCopyInviteAddress;
        @OnClick(R.id.tvCopy_inviteaddress)
        public void tvCopyInviteAddressOnclick(){
            ClipboardManager cbm=(ClipboardManager)activity.getSystemService(CLIPBOARD_SERVICE);
            ClipData data=ClipData.newPlainText("复制图片链接", inviteCode);
            cbm.setPrimaryClip(data);
            pop.dismiss();
            Toast.makeText(activity, "复制成功",Toast.LENGTH_SHORT).show();
        }
        @BindView(R.id.tvShare_inviteaddress)
        TextView tvShareInviteAddress;
        @OnClick(R.id.tvShare_inviteaddress)
        public void tvShareInviteAddressOnclick(){
            pop.dismiss();
            Toast.makeText(activity, "敬请期待",Toast.LENGTH_SHORT).show();
        }
        @BindView(R.id.tvSend_invitesms)
        TextView tvSendInviteAddress;
        @OnClick(R.id.tvSend_invitesms)
        public void tvSendInviteAddressOnclick(){
            pop.dismiss();
            Uri smsToUri = Uri.parse("smsto:");
            Intent intent = new Intent( android.content.Intent.ACTION_SENDTO, smsToUri );
            intent.putExtra("sms_body", "我发现了闪电物流APP,感觉不错,你也来体验一下吧!http://www.lianshiding.com");
            activity.startActivity( intent );
        }
        public RightAdd(View view){
            ButterKnife.bind(this,view);
        }
    }
}
