package com.example.ui;

import com.example.qingjia_04.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class activity_teacher extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teacher);
		   
		Button shenhebButton= (Button)findViewById(R.id.tea_shenhe);
		Button teacher_resetpassword_Button=(Button)findViewById(R.id.tea_resetpassword);
		
		
		shenhebButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Intent intent=new Intent();
				 intent.setClass(activity_teacher.this, activity_teashenhe.class);
				 startActivity(intent);
			}
		});
		
	}
}
