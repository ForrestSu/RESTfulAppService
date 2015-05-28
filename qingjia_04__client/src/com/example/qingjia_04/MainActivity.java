package com.example.qingjia_04;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.tsz.afinal.FinalDb;

import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSONObject;
import com.computer.entity.Response;
import com.computer.entity.User;

import com.example.ui.activity_register;
import com.example.ui.activity_student;
import com.example.ui.activity_teacher;
import com.example.util.AnalysisGson;
import com.example.util.Client;
import com.example.util.HDUrl;
import com.example.util.Mysharepreference;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Type;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends Activity {

	
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Toast.makeText(getApplicationContext(), msg.obj.toString(), 1)
			.show();
			if(msg.what==0){
			  Intent i = new Intent(MainActivity.this,
					activity_student.class);
			  Toast.makeText(getApplicationContext(), msg.obj.toString(), 1)
				.show();
			  startActivity(i);
			} else if(msg.what==1){
				Intent i = new Intent(MainActivity.this,
						activity_teacher.class);
				Toast.makeText(getApplicationContext(), msg.obj.toString(), 1)
				.show();
				  startActivity(i);
			}
		};
	};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Button loginbotton = (Button) findViewById(R.id.loginButton);
		Button registerbotton = (Button) findViewById(R.id.registerbutton);

		final EditText userEditText = (EditText) findViewById(R.id.user);
		final EditText passordEditText = (EditText) findViewById(R.id.password);
		RadioButton dl_studentButton=(RadioButton)findViewById(R.id.dl_student);
		final RadioButton dl_teacherButton=(RadioButton)findViewById(R.id.dl_teacher);

		registerbotton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, activity_register.class);

				startActivity(intent);
			}
		});

		loginbotton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String userName = userEditText.getText().toString().trim();
				String passwd = passordEditText.getText().toString().trim();
				String type = "0";
				if (dl_teacherButton.isChecked()) {
					type="1";
					
				}
				
				final Map<String, String> params = new HashMap<String, String>();
				params.put("name", userName);
				params.put("password", passwd);
				params.put("userType", type);

				Thread mythread = new Thread() {
					public void run() {

						String result="";
						try {
							result = Client.sendPost(HDUrl.BASE + HDUrl.USER
									+ "login", params);
						} catch (ClientProtocolException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						Log.i("TAGqj", result);
						Response resp = JSONObject.parseObject(result,Response.class);
						Message msg = new Message();
						if (resp.getOperateResult() == false) {
							msg.what=0;//type返回值
							msg.obj = resp.getDescription();
							handler.sendMessage(msg);
							return;
						}
						User one = JSONObject.toJavaObject(
								(JSONObject) resp.getObject(), User.class);
						msg.what = one.getUserType();
						msg.obj=one.toString();
						handler.sendMessage(msg);
						// // 持久化one 息到手机内存
					FinalDb db = FinalDb.create(getApplicationContext());
					 db.save(one);
//						Mysharepreference s = new Mysharepreference(
//								getApplicationContext());
//						s.setMessage("uid", one.getId() + "");
//						s.getMessage("uid");
						// 成功跳转
					}
				};
				mythread.start();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
