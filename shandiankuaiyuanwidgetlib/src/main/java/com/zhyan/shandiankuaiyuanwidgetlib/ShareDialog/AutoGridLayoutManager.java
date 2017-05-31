package com.zhyan.shandiankuaiyuanwidgetlib.ShareDialog;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
/**
 * Created by 卢士刚 on 2016/12/29
 * <p>
 * 重写GridLayoutManager，在{@link RecyclerView#setLayoutManager(RecyclerView.LayoutManager)}使用
 * 此类替换{@link GridLayoutManager}，使{@link RecyclerView}能够自使用内容的高度
 * </p>
 */
public class AutoGridLayoutManager extends GridLayoutManager{
	private int measuredWidth = 0;
	private int measuredHeight = 0;
	public AutoGridLayoutManager(Context context, int spanCount) {
		super(context, spanCount);
	}
	public AutoGridLayoutManager(Context context, AttributeSet attrs,
								 int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
        Log.d("debug glm","1");
	}

	public AutoGridLayoutManager(Context context, int spanCount,
								 int orientation, boolean reverseLayout) {
		super(context, spanCount, orientation, reverseLayout);
        Log.d("debug glm","2");
	}
	@Override
	public void onMeasure(Recycler recycler, State state, int widthSpec,
						  int heightSpec) {
        Log.d("debug glm","3");
		if(state.getItemCount() == 0){
			return;
		}
		View view=recycler.getViewForPosition(0);
		if(view!=null){
            Log.d("debug glm","4");
			measureChild(view, widthSpec, heightSpec);
			measuredWidth=View.MeasureSpec.getSize(widthSpec);
			measuredHeight=view.getMeasuredHeight()*getSpanCount();
			setMeasuredDimension(measuredWidth, measuredHeight);
            Log.d("debug glm","5");
		}
		super.onMeasure(recycler, state, widthSpec, heightSpec);
        Log.d("debug glm","6");
	}

}