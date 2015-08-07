package com.zhy.view;

import java.text.ParsePosition;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.zhy.demo_zhy_08_viewpageranim.*;
import com.zhy.view.*;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


//�����������ֺ���ݽ��а�

public class ListAdapter extends BaseAdapter {

	 public static  List<ItemBean >   mList ;
     private  LayoutInflater mInflater;
      //20 ����ɫ
     public int[] styleColors =new int[]{0xff3366C2, 0xff4491df, 0xffbe3522,
    		 0xffFF66CC,0xff33cc00,0xffcc0033,0xffff0066,0xff99ff99,0xff9900c2,
    		 0xffff3333,0xff330000,0xff00cc33,0xffffcf00,0xffff6666,0xffFFFF66,
    		 0xff33FFCC,0xff9966FF,0xff330033,0xff3300CC,0xff66ff66};

 	 int i=0;
     public ListAdapter(Context context ,List<ItemBean>  list) {
		mList=list;
    	mInflater=LayoutInflater.from(context);
	}
	
	
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int arg0) {
		
		return mList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
	
		return 0;
	}
    //����������ĵ���  ��Ӧ����
	@Override
	public View getView(int position, View convertview, ViewGroup arg2) {
		if (convertview==null) {
			convertview=mInflater.inflate(R.layout.single_listview, null);
		}
		
		
		TextView days=(TextView) convertview.findViewById(R.id.days);
		TextView words=(TextView) convertview.findViewById(R.id.words);
		ItemBean it=mList.get(position);
		
		//设置天数
		int year=it.getyear();
		int  mon=it.getmonth();
		int  day=it.getdays();
		
		Date  begindate=new  Date(year, mon, day);
		
		final Calendar c=Calendar.getInstance();
		Date    curDate=new Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH), Calendar.DAY_OF_MONTH);
		
		int n=(int) getdays(curDate, begindate); 
		
		
		String string=String.valueOf(n);
		
		days.setText(string.trim());
		
		words.setText(it.getTitle().toString());
		
		
	  //����textview����ɫ	
		int color=it.getColor();
	
		days.setBackgroundColor(color);
// ����textview__days�Ķ�̬����
		//�õ��ؼ��ĳ��� ---����������
		
		if (0<=n&&n<10) {
			days.setWidth(50+n);
		}
        if (9<n&&n<100) {
			days.setWidth(60+n);
		} 
        if (99<n&&n<1000) {
			if (it.getTitle()==null) {
				if (60+n<MainActivity.screenwidth) {
					days.setWidth(75+n);
				}
				else  days.setWidth(MainActivity.screenwidth);
			}
        	if ((60+n)<MainActivity.screenwidth*0.8) {
        		days.setWidth(75+n);
			}
			
				if ((60+n)>MainActivity.screenwidth*0.8) {
					
					days.setWidth((int) (MainActivity.screenwidth*0.7));
					words.setWidth((int) (MainActivity.screenwidth*0.3));
					words.setBackgroundColor(styleColors[color]);
					
					words.setTextColor(0xffffffff);
					
					words.setText(it.getTitle().toString());
					words.setGravity(Gravity.RIGHT);
					words.setGravity(Gravity.CENTER);
					
				}
			
        }
		return convertview;
		
	}
	
	public long getdays(Date d1, Date d2) {
		
		    long i = 0;
		    
		    long l = d1.getTime() - d2.getTime();
		    if (l<0) {
				return i=0;
			}
		    i = l / (1000 * 60 * 60 * 24);
		    // ����������������
		   return i;
		  
	}

}
