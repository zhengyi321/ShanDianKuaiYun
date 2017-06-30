package com.shandian.lu.ErWeiMa.lib.decode;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.shandian.lu.ErWeiMa.lib.CaptureActivity;
import com.shandian.lu.ErWeiMa.lib.ZbarManager;
import com.shandian.lu.R;
/*import com.zbar.lib.CaptureActivity;
import com.zbar.lib.ZbarManager;*/

/**
 * 浣滆��: 闄堟稕(1076559197@qq.com)
 * 
 * 鏃堕棿: 2014骞�5鏈�9鏃� 涓嬪崍12:24:13
 *
 * 鐗堟湰: V_1.0.0
 *
 * 鎻忚堪: 鎺ュ彈娑堟伅鍚庤В鐮�
 */
final class DecodeHandler extends Handler {

	CaptureActivity activity = null;

	DecodeHandler(CaptureActivity activity) {
		this.activity = activity;
	}

	@Override
	public void handleMessage(Message message) {
		switch (message.what) {
		case R.id.decode:
			decode((byte[]) message.obj, message.arg1, message.arg2);
			break;
		case R.id.quit:
			Looper.myLooper().quit();
			break;
		}
	}

	private void decode(byte[] data, int width, int height) {
		byte[] rotatedData = new byte[data.length];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++)
				rotatedData[x * height + height - y - 1] = data[x + y * width];
		}
		int tmp = width;// Here we are swapping, that's the difference to #11
		width = height;
		height = tmp;

		ZbarManager manager = new ZbarManager();
		String result = manager.decode(rotatedData, width, height, true,
				activity.getX(), activity.getY(), activity.getCropWidth(),
				activity.getCropHeight());

		if (result != null) {
			if(null != activity.getHandler()){
				Message msg = new Message();
				msg.obj = result;
				msg.what = R.id.decode_succeeded;
				activity.getHandler().sendMessage(msg);
			}
		} else {
			if (null != activity.getHandler()) {
				activity.getHandler().sendEmptyMessage(R.id.decode_failed);
			}
		}
	}

}
