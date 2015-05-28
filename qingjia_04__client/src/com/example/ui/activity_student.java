package com.example.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.qingjia_04.R;

public class activity_student extends Activity
{
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student);
		
		
		Button qingjiaButton=(Button)findViewById(R.id.btu_qingjia);
		Button qjlistbButton=(Button)findViewById(R.id.qingjia_list);
		Button stu_reser_password_Button=(Button)findViewById(R.id.stu_retpassword);
		
		
		qingjiaButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent  intent = new Intent();
				intent.setClass(activity_student.this, activity_qingjia.class);
				startActivity(intent);
				
			}
		});
		
		
		qjlistbButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent();
				intent.setClass(activity_student.this, activity_qingjias.class);
				startActivity(intent);
				
			}
		});
		
		stu_reser_password_Button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent();
				intent.setClass(activity_student.this, activity_resetpassword.class);
			}
		});
		
		
}

	
}
