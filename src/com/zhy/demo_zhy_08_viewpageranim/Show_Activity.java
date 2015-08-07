package com.zhy.demo_zhy_08_viewpageranim;

import java.util.Calendar;
import java.util.Date;

import com.zhy.demo_zhy_08_viewpageranim.RippleLayout.RippleFinishListener;

import android.media.SoundPool;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class Show_Activity extends Activity{
	
	private int Flag_back;
	private int Flag;
	private int my_requestCode=1550;
	private int my_new_code=1110;
	public static ImageButton add;
	SoundPool soundPool;
	private int soundId;
	private boolean flag = false; 
	
	//position是图片的位置 ， numbers是一共的天数 ，color是选择的颜色
	static int year,month,day,numbers;
	private int position;
	int pos_list;
	String showyear,showmonth,showday;
	String showtitle;
	String shownumbers;
	
	Intent intent=null;
	FrameLayout show_FrameLayout;
	
	int width,height;	
	public static RippleLayout ripple;
	float density;
	
	ViewStub viewStub;
	ImportMenuView menuView;
	RippleLayout rl_close,rl_edit,rl_delete,rl_new;
	public  static  TextView show_title;
	public static TextView show_numbers;
	public static TextView show_year;
	public static TextView show_month;
	public static TextView show_day;
	ImageButton img_return;
	static int[] imageIds = new int[]
	{
		R.drawable.girl,
		R.drawable.backg, 
		R.drawable.backg1, 
		R.drawable.backg2,
		R.drawable.haizi,
		R.drawable.backg4, 
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.new_activity);
		
		position=1;
		
		show_FrameLayout=(FrameLayout)findViewById(R.id.show_FrameLayout);
		show_FrameLayout.setBackgroundResource(imageIds[position]);
		
		year=1995;
		month=8;
		day=11;
		numbers=7172;
		showtitle="破壳日";
		
		
		intent=this.getIntent();
		Bundle bundle=intent.getExtras();
		//Flag_back=bundle.getInt("flag_back");
		
		//从主页面跳过去    list中跳转过来
		//if (Flag_back==1) {
			
		    pos_list=bundle.getInt("pos_list");
	//	    System.out.println("po_list+++++"+pos_list);
			ItemBean  it=MainActivity.mList.get(pos_list);
		///设置数据
		    year=it.getyear();
		    month=it.getmonth()+1;
		    day=it.getdays();
		    position=it.getPhoto();
			showtitle=it.getTitle().trim();
		
			Date  begindate=new  Date(year, month-1, day);
			
			final Calendar c=Calendar.getInstance();
			Date    curDate=new Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH), Calendar.DAY_OF_MONTH);
			
			numbers=(int) getdays(curDate, begindate); 
			
		
			
		show_day=(TextView)findViewById(R.id.show_day);
		show_month=(TextView)findViewById(R.id.show_month);
		show_numbers=(TextView)findViewById(R.id.show_numbers);
		show_title=(TextView)findViewById(R.id.show_title);
		show_year=(TextView)findViewById(R.id.show_year);
		show_FrameLayout= (FrameLayout) findViewById(R.id.show_FrameLayout);
		showyear=year+"-";
		showmonth=month+"-";
		shownumbers=numbers+" ";
		showday=day+" ";
		show_day.setText(showday);
		show_month.setText(showmonth);
		show_year.setText(showyear);
		show_numbers.setText(shownumbers);
		show_title.setText(showtitle.toString());
		show_FrameLayout.setBackgroundResource(imageIds[position]);
		
		//new
		
		rl_new = (RippleLayout)findViewById(R.id.rl_new);
		rl_new.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle bundle1=new Bundle();
			    
				Intent intent=new Intent(Show_Activity.this,New_Activity.class);
				
				startActivity(intent);
			}
		});
		
		
		//return
		img_return=(ImageButton) findViewById(R.id.new_return1111);
		img_return.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FristActivity.instance.finish(); 
			    Intent retr_intent=new Intent(Show_Activity.this,MainActivity.class);
			    startActivity(retr_intent);
			    finish();
			}
		});
		
		
		
		//edit
		rl_edit = (RippleLayout)findViewById(R.id.rl_edit);
		rl_edit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {		
			    
				Bundle bundle2=new Bundle();
				Intent intent=new Intent(Show_Activity.this,Edit_Activity.class);
				intent.putExtra("pos_list", pos_list);
			
				startActivity(intent);
			}
		});

		//show delete's dialog
		initEvent();
		
		add = (ImageButton)findViewById(R.id.add);
        //soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        
        ripple = (RippleLayout)findViewById(R.id.more2);
        DisplayMetrics metircs = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics( metircs);
        width = metircs. widthPixels;
        height = metircs. heightPixels;        
		density  = metircs.density;
		
		menuView = (ImportMenuView)findViewById(R.id.main_activity_import_menu);
		menuView.setEnabled(false);
		
		ripple.setRippleFinishListener(new RippleFinishListener() {	
			@Override
			public void rippleFinish(int id) {
				
				if(id == R.id.more2){
					menuView.setVisibility(View.VISIBLE);
					menuView.setEnabled(true);
					menuView.setFocusable(true);				
					menuView.rl_closeVisiableAnimation();
					menuView.animation(Show_Activity.this);
					menuView.bringToFront();
					ripple.setVisibility(View.GONE);
				}			
			}
		});	
	}
	
	public long getdays(Date d1, Date d2) {
		
	    long i = 0;
	    long l = d1.getTime() - d2.getTime();
	    if (l<0) {
			return i=0;
		}
	    i = l / (1000 * 60 * 60 * 24);
	   return i;
	  
}
	
	//show delete's dialog
	 public void initEvent()
	    {
		 rl_delete=(RippleLayout)findViewById(R.id.rl_delete); 
		 rl_delete.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                showDialog1();
	            }
	        });


	    }
	    private void showDialog1()
	    {
	        AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        builder.setTitle("");
	        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                //删除
	            }
	        });
	        builder.setView(findViewById(R.id.mystyle));
	        builder.setNegativeButton("取消",new DialogInterface.OnClickListener(){
	            public void onClick(DialogInterface dialog, int which){
	            	
	            }
	        });
	        AlertDialog dialog=builder.create();
	        dialog.show();
	    }
	    
	
	public float dpiConvertToPixel(float dpi){		
		float pixel = dpi * density;	
		return pixel;
	}
	@Override
	protected void onStart() {
		super.onStart();
		
		
	}
	
	
	@Override
	protected void onDestroy() {
		if(soundPool!=null){
			soundPool.release();  
	        soundPool = null;  
		}
		super.onDestroy();
	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
	}
}

