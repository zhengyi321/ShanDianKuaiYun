package com.zhyan.shandiankuaiyunlib.Widget.ViewPage;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.zhyan.shandiankuaiyunlib.R;

import java.util.ArrayList;
/*
* http://download.csdn.net/download/jimtrency/9633078
* **/
public class ImageCycleView extends LinearLayout {

	private Context mContext;

	private CycleViewPager mBannerPager = null;

	private ImageCycleAdapter mAdvAdapter;

	private ViewGroup mGroup;
	private TextView tvinfostitle;
	private ArrayList<String> infoListtitle;

	private ImageView mImageView = null;

	private ImageView[] mImageViews = null;

	private int mImageIndex = 1;

	private float mScale;

	public ImageCycleView(Context context) {
		super(context);
	}

	public ImageCycleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		mScale = context.getResources().getDisplayMetrics().density;
		LayoutInflater.from(context).inflate(R.layout.view_banner_content, this);
		mBannerPager = (CycleViewPager) findViewById(R.id.pager_banner);
		mBannerPager.setOnPageChangeListener(new GuidePageChangeListener());
		mBannerPager.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_UP:
					startImageTimerTask();
					break;
				default:
					stopImageTimerTask();
					break;
				}
				return false;
			}
		});
		mGroup = (ViewGroup) findViewById(R.id.viewGroup);
		tvinfostitle = (TextView) findViewById(R.id.tvinfostitle);
	}

	public void setImageResources(ArrayList<String> infoList, ArrayList<String> infoListtitle,
			ImageCycleViewListener imageCycleViewListener) {
		mGroup.removeAllViews();
		this.infoListtitle = infoListtitle;
		final int imageCount = infoList.size();
		mImageViews = new ImageView[imageCount];
		for (int i = 0; i < imageCount; i++) {
			mImageView = new ImageView(mContext);
			int imageParams = (int) (mScale * 20 + 0.5f);
			int imagePadding = (int) (mScale * 5 + 0.5f);
			LayoutParams layout = new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			layout.setMargins(3, 0, 3, 0);
			layout.width = 10;
			layout.height = 10;
			mImageView.setLayoutParams(layout);

			//存放小圆点的image
			mImageViews[i] = mImageView;
			if (i == 0) {
				mImageViews[i].setBackgroundResource(R.drawable.point_wheat);
			} else {
				mImageViews[i].setBackgroundResource(R.drawable.point_skyblue);
			}
			mGroup.addView(mImageViews[i]);
		}
		mAdvAdapter = new ImageCycleAdapter(mContext, infoList, imageCycleViewListener);
		mBannerPager.setAdapter(mAdvAdapter);
		startImageTimerTask();
	}

	public void startImageCycle() {
		startImageTimerTask();
	}

	public void pushImageCycle() {
		stopImageTimerTask();
	}

	private void startImageTimerTask() {
		stopImageTimerTask();
		mHandler.postDelayed(mImageTimerTask, 5000);
	}

	private void stopImageTimerTask() {
		mHandler.removeCallbacks(mImageTimerTask);
	}

	private Handler mHandler = new Handler();

	private Runnable mImageTimerTask = new Runnable() {

		@Override
		public void run() {
			if (mImageViews != null) {
				if ((++mImageIndex) == mImageViews.length + 1) {
					mImageIndex = 1;
				}
				mBannerPager.setCurrentItem(mImageIndex);
			}
		}
	};

	private final class GuidePageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int state) {
			if (state == ViewPager.SCROLL_STATE_IDLE)
				startImageTimerTask();
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int index) {
            //设置图片 title和圆点变化
			if (index == 0 || index == mImageViews.length + 1) {
				return;
			}
			mImageIndex = index;
			index -= 1;
			mImageViews[index].setBackgroundResource(R.drawable.point_wheat);
			for (int i = 0; i < mImageViews.length; i++) {

				if (index != i) {
					mImageViews[i].setBackgroundResource(R.drawable.point_skyblue);
				} else {
					tvinfostitle.setText(infoListtitle.get(index));
				}
			}
		}
	}

	private class ImageCycleAdapter extends PagerAdapter {

		private ArrayList<ImageView> mImageViewCacheList;

		private ArrayList<String> mAdList = new ArrayList<String>();

		private ImageCycleViewListener mImageCycleViewListener;

		private Context mContext;

		public ImageCycleAdapter(Context context, ArrayList<String> adList,
				ImageCycleViewListener imageCycleViewListener) {
			mContext = context;
			mAdList = adList;
			mImageCycleViewListener = imageCycleViewListener;
			mImageViewCacheList = new ArrayList<ImageView>();
		}

		@Override
		public int getCount() {
			return mAdList.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}

		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			String imageUrl = "";
			if (mAdList != null && mAdList.size() > position) {
				if (!TextUtils.isEmpty(mAdList.get(position).trim())) {
					imageUrl = mAdList.get(position);
				}
			}
			ImageView imageView = null;
			if (mImageViewCacheList.isEmpty()) {
				imageView = new ImageView(mContext);
				imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				imageView.setScaleType(ImageView.ScaleType.FIT_XY);

			} else {
				imageView = mImageViewCacheList.remove(0);
			}
			imageView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mImageCycleViewListener.onImageClick(mAdList.get(position), position, v);
				}
			});
			imageView.setTag(imageUrl);
			container.addView(imageView);
			mImageCycleViewListener.displayImage(imageUrl, imageView);
			return imageView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			ImageView view = (ImageView) object;
			container.removeView(view);
			mImageViewCacheList.add(view);
		}

	}

	public static interface ImageCycleViewListener {
		public void displayImage(String imageURL, ImageView imageView);
		public void onImageClick(String info, int postion, View imageView);
	}

}
