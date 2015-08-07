package com.zhy.demo_zhy_08_viewpageranim;

import com.zhy.demo_zhy_08_viewpageranim.RippleLayout.RippleFinishListener;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ImportMenuView extends RelativeLayout implements RippleFinishListener,View.OnClickListener{
	
	Context context=null;
	
	RippleLayout rl_close,rl_edit,rl_delete,rl_new;
	View view_shadow;
	
	public ImportMenuView(Context context) {
		super(context);
	}
	public ImportMenuView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context,attrs);
	}
	public ImportMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context,attrs);
	}
	
	public void init(Context context, AttributeSet attrs){
		LayoutInflater.from(context).inflate(R.layout.widget_import_menu, this, true);
		
		rl_close = (RippleLayout)findViewById(R.id.rl_close);
		rl_edit = (RippleLayout)findViewById(R.id.rl_edit);
		rl_delete = (RippleLayout)findViewById(R.id.rl_delete);
		rl_new = (RippleLayout)findViewById(R.id.rl_new);
		
		rl_close.setRippleFinishListener(this);
		rl_edit.setRippleFinishListener(this);
		rl_delete.setRippleFinishListener(this);
		rl_new.setRippleFinishListener(this);
		
		
		
		view_shadow = (View)findViewById(R.id.v_shadow);
		view_shadow.setOnClickListener(this);
		
		setVisibility(View.GONE);
		
		this.context = context;
	}
	
	public void animation(Context context){
		
		Animation first_ball_anim = AnimationUtils.loadAnimation(context, R.anim.collistion_import_qrcode_button);
		rl_edit.startAnimation(first_ball_anim);
		
		Animation rl_pc_anim = AnimationUtils.loadAnimation(context, R.anim.collistion_import_pc_button);
		rl_delete.startAnimation(rl_pc_anim);
		
		Animation rl_sdcard_anim = AnimationUtils.loadAnimation(context, R.anim.collistion_import_sdcard_button);
		rl_new.startAnimation(rl_sdcard_anim);	
		
		Animation view_fade_in_anim = AnimationUtils.loadAnimation(context, R.anim.fade_in);
		view_shadow.startAnimation(view_fade_in_anim);
	}
	
	public void animationOut(){
		Animation first_ball_anim = AnimationUtils.loadAnimation(context, R.anim.collistion_import_qrcorde_button_out);
		rl_edit.startAnimation(first_ball_anim);
		
		Animation rl_pc_anim = AnimationUtils.loadAnimation(context, R.anim.collistion_import_pc_button_out);
		rl_delete.startAnimation(rl_pc_anim);
		
		Animation rl_sdcard_anim = AnimationUtils.loadAnimation(context, R.anim.collistion_import_sdcard_button_out);
		rl_new.startAnimation(rl_sdcard_anim);
		
		first_ball_anim.setAnimationListener(new AnimationListener() {		
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				Animation view_fade_in_anim = AnimationUtils.loadAnimation(context, R.anim.fade_out);			
				rl_closeUnVisiableAnimation();
				view_shadow.startAnimation(view_fade_in_anim);
			}
		});
	}
	
	public void rl_closeVisiableAnimation(){		
		ObjectAnimator object = ObjectAnimator.ofFloat(rl_close, "rotation", 0, -45);
		object.setDuration(250);
		object.setRepeatCount(0);
		object.start();
	}
	
	public void rl_closeUnVisiableAnimation(){
		
		ObjectAnimator object = ObjectAnimator.ofFloat(rl_close, "rotation", -45, 0);
		object.setDuration(200);
		object.setRepeatCount(0);
		object.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {				
				Show_Activity.ripple.setVisibility(View.VISIBLE);
				setVisibility(View.GONE);
				setEnabled(false);
				setFocusable(false);
				Show_Activity.ripple.bringToFront();
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {		
			}
		});
		object.start();
	}
	
	
	@Override
	public void rippleFinish(int id) {		
		switch(id){
		case R.id.rl_close:	
			animationOut();
			break;
		case R.id.rl_edit:
			animationOut();
			break;
		case R.id.rl_delete:
			animationOut();
			break;
		case R.id.rl_new:
			animationOut();
			break;
		}
	}
	
	@Override
	public void onClick(View v) {
		animationOut();
		}
}
