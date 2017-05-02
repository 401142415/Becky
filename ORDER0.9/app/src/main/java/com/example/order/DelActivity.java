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
import android.widget.TextView;

public class DelActivity extends Activity {
	private DBUtil dbUtil;  
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_del);
		Intent intent = getIntent();
		
		dbUtil = new DBUtil();
		final String R_Name = intent.getStringExtra("R_Name");
		final String R_Price = intent.getStringExtra("R_Price");
		
		TextView textview2 = (TextView)findViewById(R.id.textView2);
		textview2.setText(R_Name);
		TextView textview1 = (TextView)findViewById(R.id.textView1);
		textview1.setText(R_Price);
		
		Button button = (Button)findViewById(R.id.button1);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				
				new Thread(){  
					   @Override  
					   public void run()  
					   {
						//   List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
						    dbUtil.delete_Recipe(R_Name);
						    System.out.println("delete_Recipe----->>"+R_Name );	
						    finish();
						    Intent intent1  = new Intent(DelActivity.this,RecipeActivity.class);
							startActivity(intent1);
					   }  	
					}.start(); 
				
			}			
		});
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO 自动生成的方法存根
	
		finish();
		Intent intent1  = new Intent(DelActivity.this,RecipeActivity.class);
		startActivity(intent1);
		
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.del, menu);
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
