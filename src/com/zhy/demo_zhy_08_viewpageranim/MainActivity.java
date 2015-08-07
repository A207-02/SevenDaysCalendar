package com.zhy.demo_zhy_08_viewpageranim;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import java.util.List;
import java.util.zip.Inflater;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.zhy.view.FristView;
import com.zhy.view.ListAdapter;
import com.zhy.view.RotateDownPageTransformer;
import com.zhy.view.ViewPagerCompat;
/**
 * http://blog.csdn.net/lmj623565791/article/details/40411921
 * @author zhy
 * 
 * 
 *listview 加进去了     但是没有数据进去
 *----适配器没有绑定到特定fristview上的listview上
 *  listview在不同手机上的size也不同
 *
 */
public class MainActivity extends Activity
{   
	public   static  List<ItemBean> mList=new ArrayList<ItemBean>(); 
	public   static  ListAdapter listAdapter;
	public   static  PagerAdapter  pAdapter;
	
	
	public   static  int  screenwidth;
	public   static  ListView lView;
	private ImageButton btnTiaozhuan,imgbut_new;
	private ViewPagerCompat mViewPager;
	//list_VG������ȫ����ŵ�viewgroup
    public  static  List<ViewGroup>  list_VG = new ArrayList<ViewGroup>();
    
    public  static  Map<ViewGroup, ItemBean>  map=new HashMap<ViewGroup, ItemBean>();
    
	//mviews�������viewpager�����viewgroup
	public  static   List<ViewGroup>  mViews = new ArrayList<ViewGroup>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
 	    FristActivity.instance.finish(); 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		

		//初始化数据
		initData();
		
		
		btnTiaozhuan = (ImageButton) findViewById(R.id.tiaozhuan);
		
		//ImageView imageView=(ImageView) findViewById(R.id.img);
		
		//ImageButton�����ת������
		btnTiaozhuan.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				int version = Integer.valueOf(android.os.Build.VERSION.SDK); 
				//�Ŵ���С��ת 
				startActivity(new Intent(MainActivity.this, CalendarActivity.class)); 
				if(version > 5 ){ 
				overridePendingTransition(R.anim.zoomin, R.anim.zoomout); 
				}
			}
		
	
		});
		
	

		
		//点击imgebut有放大效果    位ImageButton设置放大事件
	//	imgbut_new=(ImageButton) findViewById(R.id.im_new);
		imgbut_new.setOnTouchListener(new View.OnTouchListener(){
			public boolean onTouch(View v, MotionEvent event){
					 
				if(event.getAction() == MotionEvent.ACTION_DOWN){ //更改为按下时的背景图片
						v.setBackgroundResource(R.drawable.jieshu); 
								
							}else if(event.getAction() == MotionEvent.ACTION_UP){ //改为抬起时的图片 
									v.setBackgroundResource(R.drawable.anniu); 
									} 
							return false;
							} 
					});
		
		//点击新建按钮跳转到编辑界面
		imgbut_new.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent  edit_newintent = new  Intent(MainActivity.this,New_Activity.class);
				// 传递新建  标志
				edit_newintent.putExtra("flag", 1);
				startActivity(edit_newintent);
		        overridePendingTransition(R.anim.enteralph,R.anim.outalph);
				
	/////////////////////////
		        finish();
			}
		});
		
		
		
		
		
		//�õ���Ļ�Ŀ��
		WindowManager wm = this.getWindowManager();
		 
	    screenwidth = wm.getDefaultDisplay().getWidth();
	    
		mViewPager = (ViewPagerCompat) findViewById(R.id.id_viewpager);

	//	mViewPager.setPageTransformer(true, new DepthPageTransformer());
		mViewPager.setPageTransformer(true, new RotateDownPageTransformer());
		mViewPager.setCurrentItem(0);
		//��mviewpager����������
		
		  pAdapter=new PagerAdapter() {
			
			@Override
			public ViewGroup instantiateItem(ViewGroup container, int position)
			{   
				
//				ViewGroup  singleViewGroup=mViews.get(position);
//				LayoutInflater inflater=LayoutInflater.from(getApplicationContext());
//				 singleViewGroup=  (ViewGroup) inflater.inflate(R.layout.single_view, null);   
//	            TextView  show_year=(TextView) singleViewGroup.findViewById(R.id.show_year);
//    	         TextView  show_mon=(TextView) singleViewGroup.findViewById(R.id.show_month);
//	            TextView  show_day=(TextView) singleViewGroup.findViewById(R.id.show_day);
//            TextView  show_title=(TextView) singleViewGroup.findViewById(R.id.show_title);
//            TextView  show_numbers=(TextView) singleViewGroup.findViewById(R.id.show_numbers);
//	         LinearLayout lay=(LinearLayout) singleViewGroup.findViewById(R.id.singleview_lay);
//	         show_year.setText(MainActivity.mList.get(0).getyear());
//	         show_year.setText("kkkkkk");
//	            show_mon.setText(mMonth);
//	            show_day.setText(mDay);
//	           show_title.setText(mTitle);
//	            
//	            Date  begindate=new  Date(mYear, mMonth, mDay);
//				
//				final Calendar c=Calendar.getInstance();
//				Date    curDate=new Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH), Calendar.DAY_OF_MONTH);
//				
//				int  numbers=(int) getdays(curDate, begindate); 
//				
//	            show_numbers.setText(numbers);
//	            lay.setBackgroundResource(Show_Activity.imageIds[image_position]);

                  
				container.addView(mViews.get(position));
				return mViews.get(position);
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object)
			{

				container.removeView(mViews.get(position));
			}

			@Override
			public boolean isViewFromObject(View view, Object object)
			{
				return view == object;
			}

			@Override
			public int getCount()
			{
				return list_VG.size();
			}
		};
		
		mViewPager.setAdapter(pAdapter);
		
	}

	private void initData()
	{   
		
		//���ȼ���fristview
		
		ViewGroup fristViewGroup=(FristView) findViewById(R.id.id_fristview);
		LayoutInflater inflater=getLayoutInflater();
		
        fristViewGroup=(ViewGroup) inflater.inflate(R.layout.fristview, null);
        
        imgbut_new=(ImageButton)fristViewGroup.findViewById(R.id.im_new);
       
        list_VG.add(fristViewGroup);
        mViews.add(fristViewGroup);		
		
		
	//	ItemBean it1=new ItemBean(2015, 8, 6, "SevenDays破壳日", 2);
	//	mList.add(it1);	

		listAdapter=new ListAdapter(this, mList);
	   
	    lView = (ListView)fristViewGroup.findViewById(R.id.list);
		
		
		//ȥ��listview�ķָ���
		lView.setDividerHeight(0);
		lView.setAdapter(listAdapter);
		
		//  listview 单项跳转 到New_Activity中
	
		lView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
                Intent intent=new Intent(MainActivity.this,Show_Activity.class);
			  	intent.putExtra("pos_list", position);
			  	intent.putExtra("flag_back", 1);  //表示是从listview跳过来的
			  	startActivity(intent);
			}
		});
		
		
		
	}
	
	
	
	
}
