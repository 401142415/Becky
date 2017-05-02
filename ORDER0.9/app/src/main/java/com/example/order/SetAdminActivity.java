package com.example.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class SetAdminActivity extends Activity {
	
	private DBUtil dbUtil; 
	private View view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setadmin);
		
		  view = this.findViewById(R.id.button1);
	        
	        view.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					EditText editText1 = (EditText)findViewById(R.id.editText1);
					final String P_Name = editText1.getText().toString();
					
					EditText editText2 = (EditText)findViewById(R.id.editText2);
					final String P_Password = editText2.getText().toString();
					
					new Thread(){  
						   @Override  
						   public void run()  
						   {  
						//	   List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
							   System.out.println("P_name-------->>"+ P_Name);	
							//   list = dbUtil.getUser("2");
							   dbUtil = new DBUtil(); 
							   dbUtil.insert_Personnel(P_Name, P_Password,"4");
					//		   System.out.println("list----->>"+list.get(1).toString());
					//		   System.out.println("list----->>"+list.get(1).get("User_Name"));
							   Intent intent  = new Intent(SetAdminActivity.this,AdminActivity.class);
								
								 intent.putExtra("P_Name", P_Name);
								 startActivity(intent);
								 finish();
					
							  
							}
						 }.start(); 
						
				}
				
				
			});
	        
	    
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin, menu);
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
