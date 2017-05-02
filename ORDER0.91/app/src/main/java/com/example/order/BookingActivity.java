package com.example.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BookingActivity extends Activity {
	private DBUtil dbUtil;  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_booking);
		Intent intent = getIntent();
		
		dbUtil = new DBUtil();
		
	//	String User_Name = intent.getStringExtra("User_Name");
		TextView textview1 = (TextView)findViewById(R.id.textView1);
		textview1.setText(intent.getStringExtra("User_Name").toString().trim());
	//	Toast.makeText(BookingActivity.this,intent.getStringExtra("User_Name") ,1).show();
//		final String User_Name = intent.getStringExtra("User_Name");
//		
//		RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
//		RadioButton radioButton =(RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
//		final String tagString = (String)radioButton.getTag();
//		
//		EditText editText = (EditText)findViewById(R.id.editText1);
//		final String Note = editText.getText().toString();
		
		Button button = (Button)findViewById(R.id.button1);
		
		 button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				
				
//				dbUtil.Update_Logged(User_Name,tagString,Note);
				
				new Thread(){  
					   @Override  
					   public void run()  
					   {
						   TextView textview1 = (TextView)findViewById(R.id.textView1);
							String User_Name = textview1.getText().toString().trim();
							
							RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
							RadioButton radioButton =(RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
							String tagString = (String)radioButton.getTag();
							
							EditText editText = (EditText)findViewById(R.id.editText1);
							String Note = editText.getText().toString().trim();
							if (Note.isEmpty() ){
								Note = "无";
								System.out.println("  bookingactivity -------->>"+ User_Name+Note);
							}
							System.out.println("  bookingactivity -------->>"+   User_Name+ Note);
						   dbUtil.Update_Logged(User_Name,tagString,Note);   
					//	   Toast.makeText(BookingActivity.this,User_Name +"  "+ tagString +"  "+ Note, 1).show();
						   finish();
					   }  
					}.start(); 
					
				
				}
		});
		
	
		
		
	}
	//重写物理返回键
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO 自动生成的方法存根
	//	Toast.makeText(BookingActivity.this,"准备finish" ,1).show();
		finish();
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.booking, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
