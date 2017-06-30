package com.shandian.lu.ErWeiMa.lib.decode;

import java.util.concurrent.CountDownLatch;

/*import com.zbar.lib.CaptureActivity;*/

import android.os.Handler;
import android.os.Looper;

import com.shandian.lu.ErWeiMa.lib.CaptureActivity;

/**
 * 浣滆��: 闄堟稕(1076559197@qq.com)
 * 
 * 鏃堕棿: 2014骞�5鏈�9鏃� 涓嬪崍12:24:34
 *
 * 鐗堟湰: V_1.0.0
 *
 * 鎻忚堪: 瑙ｇ爜绾跨▼
 */
final class DecodeThread extends Thread {

	CaptureActivity activity;
	private Handler handler;
	private final CountDownLatch handlerInitLatch;

	DecodeThread(CaptureActivity activity) {
		this.activity = activity;
		handlerInitLatch = new CountDownLatch(1);
	}

	Handler getHandler() {
		try {
			handlerInitLatch.await();
		} catch (InterruptedException ie) {
			// continue?
		}
		return handler;
	}

	@Override
	public void run() {
		Looper.prepare();
		handler = new DecodeHandler(activity);
		handlerInitLatch.countDown();
		Looper.loop();
	}

}
