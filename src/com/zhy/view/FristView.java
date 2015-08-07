package com.zhy.view;

import android.content.Context;

import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.MeasureSpec;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class FristView extends  ViewGroup{

	//�����ֵ��ӿؼ� 
	private  ViewGroup framelayout;
	
	private  ImageButton  img_but;
    private  ViewGroup  lineanlay;
    
    //   private ViewGroup linear_titleGroup;  //  ��ű�������Բ���
    public static  ListView  listview;
    
    public static TextView tx_days,tx_words;    
    
    private int mScreenWidth=300;
 
    private boolean once;
 
	
	
	public FristView(Context context) {
		super(context);
		this.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
	}

	public FristView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
	}

	public FristView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
	}

	//������view�Ŀ�͸�  �����Լ��Ŀ�͸�
	
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
	
	
	
	
	//�����ӿؼ����ŷ�λ��
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
       
	
}
