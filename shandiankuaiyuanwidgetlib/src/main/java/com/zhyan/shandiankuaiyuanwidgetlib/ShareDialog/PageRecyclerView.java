package com.zhyan.shandiankuaiyuanwidgetlib.ShareDialog;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.zhyan.shandiankuaiyuanwidgetlib.ShareDialog.AutoGridLayoutManager;

/**
 * Created by 卢士刚 on 2016/12/9 0009.
 * <p>
 * 横向分页的GridView效果
 * </p>
 * <p>
 * 默认为2行，每页3列，如果要自定义行数和列数，请在调用{@link PageRecyclerView#setAdapter(Adapter)}方法前调用
 * {@link PageRecyclerView#setPageSize(int, int)}方法自定义行数
 * </p>
 */
public class PageRecyclerView extends RecyclerView{
	private Context mContext = null;
	private PageAdapter myAdapter = null;
	private int shortestDistance; // 超过此距离的滑动才有效
	private float downX = 0; // 手指按下的X轴坐标
	private float slideDistance = 0; // 滑动的距离
	private float scrollX = 0; // X轴当前的位置
	private int spanRow = 1; // 行数
	private int spanColumn = 3; // 每页列数
	private int totalPage = 0; // 总页数
	private int currentPage = 1; // 当前页
	private int pageMargin = 0; // 页间距

	private PageIndicatorView mIndicatorView = null; // 指示器布局
	public PageRecyclerView(Context arg0, AttributeSet arg1, int arg2) {

		super(arg0, arg1, arg2);
		Log.d("debug page","1");
	}

