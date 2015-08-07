package com.zhy.demo_zhy_08_viewpageranim;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class Edit_Activity   extends  Activity{

	private TextView mDateDisplay;
    static int mYear;
    static int mMonth;
    static int mDay;
    static int mPosition;
    static String mTitle;
    static EditText title_editText;
	static TextView color_TextView;
	static int choose_color;
	static int image_position;
	static  int flag=0;  //biao ji
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
    private int post_list;
    private int Flag_back;
     ItemBean it;
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
		post_list=bundle.getInt("pos_list");
		
		    it=MainActivity.mList.get(post_list-1);
		    mYear=it.getyear();
		    mMonth=it.getmonth();
		    mDay=it.getdays();
		    mTitle=it.getTitle();
		    mPosition=it.getPhoto();
		    
		    final Calendar c=Calendar.getInstance();
    		mYear=c.get(Calendar.YEAR);
    	    mMonth=c.get(Calendar.MONTH); 
    	    mDay=c.get(Calendar.DAY_OF_MONTH);
    	    mTitle=title_editText.getText().toString();
    	    
		color_TextView=(TextView)findViewById(R.id.color_textview);
		
		new_return=(ImageButton)findViewById(R.id.new_return);
		new_return.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Edit_Activity.this,MainActivity.class);
				startActivity(intent);
				
			}
		});

		new_save=(ImageButton)findViewById(R.id.new_save);
		new_save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				Intent intent=new Intent(Edit_Activity.this,Show_Activity.class);
				   it.setyear(mYear);
				   it.setmonth(mMonth);
				   it.setmonth(mDay);
				   
				   it.setColor(choose_color);
				   it.setPhoto(mPosition);
				   it.setTitle(mTitle);
           
			
		    	 // 新建生成一个单页viewgroup将它加到viewpager的适配器里
		    	 /*   ViewGroup singleViewGroup=new SingleView(getApplicationContext());
		    		LayoutInflater inflater=getLayoutInflater();
		            singleViewGroup=(ViewGroup) inflater.inflate(R.layout.single_view, null);
		            MainActivity.list_VG.add(singleViewGroup);*/
		        ////////////////   刷新布局 
		            MainActivity.listAdapter.notifyDataSetChanged();
		        ///////////////    
		        //    MainActivity.mViews.add(singleViewGroup);
		            
		        ////////    
		            MainActivity.pAdapter.notifyDataSetChanged();
		     
		           //将viewgroup和数据绑定   
		        //    MainActivity.map.put(singleViewGroup, it);
		            
		            intent.putExtra("pos_list",it.pos_in_list);
		            
					startActivity(intent);
					
					
					flag=2;
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
				ImageView imageView = new ImageView(Edit_Activity.this);
				imageView.setImageResource(imageIds[_position]);
				// 设置ImageView的缩放类型
				imageView.setScaleType(ImageView.ScaleType.FIT_XY);
				// 为imageView设置布局参数
				imageView.setLayoutParams(new Gallery.LayoutParams(300, 370));
				TypedArray typedArray = obtainStyledAttributes(
						R.styleable.Gallery);
				imageView.setBackgroundResource(typedArray.getResourceId(
						R.styleable.Gallery_android_galleryItemBackground, 0));
				
				mPosition=_position;
				
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
			
			new  AnimatedColorPickerDialog.Builder(Edit_Activity.this)
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
}
