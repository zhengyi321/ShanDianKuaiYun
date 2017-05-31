package com.zhyan.shandiankuaiyuanwidgetlib.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhyan.shandiankuaiyuanwidgetlib.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



public class MyFragment extends Fragment  {
	 List<TextView> textviews = new ArrayList<TextView>();
	    List<ImageView> imageviews = new ArrayList<ImageView>();
	    


	
		int day=0 ; 		//当月的天数
	    private TextView one;
	    private TextView two;
	    private TextView three;
	    private TextView four;
	    private TextView five;
	    private TextView six;
	    private TextView senve;
	    private TextView onetwo;
	    private TextView twotwo;
	    private TextView threetwo;
	    private TextView fourtwo;
	    private TextView fivetwo;
	    private TextView sixtwo;
	    private TextView senvetwo;
	    private TextView onethree;
	    private TextView twothree;
	    private TextView threethree;
	    private TextView fourthree;
	    private TextView fivethree;
	    private TextView sixthree;
	    private TextView senvethree;
	    private TextView onefour;
	    private TextView twofour;
	    private TextView threefour;
	    private TextView fourfour;
	    private TextView fivefour;
	    private TextView sixfour;
	    private TextView senvefour;
	    private TextView onefive;
	    private TextView twofive;
	    private TextView threefive;
	    private TextView fourfive;
	    private TextView fivefive;
	    private TextView sixfive;
	    private TextView senvefive;
	    private TextView onesix;
	    private TextView twosix;
	    private TextView threesix;
	    private TextView foursix;
	    private TextView fivesix;
	    private TextView sixsix;
	    private TextView senvesix;
	    private ImageView one_pic;
	    private ImageView two_pic;
	    private ImageView three_pic;
	    private ImageView four_pic;
	    private ImageView five_pic;
	    private ImageView six_pic;
	    private ImageView senve_pic;
	    private ImageView onetwo_pic;
	    private ImageView twotwo_pic;
	    private ImageView threetwo_pic;
	    private ImageView fourtwo_pic;
	    private ImageView fivetwo_pic;
	    private ImageView sixtwo_pic;
	    private ImageView senvetwo_pic;
	    private ImageView onethree_pic;
	    private ImageView twothree_pic;
	    private ImageView threethree_pic;
	    private ImageView fourthree_pic;
	    private ImageView fivethree_pic;
	    private ImageView sixthree_pic;
	    private ImageView senvethree_pic;
	    private ImageView onefour_pic;
	    private ImageView twofour_pic;
	    private ImageView threefour_pic;
	    private ImageView fourfour_pic;
	    private ImageView fivefour_pic;
	    private ImageView sixfour_pic;
	    private ImageView senvefour_pic;
	    private ImageView onefive_pic;
	    private ImageView twofive_pic;
	    private ImageView threefive_pic;
	    private ImageView fourfive_pic;
	    private ImageView fivefive_pic;
	    private ImageView sixfive_pic;
	    private ImageView senvefive_pic;
	    private ImageView onesix_pic;
	    private ImageView twosix_pic;
	    private ImageView threesix_pic;
	    private ImageView foursix_pic;
	    private ImageView fivesix_pic;
	    private ImageView sixsix_pic;
	    private ImageView senvesix_pic;
	    private LinearLayout toshow;
	    private Button qiandao;
	    private int num;
	    private MyBroadCast broadcast;
	    public MyFragment() {
	        // Required empty public constructor
	    }
	    private class MyBroadCast extends BroadcastReceiver{

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				
				if("s".equals(intent.getStringExtra("sing"))){
					int date = new Date().getDate();
					imageviews.get(num+date-1).setVisibility(View.VISIBLE);
				}else{
					ArrayList<Integer> days=intent.getIntegerArrayListExtra("days");
					for(int i=0;i<days.size();i++){
						imageviews.get(num+days.get(i)-1).setVisibility(View.VISIBLE);
						
					}
				
//					Log.e(">>>>", days.get(0)+"");
				}
			}
	    	
	    }
	    @Override
	    public void onDestroy() {
	    	// TODO Auto-generated method stub
	    	super.onDestroy();
	    	getActivity().unregisterReceiver(broadcast);
	    }
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                             Bundle savedInstanceState) {
	        // Inflate the layout for new MyClick() fragment
	    	broadcast=new MyBroadCast();
	    	getActivity().registerReceiver(broadcast, new IntentFilter("sign"));
	        View view = inflater.inflate(R.layout.fragment_mycalendview, container, false);
	        one = (TextView) view.findViewById(R.id.one);
	        two = (TextView) view.findViewById(R.id.two);
	        three = (TextView) view.findViewById(R.id.three);
	        four = (TextView) view.findViewById(R.id.four);
	        five = (TextView) view.findViewById(R.id.five);
	        six = (TextView) view.findViewById(R.id.six);
	        senve = (TextView) view.findViewById(R.id.senve);
	        onetwo = (TextView) view.findViewById(R.id.onetwo);
	        twotwo = (TextView) view.findViewById(R.id.twotwo);
	        threetwo = (TextView) view.findViewById(R.id.threetwo);
	        fourtwo = (TextView) view.findViewById(R.id.fourtwo);
	        fivetwo = (TextView) view.findViewById(R.id.fivetwo);
	        sixtwo = (TextView) view.findViewById(R.id.sixtwo);
	        senvetwo = (TextView) view.findViewById(R.id.senvetwo);
	        onethree = (TextView) view.findViewById(R.id.onethree);
	        twothree = (TextView) view.findViewById(R.id.twothree);
	        threethree = (TextView) view.findViewById(R.id.threethree);
	        fourthree = (TextView) view.findViewById(R.id.fourthree);
	        fivethree = (TextView) view.findViewById(R.id.fivethree);
	        sixthree = (TextView) view.findViewById(R.id.sixthree);
	        senvethree = (TextView) view.findViewById(R.id.senvethree);
	        onefour = (TextView) view.findViewById(R.id.onefour);
	        twofour = (TextView) view.findViewById(R.id.twofour);
	        threefour = (TextView) view.findViewById(R.id.threefour);
	        fourfour = (TextView) view.findViewById(R.id.fourfour);
	        fivefour = (TextView) view.findViewById(R.id.fivefour);
	        sixfour = (TextView) view.findViewById(R.id.sixfour);
	        senvefour = (TextView) view.findViewById(R.id.senvefour);
	        onefive = (TextView) view.findViewById(R.id.onefive);
	        twofive = (TextView) view.findViewById(R.id.twofive);
	        threefive = (TextView) view.findViewById(R.id.threefive);
	        fourfive = (TextView) view.findViewById(R.id.fourfive);
	        fivefive = (TextView) view.findViewById(R.id.fivefive);
	        sixfive = (TextView) view.findViewById(R.id.sixfive);
	        senvefive = (TextView) view.findViewById(R.id.senvefive);
	        onesix = (TextView) view.findViewById(R.id.onesix);
	        twosix = (TextView) view.findViewById(R.id.twosix);
	        threesix = (TextView) view.findViewById(R.id.threesix);
	        foursix = (TextView) view.findViewById(R.id.foursix);
	        fivesix = (TextView) view.findViewById(R.id.fivesix);
	        sixsix = (TextView) view.findViewById(R.id.sixsix);
	        senvesix = (TextView) view.findViewById(R.id.senvesix);
	        one_pic = (ImageView) view.findViewById(R.id.one_pic);
	        two_pic = (ImageView) view.findViewById(R.id.two_pic);
	        three_pic = (ImageView) view.findViewById(R.id.three_pic);
	        four_pic = (ImageView) view.findViewById(R.id.four_pic);
	        five_pic = (ImageView) view.findViewById(R.id.five_pic);
	        six_pic = (ImageView) view.findViewById(R.id.six_pic);
	        senve_pic = (ImageView) view.findViewById(R.id.senve_pic);
	        onetwo_pic = (ImageView) view.findViewById(R.id.onetwo_pic);
	        twotwo_pic = (ImageView) view.findViewById(R.id.twotwo_pic);
	        threetwo_pic = (ImageView) view.findViewById(R.id.threetwo_pic);
	        fourtwo_pic = (ImageView) view.findViewById(R.id.fourtwo_pic);
	        fivetwo_pic = (ImageView) view.findViewById(R.id.fivetwo_pic);
	        sixtwo_pic = (ImageView)view.findViewById(R.id.sixtwo_pic);
	        senvetwo_pic = (ImageView)view.findViewById(R.id.senvetwo_pic);
	        onethree_pic = (ImageView) view.findViewById(R.id.onethree_pic);
	        twothree_pic = (ImageView)view.findViewById(R.id.twothree_pic);
	        threethree_pic = (ImageView)view.findViewById(R.id.threethree_pic);
	        fourthree_pic = (ImageView) view.findViewById(R.id.fourthree_pic);
	        fivethree_pic = (ImageView)view.findViewById(R.id.fivethree_pic);
	        sixthree_pic = (ImageView) view.findViewById(R.id.sixthree_pic);
	        senvethree_pic = (ImageView) view.findViewById(R.id.senvethree_pic);
	        onefour_pic = (ImageView) view.findViewById(R.id.onefour_pic);
	        twofour_pic = (ImageView) view.findViewById(R.id.twofour_pic);
	        threefour_pic = (ImageView)view.findViewById(R.id.threefour_pic);
	        fourfour_pic = (ImageView) view.findViewById(R.id.fourfour_pic);
	        fivefour_pic = (ImageView) view.findViewById(R.id.fivefour_pic);
	        sixfour_pic = (ImageView) view.findViewById(R.id.sixfour_pic);
	        senvefour_pic = (ImageView) view.findViewById(R.id.senvefour_pic);
	        onefive_pic = (ImageView) view.findViewById(R.id.onefive_pic);
	        twofive_pic = (ImageView)view.findViewById(R.id.twofive_pic);
	        threefive_pic = (ImageView) view.findViewById(R.id.threefive_pic);
	        fourfive_pic = (ImageView) view.findViewById(R.id.fourfive_pic);
	        fivefive_pic = (ImageView) view.findViewById(R.id.fivefive_pic);
	        sixfive_pic = (ImageView) view.findViewById(R.id.sixfive_pic);
	        senvefive_pic = (ImageView)view.findViewById(R.id.senvefive_pic);
	        onesix_pic = (ImageView) view.findViewById(R.id.onesix_pic);
	        twosix_pic = (ImageView)view.findViewById(R.id.twosix_pic);
	        threesix_pic = (ImageView) view.findViewById(R.id.threesix_pic);
	        foursix_pic = (ImageView) view.findViewById(R.id.foursix_pic);
	        fivesix_pic = (ImageView) view.findViewById(R.id.fivesix_pic);
	        sixsix_pic = (ImageView) view.findViewById(R.id.sixsix_pic);
	        senvesix_pic = (ImageView) view.findViewById(R.id.senvesix_pic);
	        toshow = (LinearLayout) view.findViewById(R.id.toshow);
	        qiandao = (Button) view.findViewById(R.id.qiandao);
	        textviews.add(one);
	        textviews.add(two);
	        textviews.add(three);
	        textviews.add(four);
	        textviews.add(five);
	        textviews.add(six);
	        textviews.add(senve);
	        textviews.add(onetwo);
	        textviews.add(twotwo);
	        textviews.add(threetwo);
	        textviews.add(fourtwo);
	        textviews.add(fivetwo);
	        textviews.add(sixtwo);
	        textviews.add(senvetwo);
	        textviews.add(onethree);
	        textviews.add(twothree);
	        textviews.add(threethree);
	        textviews.add(fourthree);
	        textviews.add(fivethree);
	        textviews.add(sixthree);
	        textviews.add(senvethree);
	        textviews.add(onefour);
	        textviews.add(twofour);
	        textviews.add(threefour);
	        textviews.add(fourfour);
	        textviews.add(fivefour);
	        textviews.add(sixfour);
	        textviews.add(senvefour);
	        textviews.add(onefive);
	        textviews.add(twofive);
	        textviews.add(threefive);
	        textviews.add(fourfive);
	        textviews.add(fivefive);
	        textviews.add(sixfive);
	        textviews.add(senvefive);
	        textviews.add(onesix);
	        textviews.add(twosix);
	        textviews.add(threesix);
	        textviews.add(foursix);
	        textviews.add(fivesix);
	        textviews.add(sixsix);
	        textviews.add(senvesix);
	        imageviews.add(one_pic);
	        imageviews.add(two_pic);
	        imageviews.add(three_pic);
	        imageviews.add(four_pic);
	        imageviews.add(five_pic);
	        imageviews.add(six_pic);
	        imageviews.add(senve_pic);
	        imageviews.add(onetwo_pic);
	        imageviews.add(twotwo_pic);
	        imageviews.add(threetwo_pic);
	        imageviews.add(fourtwo_pic);
	        imageviews.add(fivetwo_pic);
	        imageviews.add(sixtwo_pic);
	        imageviews.add(senvetwo_pic);
	        imageviews.add(onethree_pic);
	        imageviews.add(twothree_pic);
	        imageviews.add(threethree_pic);
	        imageviews.add(fourthree_pic);
	        imageviews.add(fivethree_pic);
	        imageviews.add(sixthree_pic);
	        imageviews.add(senvethree_pic);
	        imageviews.add(onefour_pic);
	        imageviews.add(twofour_pic);
	        imageviews.add(threefour_pic);
	        imageviews.add(fourfour_pic);
	        imageviews.add(fivefour_pic);
	        imageviews.add(sixfour_pic);
	        imageviews.add(senvefour_pic);
	        imageviews.add(onefive_pic);
	        imageviews.add(twofive_pic);
	        imageviews.add(threefive_pic);
	        imageviews.add(fourfive_pic);
	        imageviews.add(fivefive_pic);
	        imageviews.add(sixfive_pic);
	        imageviews.add(senvefive_pic);
	        imageviews.add(onesix_pic);
	        imageviews.add(twosix_pic);
	        imageviews.add(threesix_pic);
	        imageviews.add(foursix_pic);
	        imageviews.add(fivesix_pic);
	        imageviews.add(sixsix_pic);
	        imageviews.add(senvesix_pic);
	        return view;
	    }

	    @Override
	    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
	        super.onActivityCreated(savedInstanceState);
	        init();
	    }

	    private void init() {
	        Calendar calendar = Calendar.getInstance();
	        calendar.set(Calendar.DAY_OF_MONTH, 1);
	        num = calendar.get(Calendar.DAY_OF_WEEK) - 1;
	        getWeek();
	        Log.e("....", num +"");
	        switch (num) {
	            case 0:
	                setNum(0);
	                break;
	            case 1:
	                setNum(1);
	                break;
	            case 2:
	                setNum(2);
	                break;
	            case 3:
	                setNum(3);
	                break;
	            case 4:
	                setNum(4);
	                break;
	            case 5:
	                setNum(5);
	                break;
	            case 6:
	                setNum(6);
	                break;
	        }
	        /*qiandao.setOnClickListener(new MyClick());*/
	    }

	    private void setNum(int week) {
	        int k = 1;
	        for (int i = week; i <day+week ; i++) {
	            textviews.get(i).setText(k+"");
	            k++;
	        }
	    }

	   /* private class MyClick implements View.OnClickListener {
	        @Override
	        public void onClick(View v) {
	            switch (v.getId()) {
	                case R.id.qiandao:
	                    int date = new Date().getDate();
	                    imageviews.get(num+date-1).setVisibility(View.VISIBLE);
	                    break;
	            }
	        }
	    }*/

	    private void getWeek(){
	        Date date = new Date();
	        int year = date.getYear();
	        int month =date.getMonth();
	        boolean bool=false;
	// 判断输入的年份是否是闰年备用
	        if(year%4==0&&year%100!=0||year%400==0){
	            bool=true;
	        }
	      
	            switch(month+1){ //根据月不同给day赋值进行计算
	                case 1: //同时day还会用于保存当月天数后面的输出
	                case 3:
	                case 5:
	                case 7:
	                case 8:
	                case 10:
	                case 12:
	             	day=31;
	             	break;
	             	
	                case 4:
	                case 6:
	                case 9:
	                case 11:day=30;break;
	// 根据是否闰年来决定2月多少天
	                case 2:if(bool){
	                    day=29;
	                    break;
	                }else{
	                    day=28;
	                    break;
	                }
	//在获得输入月份之前所有天数的同时
	//还获得了输入月份的天数备用
	            }
	     
	// 把总天数对7取余计算出当月第一天是星期几
	        for(int i=1;i<=day;i++){
	//控制每到星期6就换行
	//原理为总天数加上当月天数对7取余
	        }
	    }
		/*@Override
		public void todaysign() {
			   int date = new Date().getDate();
               imageviews.get(num+date-1).setVisibility(View.VISIBLE);
			
		}*/
		
		

}