	public PageRecyclerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Log.d("debug page","2");
	}

	public PageRecyclerView(Context context) {
		super(context);
		Log.d("debug page","3");
	}
	// 默认初始化
	private void defaultInit(Context context) {
		Log.d("debug page","4");
		this.mContext = context;
		setLayoutManager(new AutoGridLayoutManager(
				mContext, spanRow, AutoGridLayoutManager.HORIZONTAL, false));
		setOverScrollMode(OVER_SCROLL_NEVER);
		Log.d("debug page","5");
	}
	/**
	 * 设置行数和每页列数
	 *
	 * @param spanRow    行数，<=0表示使用默认的行数
	 * @param spanColumn 每页列数，<=0表示使用默认每页列数
	 */
	public void setPageSize(int spanRow, int spanColumn) {
		Log.d("debug page","6");
		this.spanRow=spanRow<=0?this.spanRow:spanRow;
		Log.d("debug page spanRow","6 spanRow:"+spanRow);
		this.spanColumn=spanColumn<=0?this.spanColumn:spanColumn;
		Log.d("debug page","7");
		/*setLayoutManager(new AutoGridLayoutManager(mContext,this.spanRow,AutoGridLayoutManager.HORIZONTAL,false));*/
		setLayoutManager(new AutoGridLayoutManager(mContext,this.spanRow,AutoGridLayoutManager.HORIZONTAL,false));
		Log.d("debug page","8");
	}
	/**
	 * 设置页间距
	 *
	 * @param pageMargin 间距(px)
	 */
	public void setPageMargin(int pageMargin) {
		Log.d("debug page","9");
		this.pageMargin = pageMargin;

	}
	/**
	 * 设置指示器
	 *
	 * @param indicatorView 指示器布局
	 */
	public void setIndicator(PageIndicatorView indicatorView){
		this.mIndicatorView=indicatorView;
		Log.d("debug page","10");
	}
	@Override
	protected void onMeasure(int widthSpec, int heightSpec) {
		super.onMeasure(widthSpec, heightSpec);
		Log.d("debug page","11");
		shortestDistance=getMeasuredWidth()/4;
		Log.d("debug page","12");
	}
	public void setAdapter(Adapter adapter){
		super.setAdapter(adapter);
		Log.d("debug page","13");
		this.myAdapter=(PageAdapter)adapter;
		Log.d("debug page","14");
		update();
		Log.d("debug page","15");
	}


	// 更新页码指示器和相关数据
	private void update() {
		// 计算总页数
//		int temp=Math.ceil(myAdapter.dataList.size())
		Log.d("debug page","16");
		int temp = ((int) Math.ceil(myAdapter.dataList.size() / (double) (spanRow * spanColumn)));
		Log.d("debug page","17");
		if (temp != totalPage) {
			Log.d("debug page","18");
			mIndicatorView.initIndicator(temp);
			// 页码减少且当前页为最后一页
			if (temp < totalPage && currentPage == totalPage) {
				currentPage = temp;
				Log.d("debug page","19");
				// 执行滚动
				smoothScrollBy(-getWidth(), 0);
			}
			Log.d("debug page","20");
			mIndicatorView.setSelectedPage(currentPage - 1);
			Log.d("debug page","21");
			totalPage = temp;
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d("debug page","22");
		switch (event.getAction()) {
			case MotionEvent.ACTION_MOVE:
				if (currentPage == totalPage && downX - event.getX() > 0) {
					return true;
				}
				break;
			case MotionEvent.ACTION_DOWN:
				downX = event.getX();
				break;
			case MotionEvent.ACTION_UP:
				slideDistance = event.getX() - downX;
				Log.d("debug page","23");
				if (Math.abs(slideDistance) > shortestDistance) {
					// 滑动距离足够，执行翻页
					if (slideDistance > 0) {
						// 上一页
						currentPage = currentPage == 1 ? 1 : currentPage - 1;
					} else {
						// 下一页
						currentPage = currentPage == totalPage ? totalPage : currentPage + 1;
					}
					// 修改指示器选中项
					mIndicatorView.setSelectedPage(currentPage - 1);
				}
				// 执行滚动
				smoothScrollBy((int) ((currentPage - 1) * getWidth() - scrollX), 0);
				return true;
			default:
				break;
		}

		return super.onTouchEvent(event);
	}

	@Override
	public void onScrolled(int dx, int dy) {
		scrollX += dx;
		super.onScrolled(dx, dy);
		Log.d("debug page","24");
	}

	/**
	 * 数据适配器
	 */
	public class PageAdapter extends Adapter<RecyclerView.ViewHolder>{
		private List<?> dataList = null;
		private CallBack mCallBack = null;
		private int itemWidth = 0;
		private int itemCount = 0;

		/**
		 * 实例化适配器
		 *
		 * @param data
		 * @param callBack
		 */
		public PageAdapter(List<?> data, CallBack callBack) {
			Log.d("debug page","25");
			this.dataList = data;
			this.mCallBack = callBack;
			itemCount = dataList.size() + spanRow * spanColumn;

		}

		@Override
		public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			Log.d("debug page","26");
			if (itemWidth <= 0) {
				// 计算Item的宽度
				itemWidth = (parent.getWidth() - pageMargin * 2) / spanColumn;
			}
			Log.d("debug page","27");
			RecyclerView.ViewHolder holder = mCallBack.onCreateViewHolder(parent, viewType);
			Log.d("debug page","28");
			holder.itemView.measure(0, 0);
			holder.itemView.getLayoutParams().width = itemWidth;
			holder.itemView.getLayoutParams().height = holder.itemView.getMeasuredHeight();
			Log.d("debug page","29");
			return holder;
		}

		@Override
		public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
			Log.d("debug page","30");
			if (spanColumn == 1) {
				Log.d("debug page","31");
				// 每个Item距离左右两侧各pageMargin
				holder.itemView.getLayoutParams().width = itemWidth + pageMargin * 2;
				Log.d("debug page","32");
				holder.itemView.setPadding(pageMargin, 0, pageMargin, 0);

			} else {
				Log.d("debug page","33");
				int m = position % (spanRow * spanColumn);
				if (m < spanRow) {
					Log.d("debug page","34");
					// 每页左侧的Item距离左边pageMargin
					holder.itemView.getLayoutParams().width = itemWidth + pageMargin;
					holder.itemView.setPadding(pageMargin, 0, 0, 0);
				} else if (m >= spanRow * spanColumn - spanRow) {
					Log.d("debug page","35");
					// 每页右侧的Item距离右边pageMargin
					holder.itemView.getLayoutParams().width = itemWidth + pageMargin;
					holder.itemView.setPadding(0, 0, pageMargin, 0);
				} else {
					Log.d("debug page","36");
					// 中间的正常显示
					holder.itemView.getLayoutParams().width = itemWidth;
					holder.itemView.setPadding(0, 0, 0, 0);
				}
			}
			Log.d("debug page","37");
			if (position < dataList.size()) {
				Log.d("debug page","38");
				holder.itemView.setVisibility(View.VISIBLE);
				mCallBack.onBindViewHolder(holder, position);
			} else {
				Log.d("debug page","39");
				holder.itemView.setVisibility(View.INVISIBLE);
			}

		}

		@Override
		public int getItemCount() {
			Log.d("debug page","40");
			return itemCount;
		}

		/**
		 * 删除Item
		 * @param position 位置
		 */
		public void remove(int position) {
			Log.d("debug page","41");
			if (position < dataList.size()) {
				Log.d("debug page","42");
				// 删除数据
				dataList.remove(position);
				itemCount--;
				// 删除Item
				notifyItemRemoved(position);
				// 更新界面上发生改变的Item
				notifyItemRangeChanged(position, currentPage * spanRow * spanColumn);
				// 更新页码指示器
				update();
			}
		}

	}
	public interface CallBack {
		/**
		 * 创建VieHolder
		 *
		 * @param parent
		 * @param viewType
		 */

		RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);
		/**
		 * 绑定数据到ViewHolder
		 *
		 * @param holder
		 * @param position
		 */
		void onBindViewHolder(RecyclerView.ViewHolder holder,int position);
	}

}


