package com.example.ui;

import com.example.qingjia_04.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class activity_qingjia extends Activity {
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avtivity_qingjia);
		
		final EditText sq_reasonEditText=(EditText)findViewById(R.id.qj_reason);
		
/*		Spinner tea_ListView=(Spinner)findViewById(R.id.qj_teacher);*/
		
		final EditText sq_sdateText=(EditText)findViewById(R.id.sq_sdate);
		final EditText sq_edateText=(EditText)findViewById(R.id.sq_edate);
		
		Button qj_submitbButton=(Button) findViewById(R.id.qj_sumbit);
		Button qj_canclebButton=(Button) findViewById(R.id.qj_cancel);
		
		
		qj_submitbButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String qj_reasonString=sq_reasonEditText.getText().toString().trim();
				
				String qj_sdateString=sq_sdateText.getText().toString().trim();
				String qj_edateString=sq_edateText.getText().toString().trim();
				
				
				
				
			}
		} );
	}
}