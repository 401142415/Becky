package com.example.order;

import android.app.Activity;
import android.os.Bundle;
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

public class AddSuggestionActivity extends Activity {
	
	private DBUtil dbUtil;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_suggestion);
		
		
		dbUtil = new DBUtil();
		Button button = (Button)findViewById(R.id.button2);
		
		
		 button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				
//				dbUtil.Update_Logged(User_Name,tagString,Note);
				
				new Thread(){  
					   @Override  
					   public void run()  
					   {

							EditText editText = (EditText)findViewById(R.id.editText1);
							String S_suggestion = editText.getText().toString().trim();
							if (S_suggestion.isEmpty()){
								finish();
								   System.out.println("addsuggestionactivity ------->>empty");
							}
							
							dbUtil.insert_Suggestion(S_suggestion);
							
					//	   Toast.makeText(BookingActivity.this,User_Name +"  "+ tagString +"  "+ Note, 1).show();
						   finish();
					   }  
					}.start(); 
					
					Toast.makeText(AddSuggestionActivity.this,"感谢您对本店的支持", 2).show();
				
				}
		});
		 
		 
			Button button1 = (Button)findViewById(R.id.button1);
			
			
			 button1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					
//					dbUtil.Update_Logged(User_Name,tagString,Note);
				finish();
					
					}
			});
			 
			 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_suggestion, menu);
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
