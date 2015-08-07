package com.zhy.demo_zhy_08_viewpageranim;

import java.util.Calendar;
import java.util.Date;

import com.zhy.view.SingleView;

import android.R.integer;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class New_Activity extends Activity
{
	private TextView mDateDisplay;
    static int mYear,year1;
    static int mMonth;
    static int mDay;
    static int mPosition;
    static String mTitle;
    static EditText title_editText;
	static TextView color_TextView;
	static int choose_color=12544;
	static int image_position;
	
	public final static int my_requestCode=1550;
	public final static int my_new_code=1110;
	public final static int result_code = 0;

	private Spinner recycleSpinner;
	
	Context context;  
	
    static final int DATE_DIALOG_ID = 0;
	
    private ImageButton new_return;
    private ImageButton new_save;
    
    //颜色初始值
    public int[] styleColors =new int[]{0xff3366C2, 0xff4491df, 0xffbe3522,0xffFF66CC,0xff33cc00,0xffcc0033,0xffff0066,0xff99ff99,0xff9900c2,0xffff3333,0xff330000,0xff00cc33,0xffffcf00,0xffff6666,0xffFFFF66,0xff33FFCC,0xff9966FF,0xff330033,0xff3300CC,0xff66ff66,0x00ffff,0x3300ff,0xccff99,0x33cc66};
    
    
    static int[] imageIds = new int[]
	{
		R.drawable.girl,
		R.drawable.backg, 
		R.drawable.backg1, 
		R.drawable.backg2,
		R.drawable.haizi,
		R.drawable.backg4, 
	};
    private int Flag;
    private int Flag_back;
	Gallery gallery;
	ImageView imageView;
	FrameLayout F1;
	Intent intent=null;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.edit_activity);
		F1=(FrameLayout)findViewById(R.id.New_Layout);
		title_editText=(EditText) findViewById(R.id.title_edittext);
		intent=this.getIntent();
		Bundle bundle=intent.getExtras();
		
			final Calendar c=Calendar.getInstance();
    		mYear=c.get(Calendar.YEAR);
    	    mMonth=c.get(Calendar.MONTH); 
    	    mDay=c.get(Calendar.DAY_OF_MONTH);
    	    mTitle=title_editText.getText().toString().trim();
    	    
	//	}
		
		color_TextView=(TextView)findViewById(R.id.color_textview);
		
		new_return=(ImageButton)findViewById(R.id.new_return);
		new_return.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent=new Intent(New_Activity.this,MainActivity.class);
				startActivity(intent);
				
			}
		});

		new_save=(ImageButton)findViewById(R.id.new_save);
		new_save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				Intent intent=new Intent(New_Activity.this,Show_Activity.class);
/////////////////////////			
				
				 mTitle =title_editText.getText().toString().trim();
				 String  time =mDateDisplay.getText().toString().trim() ;
		            ItemBean it=new  ItemBean(mYear,mMonth,mDay,mTitle,choose_color,image_position); 
		            
		    	    MainActivity.mList.add(it);
		            intent.putExtra("pos_list",MainActivity.mList.size()-1);
		        
					 startActivity(intent);
					   
					
			    	 // 新建生成一个单页viewgroup将它加到viewpager的适配器里
			   	    ViewGroup singleViewGroup=new SingleView(getApplicationContext());
			    		LayoutInflater inflater=getLayoutInflater();
			            singleViewGroup=  (ViewGroup) inflater.inflate(R.layout.single_view, null);
			           
			         TextView  show_year=(TextView) singleViewGroup.findViewById(R.id.show_year);
	        	    TextView  show_mon=(TextView) singleViewGroup.findViewById(R.id.show_month);
			        TextView  show_day=(TextView) singleViewGroup.findViewById(R.id.show_day);
		            TextView  show_title=(TextView) singleViewGroup.findViewById(R.id.show_title);
		            TextView  show_numbers=(TextView) singleViewGroup.findViewById(R.id.show_numbers);
			         LinearLayout lay=(LinearLayout) singleViewGroup.findViewById(R.id.singleview_lay);
			          System.out.println(MainActivity.mList.size()); 
			          String[] a=time.split("-");
			          int n1=Integer.parseInt(a[0].trim());
			          int n2=Integer.parseInt(a[1].trim());
			          int n3=Integer.parseInt(a[2].trim());
			          
			       //   System.out.println("KKKKKKKKKKK"+n1);
			          show_year.setText(String.valueOf(n1)+"-");
			           show_title.setText(mTitle);
			          
			            show_mon.setText(String.valueOf(n2)+"-");
			           show_day.setText(String.valueOf(n3)+" ");
			            
