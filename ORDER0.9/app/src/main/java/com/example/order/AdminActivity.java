package com.example.order;

import android.app.Activity;
import android.content.Intent;
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

public class AdminActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);
		
		
		
		Button button = (Button)findViewById(R.id.button1);
		
		 button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				
				 Intent intent  = new Intent(AdminActivity.this,PersonnelManagementActivity.class);
				 startActivity(intent);
								
				}
		});
		 
		 

			Button button2 = (Button)findViewById(R.id.button2);
			
			 button2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					
					 Intent intent  = new Intent(AdminActivity.this,TableManagementActivity .class);
					 startActivity(intent);
									
					}
			});
			 
			 
				Button button3 = (Button)findViewById(R.id.button3);
				
				 button3.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO 自动生成的方法存根
						
						 Intent intent  = new Intent(AdminActivity.this,RecipeActivity .class);
						 startActivity(intent);
										
						}
				});
				 
				 Button button4 = (Button)findViewById(R.id.button4);
					
				 button4.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO 自动生成的方法存根
						 System.out.println(v.toString());
						 Intent intent4  = new Intent(AdminActivity.this,CookActivity .class);
						 startActivity(intent4);
										
						}
				});
				 
				 Button button5 = (Button)findViewById(R.id.button5);
					
				 button5.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO 自动生成的方法存根
						
						 Intent intent  = new Intent(AdminActivity.this,SuggestionActivity .class);
						 startActivity(intent);
										
						}
				});
				 
				 
				 Button button6 = (Button)findViewById(R.id.button6);
					
				 button6.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO 自动生成的方法存根
						
						 Intent intent  = new Intent(AdminActivity.this,CashierActivity .class);
						 startActivity(intent);
										
						}
				});
				 
				 
				 Button button7 = (Button)findViewById(R.id.button7);
					
				 button7.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO 自动生成的方法存根
						
						 Intent intent  = new Intent(AdminActivity.this,AllPayActivity .class);
						 startActivity(intent);
										
						}
				});
				 
				 Button button8 = (Button)findViewById(R.id.button8);
					
				 button8.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO 自动生成的方法存根
						
						 Intent intent  = new Intent(AdminActivity.this,All_OrderActivity .class);
						 startActivity(intent);
										
						}
				});
				 
				 Button button9 = (Button)findViewById(R.id.button9);
					
				 button9.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO 自动生成的方法存根
						
						 Intent intent  = new Intent(AdminActivity.this,SeatActivity .class);
						 startActivity(intent);
										
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
