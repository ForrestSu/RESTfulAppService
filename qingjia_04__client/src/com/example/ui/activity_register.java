package com.example.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.computer.entity.Response;
import com.example.qingjia_04.MainActivity;
import com.example.qingjia_04.R;
import com.example.util.Client;
import com.example.util.HDUrl;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.media.JetPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class activity_register extends Activity {

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Toast.makeText(getApplicationContext(), msg.obj.toString(), 1)
					.show();
			if (msg.what == 1) {
				activity_register.this.finish();
			}
		};
	};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		final EditText nameEditText = (EditText) findViewById(R.id.user_name);
		final EditText passwordEditText = (EditText) findViewById(R.id.user_password);
		// comfirmpasswordEditText = (EditText)
		// findViewById(R.id.user_comfirmpassword);
		final EditText telEditText = (EditText) findViewById(R.id.user_telnumber);
		final EditText idnumEditText = (EditText) findViewById(R.id.user_id);
		// sex_man_Button = (RadioButton) findViewById(R.id.sex_man);
		final RadioButton sex_famle_Button = (RadioButton) findViewById(R.id.sex_famle);
		// studentbButton = (RadioButton) findViewById(R.id.sf_student);
		final RadioButton teacherbButton = (RadioButton) findViewById(R.id.sf_teacher);

		Button confirmbButton = (Button) findViewById(R.id.btn_confirm);
		Button cancleButton = (Button) findViewById(R.id.btn_cancle);

		confirmbButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String userNameString = nameEditText.getText().toString()
						.trim();
				String passwordString = passwordEditText.getText().toString()
						.trim();
				String telnumbersString = telEditText.getText().toString()
						.trim();
				String idnumberString = idnumEditText.getText().toString()
						.trim();
				String type ="0";
				if (teacherbButton.isChecked()) {
					type = "1";
				}
				String sexString = "男";
				if (sex_famle_Button.isChecked()) {
					sexString = "女";
				}

				if (userNameString.isEmpty()) {
					Toast.makeText(getApplicationContext(), "用户名不能为空",Toast.LENGTH_LONG );
					return;
				}
				
				final Map<String, String> params = new HashMap<String, String>();
				params.put("name", userNameString);
				params.put("password", passwordString);
				params.put("userType", type);
				params.put("stunumber", idnumberString);
				params.put("tel", telnumbersString);
				params.put("sex", sexString);
				params.put("reserve", "");
				Log.i("register", userNameString + passwordString);

				Thread myThread = new Thread() {

					@Override
					public void run() {
						// TODO Auto-generated method stub

						String result = "";
						try {
							result = Client.sendPost(HDUrl.BASE + HDUrl.USER
									+ "register", params);

						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						Log.i("register", result);

						Response respon = JSONObject.parseObject(result,
								Response.class);
						Message msg = new Message();
						if (respon.getOperateResult() == false) {
							msg.what = 0;
							msg.obj = respon.getDescription();
							handler.sendMessage(msg);
							return;
						}
						msg.what = 1;
						msg.obj = respon.getDescription();
						handler.sendMessage(msg);
					}

				};

				myThread.start();

			}
		}

		);

		cancleButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			 activity_register.this.finish();
			}
		});

	}

}