//			            
//			             Date  begindate=new  Date(n1, n2, n3);
//						
//						final Calendar c=Calendar.getInstance();
//						@SuppressWarnings("deprecation")
//						Date    curDate=new Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH), Calendar.DAY_OF_MONTH);
//						
//						int  numbers=(int) getdays(curDate, begindate); 
//						
//			            show_numbers.setText(numbers);
			           int i=0;
			           if (i>5) {
						 i=0;
					     }
			           lay.setBackgroundResource(Show_Activity.imageIds[i++]);
                     
			           
					      
			            MainActivity.list_VG.add(singleViewGroup);
			        ////////////////   刷新布局 
			            MainActivity.listAdapter.notifyDataSetChanged();
			        ///////////////    
			            MainActivity.mViews.add(singleViewGroup);
			            
			        ////////    
			            MainActivity.pAdapter.notifyDataSetChanged();
			            
			            startActivity(intent);
	          
				}	
			
	
		});
		
		//EditText
		title_editText=(EditText)findViewById(R.id.title_edittext);
		title_editText.setText(mTitle);
		title_editText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				title_editText.getLayoutParams().height = 120;
				
			}
		});
		title_editText.setFocusable(true);
		title_editText.setFocusableInTouchMode(true);
		title_editText.requestFocus();
		
		
		//spinner
		recycleSpinner=(Spinner)findViewById(R.id.recycle_spinner);
		String[] arr = { "不重复", "每年重复"};
		// ArrayAdapter
		ArrayAdapter<String> recyAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_list_item_multiple_choice, arr);
		
		recycleSpinner.setAdapter(recyAdapter);
		
		
		mDateDisplay=(TextView)findViewById(R.id.dateDisplay);
        
        mDateDisplay.setOnClickListener(new View.OnClickListener()
        {            
            public void onClick(View v)
            {
                showDialog(DATE_DIALOG_ID);
            }
        });
        
        updateDisplay();
		
		//show picture
		gallery = (Gallery) findViewById(R.id.gallery);
		// 获取显示图片的ImageView对象
		imageView = (ImageView) findViewById(R.id.imageView);
		// 创建一个BaseAdapter对象，该对象负责提供Gallery所显示的列表项
		BaseAdapter adapter = new BaseAdapter()
		{
			@Override
			public int getCount()
			{
				return imageIds.length;
			}
			
			@Override
			public Object getItem(int position)
			{
				
				return position;
			}

			@Override
			public long getItemId(int position)
			{
				return position;
			}

			// 该方法的返回的View就是代表了每个列表项
			@Override
			public View getView(int _position, View convertView, ViewGroup parent)
			{
				ImageView imageView = new ImageView(New_Activity.this);
				imageView.setImageResource(imageIds[_position]);
				// 设置ImageView的缩放类型
				imageView.setScaleType(ImageView.ScaleType.FIT_XY);
				// 为imageView设置布局参数
				imageView.setLayoutParams(new Gallery.LayoutParams(300, 370));
				TypedArray typedArray = obtainStyledAttributes(
						R.styleable.Gallery);
				imageView.setBackgroundResource(typedArray.getResourceId(
						R.styleable.Gallery_android_galleryItemBackground, 0));
				
				return imageView;
			}
		};
		gallery.setAdapter(adapter);
		gallery.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id)
			{
				//imageView.setImageResource(imageIds[position]);
				F1.setBackgroundResource(imageIds[position]);
				image_position=position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{}
		});
		

		//Aniamtion color
		color_TextView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
			new  AnimatedColorPickerDialog.Builder(New_Activity.this)
				.setTitle(" 选择一种颜色❤❤")
				.setColors(styleColors)
				.setOnColorClickListener(new AnimatedColorPickerDialog.ColorClickListener() {
			@Override
			public void onColorClick(int color) { 
				color_TextView.setBackgroundColor(color);
				choose_color=color;
	
			}	
			}).create().show();}
		});
		
	
	
	} 
	
	 
	//hide keyboard	
	public boolean dispatchTouchEvent(MotionEvent ev) {  
	    if (ev.getAction() == MotionEvent.ACTION_DOWN) {  
	        View v = getCurrentFocus();  
	        if (isShouldHideInput(v, ev)) {  
	  
	            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);  
	            if (imm != null) {  
	            	title_editText.getLayoutParams().height = 100;
	                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);  
	            }  
	        }  
	        return super.dispatchTouchEvent(ev);  
	    }  
	    // 必不可少，否则所有的组件都不会有TouchEvent了  
	    if (getWindow().superDispatchTouchEvent(ev)) {  
	        return true;  
	    }  
	    return onTouchEvent(ev);  
	}
	
	public  boolean isShouldHideInput(View v, MotionEvent event) {  
	    if (v != null && (v instanceof EditText)) {  
	        int[] leftTop = { 0, 0 };  
	        //获取输入框当前的location位置  
	        v.getLocationInWindow(leftTop);  
	        int left = leftTop[0];  
	        int top = leftTop[1];  
	        int bottom = top + v.getHeight();  
	        int right = left + v.getWidth();  
	        if (event.getX() > left && event.getX() < right  
	                && event.getY() > top && event.getY() < bottom) {  
	            // 点击的是输入框区域，保留点击EditText的事件  
	            return false;  
	        } else {  
	            return true;  
	        }  
	    }  
	    return false;  
	} 
	
	
	
	//show date
	 private void updateDisplay()
	    {
	        mDateDisplay.setText(
	                new StringBuilder()
	                    .append(mYear).append("-")
	                    .append(mMonth+1).append("-")
	                    .append(mDay).append(" "));
	    }
	    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() 
	    {                
	        public void onDateSet(DatePicker view, int year,int monthOfYear, int dayOfMonth)
	        {                    
	            mYear = year;                    
	            mMonth = monthOfYear;                    
	            mDay = dayOfMonth;                    
	            updateDisplay();    
	                 
	        }            
	    };
	    
	    protected Dialog onCreateDialog(int id) 
	    {    
	        switch (id)
	        {    
	            case DATE_DIALOG_ID:       
	                return new DatePickerDialog(this,mDateSetListener, mYear, mMonth, mDay);
	        }    
	        return null;
	    }
	    
	    @Override
	    protected void onStart() {
	    	
	    	super.onStart();
	    //	mTitle=title_editText.getText().toString().trim();
	    
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
		@Override
		public void finish() {
			// TODO Auto-generated method stub
			super.finish();
		}
}

