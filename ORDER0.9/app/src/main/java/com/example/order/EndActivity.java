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

public class EndActivity extends Activity {
	
	private String User_Name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_end);
		
		Intent intent = getIntent();
		User_Name = intent.getStringExtra("User_Name");
		

		Button button1 = (Button)findViewById(R.id.button1);
		
		
		 button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				
//				dbUtil.Update_Logged(User_Name,tagString,Note);
				 Intent intent  = new Intent(EndActivity.this,AddSuggestionActivity.class);
				 startActivity(intent);
				
				}
		});
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO 自动生成的方法存根
		if (keyCode == KeyEvent.KEYCODE_HOME) {
			moveTaskToBack(false);
			return true;
		}
		
		finish();
		OrderActivity.orderActivity1.finish();
		Intent intent1  = new Intent(EndActivity.this,AllTableActivity.class);
		intent1.putExtra("User_Name", User_Name);
		startActivity(intent1);
		
		return super.onKeyDown(keyCode, event);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.end, menu);
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
