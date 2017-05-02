package com.example.order;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class WelcomeActivity extends Activity {

	private DBUtil dbUtil;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		Intent intent = getIntent();
		
		final String User_Name = intent.getStringExtra("User_Name");
		
		dbUtil = new DBUtil();
		
		ImageButton button = (ImageButton)findViewById(R.id.imageButton1);
		
		 button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				
				
//				dbUtil.Update_Logged(User_Name,tagString,Note);
				
				new Thread(){  
					   @Override  
					   public void run()  
					   {
						   							
						   dbUtil.Update_Logged(User_Name,"2","无");  
						   System.out.println("welcome Update_Logged----->>"+User_Name);
					//	   Toast.makeText(BookingActivity.this,User_Name +"  "+ tagString +"  "+ Note, 1).show();
						   Intent intent1  = new Intent(WelcomeActivity.this,OrderActivity.class);
							 
						   intent1.putExtra("User_Name", User_Name);
						   startActivity(intent1);
						   finish();
					   }  
					}.start(); 
					
				
				}
		});
		
	
		
	}
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			moveTaskToBack(false);
			return true;
		}
		
		if (keyCode == KeyEvent.KEYCODE_HOME) {
			moveTaskToBack(false);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
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
