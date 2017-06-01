package com.shandian.lu.Widget.Dialog.ShareDialog;
import java.util.ArrayList;
import java.util.List;

import com.zhyan.shandiankuaiyuanwidgetlib.ShareDialog.PageIndicatorView;
import com.zhyan.shandiankuaiyuanwidgetlib.ShareDialog.PageRecyclerView;
import com.zhyan.shandiankuaiyuanwidgetlib.ShareDialog.ViewUtil;
/*import com.shandian.lu.util.ViewUtil;
import com.shandian.lu.weight.PageIndicatorView;
import com.shandian.lu.weight.PageRecyclerView;*/
/*
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;*/
import com.shandian.lu.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
public class CommonSharePopWindowActivity extends Activity{
/*
	private Context context;
	private Activity current_activity;
	private static CommonSharePopWindowActivity instance;
	//分享的dialog
	private Dialog shareDialog;
	//分享的分页PageRecyclerView
	private PageRecyclerView myRecyclerView;
	//分享的平台集合
	private  List<String> dataList = null;
	//分享的分页PageRecyclerView的Adapter
	private PageRecyclerView.PageAdapter myAdapter = null;
	int []shareimages=null;//分享的平台图片资源
	//分享的回调
	private CustomShareListener umShareListener;
	public synchronized static CommonSharePopWindowActivity getInstance() {
		if (null == instance) {
			instance = new CommonSharePopWindowActivity();
		}
		return instance;

	}
	public void showBottomDialog(Activity activity) {
		umShareListener =new CustomShareListener();
		this.context = activity;
		this.current_activity=activity;
		shareDialog = new Dialog(this.context,R.style.dialog);
		View contentView = LayoutInflater.from(this.context).inflate(R.layout.pop_share, null);
		shareDialog.setContentView(contentView);
		shareDialog.setCanceledOnTouchOutside(true);
		int screenWidth =activity.getWindowManager().getDefaultDisplay().getWidth();
		Window dialogWindow = shareDialog.getWindow();
		dialogWindow.setWindowAnimations(R.style.popupAnimation);
		dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL);
		WindowManager.LayoutParams layoutParams = shareDialog.getWindow().getAttributes();
		ViewUtil viewUtil = new ViewUtil();
		layoutParams.width = screenWidth - viewUtil.dip2px(this.context,20);
		layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		layoutParams.x = 0;
		layoutParams.y = activity.getWindowManager().getDefaultDisplay().getHeight()-viewUtil.dip2px(this.context,50);
		dialogWindow.setAttributes(layoutParams);
		shareDialog.show();
		contentView.findViewById(R.id.tv_delete).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				shareDialog.dismiss();

			}
		});
        initData();
        Toast.makeText(activity,"myRecyclerView size"+dataList.size(),Toast.LENGTH_LONG).show();
        Log.d("debug Share","1");
		// 显示分享分页的viewpager
		myRecyclerView=(PageRecyclerView) contentView.findViewById(R.id.myRecyclerView);
		Log.d("debug Share","2");
		// 设置指示器
		myRecyclerView.setIndicator((PageIndicatorView)contentView.findViewById(R.id.indicator));
		Log.d("debug Share","3");
		// 设置行数和列数
		myRecyclerView.setPageSize(2, 3);
		Log.d("debug Share","4");
		// 设置页间距
		myRecyclerView.setPageMargin(30);
		Log.d("debug Share","5");
		// 设置数据
		myRecyclerView.setAdapter(myAdapter = myRecyclerView.new PageAdapter(dataList, new PageRecyclerView.CallBack() {
			@Override
			public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
				Log.d("debug Share","6");
				View view = LayoutInflater.from(CommonSharePopWindowActivity.this.context).inflate(R.layout.item, parent, false);
				Log.d("debug Share","7");
				return new MyHolder(view);
			}

			@Override
			public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
				Log.d("debug Share","8");
				((MyHolder)holder).tv.setText(dataList.get(position));
				Log.d("debug Share","9");
				Drawable drawable=CommonSharePopWindowActivity.this.context.getResources().getDrawable(shareimages[position]);
				Log.d("debug Share","10");
				drawable.setBounds(0, 0, drawable.getMinimumWidth(),drawable.getMinimumHeight());
				Log.d("debug Share","1");
				((MyHolder)holder).tv.setCompoundDrawables(null, drawable, null, null);
				Log.d("debug Share","11");
				((MyHolder)holder).tv.setText(dataList.get(position));
				Log.d("debug Share","12");
			}
		}));


	}
	private void initData() {
		shareimages=new int[]{R.drawable.sharewechat,R.drawable.shareqq,R.drawable.sharecirclefriends,R.drawable.shareqqzone,R.drawable.sharesina,R.drawable.sharemessage,R.drawable.shareweichatcollect};
		dataList = new ArrayList<String>();
		dataList.add("微信好友");
		dataList.add("QQ");
		dataList.add("微信朋友圈");
		dataList.add("QQ空间");
		dataList.add("新浪微博");
		dataList.add("短信");
		dataList.add("微信收藏");
	}
	public class MyHolder extends RecyclerView.ViewHolder {

		public TextView tv = null;

		public MyHolder(View itemView) {
			super(itemView);
			tv = (TextView) itemView.findViewById(R.id.text);
			tv.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
//	                 Toast.makeText(CarSourceDetailActivity.this, getAdapterPosition() + "", Toast.LENGTH_SHORT).show();
					int position=getAdapterPosition();
					UMImage image=new UMImage(CommonSharePopWindowActivity.this.context, R.mipmap.logo);
					String title="欢迎分享闪电快运APP";
					String text="我发现了闪电物流APP,感觉不错,你也来体验一下吧!http://www.lianshiding.com";
					String url="http://www.lianshiding.com";
					switch (position) {
						case 0:
							new ShareAction(current_activity).setPlatform(SHARE_MEDIA.WEIXIN).withTitle(title).withText(text).withTargetUrl(url).withMedia(image).setCallback(umShareListener).share();
							break;
						case 1:
							new ShareAction(current_activity).setPlatform(SHARE_MEDIA.QQ).withTitle(title).withText(text).withTargetUrl(url).withMedia(image).setCallback(umShareListener).share();
							break;
						case 2:
							new ShareAction(current_activity).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).withTitle(title).withText(text).withTargetUrl(url).withMedia(image).setCallback(umShareListener).share();
							break;
						case 3:
							new ShareAction(current_activity).setPlatform(SHARE_MEDIA.QZONE).withTitle(title).withText(text).withTargetUrl(url).withMedia(image).setCallback(umShareListener).share();
							break;
						case 4:
							new ShareAction(current_activity).setPlatform(SHARE_MEDIA.SINA).withTitle(title).withText(text).withTargetUrl(url).withMedia(image).setCallback(umShareListener).share();
							break;

						case 5:
							new ShareAction(current_activity).setPlatform(SHARE_MEDIA.SMS).withText(text).setCallback(umShareListener).share();
							break;
						case 6:
							new ShareAction(current_activity).setPlatform(SHARE_MEDIA.WEIXIN_FAVORITE).withTitle(title).withText(text).withTargetUrl(url).withMedia(image).setCallback(umShareListener).share();
							break;
					}

				}
			});

		}
	}
	private class CustomShareListener implements UMShareListener{

		@Override
		public void onResult(SHARE_MEDIA platform) {
			Log.d("plat","platform"+platform);

			Toast.makeText(CommonSharePopWindowActivity.this.context, platform + "恭喜分享成功", Toast.LENGTH_SHORT).show();
			shareDialog.dismiss();


		}
		@Override
		public void onCancel(SHARE_MEDIA platform) {
			Toast.makeText(CommonSharePopWindowActivity.this.context,platform + "分享已取消", Toast.LENGTH_SHORT).show();
			shareDialog.dismiss();
		}

		@Override
		public void onError(SHARE_MEDIA platform, Throwable t) {
			Toast.makeText(CommonSharePopWindowActivity.this.context,platform + " 分享失败"+t.getMessage(), Toast.LENGTH_SHORT).show();
			shareDialog.dismiss();
			if(t!=null){
				Log.d("throw","throw:"+t.getMessage());
			}

		}

	}
*/

}
