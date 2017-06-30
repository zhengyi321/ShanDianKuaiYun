package com.shandian.lu.ErWeiMa;

import java.io.IOException;
import java.lang.reflect.Field;

import com.shandian.lu.R;
/*import com.shandian.lu.activity.RegistActivity;*/

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CaptureActivity extends Activity {

	private Camera mCamera;
	private CameraPreview mPreview;
	private Handler autoFocusHandler;
	private CameraManager mCameraManager;

	private TextView scanResult;
	private FrameLayout scanPreview;
	private Button scanRestart;
	private RelativeLayout scanContainer;
	private RelativeLayout scanCropView;
	private ImageView scanLine;

	private Rect mCropRect = null;
	private boolean barcodeScanned = false;
	private boolean previewing = true;
	private ImageScanner mImageScanner = null;

	static {
		System.loadLibrary("iconv");
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_erweima);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		findViewById();
		addEvents();
		initViews();
	}

	private void findViewById() {
		scanPreview = (FrameLayout) findViewById(R.id.capture_preview);
		scanResult = (TextView) findViewById(R.id.capture_scan_result);
		scanRestart = (Button) findViewById(R.id.capture_restart_scan);
		scanContainer = (RelativeLayout) findViewById(R.id.capture_container);
		scanCropView = (RelativeLayout) findViewById(R.id.capture_crop_view);
		scanLine = (ImageView) findViewById(R.id.capture_scan_line);
	}

	private void addEvents() {
		scanRestart.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
			finish();
			}
		});
	}

	private void initViews() {
		mImageScanner = new ImageScanner();
		mImageScanner.setConfig(0, Config.X_DENSITY, 3);
		mImageScanner.setConfig(0, Config.Y_DENSITY, 3);

		autoFocusHandler = new Handler();
		mCameraManager = new CameraManager(this);
		try {
			mCameraManager.openDriver();
		} catch (IOException e) {
			e.printStackTrace();
		}

		mCamera = mCameraManager.getCamera();
		mPreview = new CameraPreview(this, mCamera, previewCb, autoFocusCB);
		scanPreview.addView(mPreview);

		TranslateAnimation animation = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.85f);
		animation.setDuration(3000);
		animation.setRepeatCount(-1);
		animation.setRepeatMode(Animation.REVERSE);
		scanLine.startAnimation(animation);
	}

	public void onPause() {
		super.onPause();
		releaseCamera();
	}

	private void releaseCamera() {
		if (mCamera != null) {
			previewing = false;
			mCamera.setPreviewCallback(null);
			mCamera.release();
			mCamera = null;
		}
	}

	private Runnable doAutoFocus = new Runnable() {
		public void run() {
			if (previewing)
				mCamera.autoFocus(autoFocusCB);
		}
	};

	PreviewCallback previewCb = new PreviewCallback() {
		public void onPreviewFrame(byte[] data, Camera camera) {
			Size size = camera.getParameters().getPreviewSize();

			// 杩欓噷闇�瑕佸皢鑾峰彇鐨刣ata缈昏浆涓�涓嬶紝鍥犱负鐩告満榛樿鎷跨殑鐨勬í灞忕殑鏁版嵁
			byte[] rotatedData = new byte[data.length];
			for (int y = 0; y < size.height; y++) {
				for (int x = 0; x < size.width; x++)
					rotatedData[x * size.height + size.height - y - 1] = data[x
							+ y * size.width];
			}

			// 瀹介珮涔熻璋冩暣
			int tmp = size.width;
			size.width = size.height;
			size.height = tmp;

			initCrop();

			Image barcode = new Image(size.width, size.height, "Y800");
			barcode.setData(rotatedData);
			barcode.setCrop(mCropRect.left, mCropRect.top, mCropRect.width(),
					mCropRect.height());

			int result = mImageScanner.scanImage(barcode);
			String resultStr = null;

			if (result != 0) {
				SymbolSet syms = mImageScanner.getResults();
				for (Symbol sym : syms) {
					resultStr = sym.getData();
				}
			}

			if (!TextUtils.isEmpty(resultStr)) {
				previewing = false;
				mCamera.setPreviewCallback(null);
				mCamera.stopPreview();
         if(resultStr.indexOf("one_code")!=-1){
    /*    	  String code=resultStr.substring(resultStr.length()-6,resultStr.length());
        	  Intent oneCode=new Intent(CaptureActivity.this,RegistActivity.class);
        	  oneCode.putExtra("one_code", code);
        	  startActivity(oneCode);*/
        	  finish(); 
        	  barcodeScanned = true;
         }else{
        	 
        	 Intent intent = new Intent();
        	 intent.setAction("android.intent.action.VIEW");
        	 Uri content_url = Uri.parse(resultStr); 
        	 intent.setData(content_url); 
        	 startActivity(intent);   
        	 finish(); 
        	 Log.i("info","result-->"+resultStr);
        	 barcodeScanned = true;
         }
			}
		}
	};

	// Mimic continuous auto-focusing
	AutoFocusCallback autoFocusCB = new AutoFocusCallback() {
		public void onAutoFocus(boolean success, Camera camera) {
			autoFocusHandler.postDelayed(doAutoFocus, 1000);
		}
	};

	/**
	 * 鍒濆鍖栨埅鍙栫殑鐭╁舰鍖哄煙
	 */
	private void initCrop() {
		int cameraWidth = mCameraManager.getCameraResolution().y;
		int cameraHeight = mCameraManager.getCameraResolution().x;

		/** 鑾峰彇甯冨眬涓壂鎻忔鐨勪綅缃俊鎭� */
		int[] location = new int[2];
		scanCropView.getLocationInWindow(location);

		int cropLeft = location[0];
		int cropTop = location[1] - getStatusBarHeight();

		int cropWidth = scanCropView.getWidth();
		int cropHeight = scanCropView.getHeight();

		/** 鑾峰彇甯冨眬瀹瑰櫒鐨勫楂� */
		int containerWidth = scanContainer.getWidth();
		int containerHeight = scanContainer.getHeight();

		/** 璁＄畻鏈�缁堟埅鍙栫殑鐭╁舰鐨勫乏涓婅椤剁偣x鍧愭爣 */
		int x = cropLeft * cameraWidth / containerWidth;
		/** 璁＄畻鏈�缁堟埅鍙栫殑鐭╁舰鐨勫乏涓婅椤剁偣y鍧愭爣 */
		int y = cropTop * cameraHeight / containerHeight;

		/** 璁＄畻鏈�缁堟埅鍙栫殑鐭╁舰鐨勫搴� */
		int width = cropWidth * cameraWidth / containerWidth;
		/** 璁＄畻鏈�缁堟埅鍙栫殑鐭╁舰鐨勯珮搴� */
		int height = cropHeight * cameraHeight / containerHeight;

		/** 鐢熸垚鏈�缁堢殑鎴彇鐨勭煩褰� */
		mCropRect = new Rect(x, y, width + x, height + y);
	}

	private int getStatusBarHeight() {
		try {
			Class<?> c = Class.forName("com.android.internal.R$dimen");
			Object obj = c.newInstance();
			Field field = c.getField("status_bar_height");
			int x = Integer.parseInt(field.get(obj).toString());
			return getResources().getDimensionPixelSize(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
