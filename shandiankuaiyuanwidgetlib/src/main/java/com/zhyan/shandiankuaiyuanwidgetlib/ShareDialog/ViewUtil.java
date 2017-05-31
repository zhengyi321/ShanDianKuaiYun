package com.zhyan.shandiankuaiyuanwidgetlib.ShareDialog;

import android.content.Context;

public class ViewUtil {
	/**
	 * dip转换px
	 * @param context
	 * @param dipValue
	 * @return
	 */
	public  int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);

	}
}