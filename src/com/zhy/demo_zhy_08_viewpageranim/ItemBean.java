package com.zhy.demo_zhy_08_viewpageranim;

import android.R.integer;

public class ItemBean {
	private int year;
	private int month;
	private int day;
	private String title;
	private int color;//颜色
	private int photo_id;//图片的位置
     
	//将他的位置绑定
	//新建的时候在最后一个
	public static int  pos_in_list=MainActivity.mList.size();
	
	
	public ItemBean(int year,int month,int day,String text,int color)
	{
		this.year=year;
		this.month=month;
		this.day=day;
		this.title=text;
		this.color=color;
	}
	
	public ItemBean(int year,int month,int day,String text,int color,int position)
	{
		this.year=year;
		this.month=month;
		this.day=day;
		this.title=text;
		this.color=color;
		this.photo_id=position;
	}
	
	public ItemBean (int year,int month,int day)
	{
		this.year=year;
		this.month=month;
		this.day=day;
	
	}
	
	public ItemBean(String text)
	{
		this.title=text;
	}
	
	public ItemBean (int position)
	{
		this.photo_id=position;
	}
	
     
     public void setTitle(String a) {
		title=a;	
	}
     
     public String  getTitle() {
		return title;
	}
     
     
     
     public void setyear(int a) {
    	 year=a;	
     }
     
     public int  getyear() {
    	 return year;
     }
     
     public void setmonth(int a) {
    	 month=a;	
     }
     
     public int  getmonth() {
    	 return month;
     }
     
     public void setdays(int a) {
    	 day=a;	
     }
     
     public int  getdays() {
    	 return day;
     }
     
     
     public void setColor(int a) {
    	 color=a;	
     }
     
     public int  getColor() {
    	 return color;
     }
     
     
     
     public void setPhoto(int a) {
    	 photo_id=a;	
     }
     
     public int  getPhoto() {
    	 return photo_id;
     }
     
     
     
     
}
