package com.zhy.demo_zhy_08_viewpageranim;

import android.os.Bundle;

import android.os.Handler;
import android.provider.CalendarContract.Instances;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;
//����ҳ���������뵭������ʱ2����ת��������


public class FristActivity extends Activity {

 public	static  FristActivity instance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_frist);
		instance = this;
       new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent intent =new Intent(FristActivity.this,MainActivity.class);
				startActivity(intent);
				 //��Ļ�������뵭��Ч���л�������anim�ļ����д�����enteralpha�����붯������exitalpha����������������������ע�⣺����xml�ļ��������д�д��ĸ��
		        //����붨������Ч��ֻ��Ҫ�ı�enteralpha��exitalpha�����ļ�
		        overridePendingTransition(R.anim.enteralph,R.anim.outalph);
			}
		}, 1000);
	}
	
}
