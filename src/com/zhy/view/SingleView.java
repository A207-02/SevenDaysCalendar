package com.zhy.view;

import com.zhy.demo_zhy_08_viewpageranim.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SingleView extends ViewGroup{
    
	public  static  TextView show_year;
	public  static  TextView show_mon;
	public  static  TextView  show_day;
	public  static  TextView   show_title;
	public  static  TextView   show_numbers;
	
	
	
	
	public SingleView(Context context) {
		super(context);
		this.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		
	}
   
	
	//int defstyle �������ݣ�--->�������list����� ,�õ�����,���õ��ؼ�����
	public SingleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		show_year=(TextView) findViewById(R.id.show_year);
		show_mon=(TextView) findViewById(R.id.show_month);
		show_day=(TextView) findViewById(R.id.show_day);
		show_title=(TextView) findViewById(R.id.show_title);
		show_numbers=(TextView) findViewById(R.id.show_numbers);
		
		/*ItemBean it=MainActivity.mList.get(defStyle);
		show_title.setText(it.getTitle().trim());
		show_numbers.setText(String.valueOf(it.getDate()));
		show_year.setText("2");
		show_mon.setText("3");
		show_day.setText("4");
		show_title.setText("fffff");
		show_numbers.setText("mm");*/
		
	}

	public SingleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		
	}

	@Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
		
		
		/*�����Ԫ�صĸ���
		l �� t �ǿؼ����Ե���ϱ�Ե����ڸ���ؼ����Ե���ϱ�Ե�ľ���
		r �� b�ǿռ��ұ�Ե���±�Ե����ڸ���ؼ����Ե���ϱ�Ե�ľ���*/
		int childLeft = 0;
		//�����Ԫ�صĸ���
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			final View child = getChildAt(i);
			if (child.getVisibility() != View.GONE) {
				final int childWidth = child.getMeasuredWidth();
				child.layout(childLeft, 0, childLeft + childWidth,
						child.getMeasuredHeight());
				childLeft += childWidth;
			}
		}
     }				
     
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		//ȡ�ÿ��ֵ
		final int width = MeasureSpec.getSize(widthMeasureSpec);
		final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		if (widthMode != MeasureSpec.EXACTLY) {
			throw new IllegalStateException("error mode.");
		}
		final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		if (heightMode != MeasureSpec.EXACTLY) {
			throw new IllegalStateException("error mode.");
		}
		//���߿���Ϣ���ݸ���Ԫ��
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
		}
		
		
	}
	
	
}
