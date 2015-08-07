package com.zhy.demo_zhy_08_viewpageranim;


import java.text.SimpleDateFormat;
import java.util.Date;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
 


public class CalendarActivity extends Activity implements OnGestureListener,OnTouchListener {
	
	protected static final int clickTemp = -1;
	
	private GestureDetector gestureDetector = null;
	private CalendarAdapter calV = null;
	private SpecialCalendar sc = null;
	private LunarCalendar lc = null; 
	private GridView gridView = null;
	private TextView topText = null ,tvshow=null,myTV=null;
	private static int jumpMonth = 0;      //ÿ�λ��������ӻ��ȥһ����,Ĭ��Ϊ0������ʾ��ǰ�£�
	private static int jumpYear = 0;       //������Խһ�꣬�����ӻ��߼�ȥһ��,Ĭ��Ϊ0(����ǰ��)
	private int year_c = 0;
	private int month_c = 0;
	private int day_c = 0;
	private String currentDate = "";
	private Button btn_back;
	private TextView tv;

	public CalendarActivity() {

		Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
    	currentDate = sdf.format(date);  //��������
    	year_c = Integer.parseInt(currentDate.split("-")[0]);
    	month_c = Integer.parseInt(currentDate.split("-")[1]);
    	day_c = Integer.parseInt(currentDate.split("-")[2]);
    	
    	
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.calendar);
		btn_back=(Button)findViewById(R.id.btn_back);
		myTV=(TextView)findViewById(R.id.tv_today);
		registerForContextMenu(myTV);

		myTV.setBackgroundDrawable(getResources().getDrawable(R.drawable.back_today));
		gestureDetector = new GestureDetector((OnGestureListener)this);
		
		calV = new CalendarAdapter(this,getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);
        addGridView();
        gridView.setAdapter(calV);
        
		topText = (TextView) findViewById(R.id.tv_month);
		addTextToTopTextView(topText);
		
		tv=(TextView)findViewById(R.id.tv_today);
		tv.setBackgroundColor(0x00ff00ff);
		
	btn_back.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
    	  Intent intent=new Intent(CalendarActivity.this,MainActivity.class);
          startActivity(intent);
          finish();
      }} );
      
  
}
	
	/**
	 * �����˵�
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu,View v,ContextMenuInfo menuInfo) {

		menu.add(0, menu.FIRST, 0, "今天");
		
	}
	
	/**
	 * ѡ��˵�
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
        case Menu.FIRST:
        	//��ת������
        	int xMonth = jumpMonth;
        	int xYear = jumpYear;
        	int gvFlag =0;
        	jumpMonth = 0;
        	jumpYear = 0;
        	addGridView();   //���һ��gridView
        	year_c = Integer.parseInt(currentDate.split("-")[0]);
        	month_c = Integer.parseInt(currentDate.split("-")[1]);
        	day_c = Integer.parseInt(currentDate.split("-")[2]);
        	calV = new CalendarAdapter(this,getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);
	        gridView.setAdapter(calV);
	        addTextToTopTextView(topText);
	        gvFlag++;
        	break;
        }
		
        return true;
	}
	
	
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		int gvFlag = 0;         //ÿ�����gridview��viewflipper��ʱ��ı��
		if (e1.getX() - e2.getX() > 120) {
            //���󻬶�
			addGridView();   //���һ��gridView
			jumpMonth++;     //��һ����
			
			calV = new CalendarAdapter(this,getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);
	        gridView.setAdapter(calV);
	        addTextToTopTextView(topText);
	        gvFlag++;
	        return true;
	      	
		} 
		if (e1.getX() - e2.getX() < -120) {
            //���һ���
			addGridView();   //���һ��gridView
			jumpMonth--;     //��һ����
			
			calV = new CalendarAdapter(this,getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);
	        gridView.setAdapter(calV);
	        gvFlag++;
	        addTextToTopTextView(topText);

	        return true;
		}
		 if(e1.getY()-e2.getY()>20){
			 //���ϻ���
			 Intent intent=new Intent(CalendarActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
				return true;
		}
		 if(e1.getY()-e2.getY()<-20){
			 //���»���
			 Intent intent=new Intent(CalendarActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
				return true;
		}
		 return false;
	}
	



	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return this.gestureDetector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//���ͷ�������,�µ���Ϣ
	public void addTextToTopTextView(TextView view){
		StringBuffer textDate = new StringBuffer();
		textDate.append(calV.getShowYear()).append("年").append(
				calV.getShowMonth()).append("月").append("\t");
		view.setText(textDate);
		view.setTextColor(Color.BLACK);
		view.setTypeface(Typeface.DEFAULT_BOLD);
	}
	
	//���gridview
	private void addGridView() {
		
		gridView =(GridView)findViewById(R.id.gridview);

		gridView.setOnTouchListener(new OnTouchListener() {
            //��gridview�еĴ����¼��ش���gestureDetector
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return CalendarActivity.this.gestureDetector.onTouchEvent(event);
			}
		});           
		
		gridView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				 // text_view=(TextView)findViewById(R.id.tvtext);
				 // text_view.setBackgroundResource(R.drawable.bg);
				  //����κ�һ��item���õ����item������(�ų����������յ�����(�������Ӧ))
				  int startPosition = calV.getStartPositon();
				  int endPosition = calV.getEndPosition();
				  if(startPosition <= position+7  && position <= endPosition-7){
					  String scheduleDay = calV.getDateByClickItem(position).split("\\.")[0];  //��һ�������
					  String scheduleLunarDay = calV.getDateByClickItem(position).split("\\.")[1];  //��һ�������
	                  String scheduleYear = calV.getShowYear();
	                  String scheduleMonth = calV.getShowMonth();
	                  	
	                  //calV.setSelection(clickTemp);
	                  //calV.notifyDataSetChanged();
	                  tvshow=(TextView)findViewById(R.id.tv_show);
	                  String msg1= getText(scheduleYear+"年"+scheduleMonth+"月"+scheduleDay+"日"+"  "+scheduleLunarDay+" \n "+"\n"+calV.getCyclical() +calV.getAnimalsYear()+"年"+ "  "+calV.getLeapMonth()).toString();
	                 
	                  tvshow.setText(msg1);
	                }
				  }

			
		});
	}

	protected Object getText(String string) {
		// TODO Auto-generated method stub
		return string;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return gestureDetector.onTouchEvent(event);
	}
	
}