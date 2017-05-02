package com.example.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddActivity extends Activity {

	 private WordWrapView wordWrapView;
	private DBUtil dbUtil;  
	private int mark = 1 ;
	private  List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		
		dbUtil = new DBUtil();
		final RadioGroup group = new RadioGroup(AddActivity.this);  
		wordWrapView = (WordWrapView) AddActivity.this.findViewById(R.id.view_wordwrap);
		new Thread(){  
			   @Override  
			   public void run()  
			   {
				   
				   
				   list = dbUtil.selectR_Type();
				   mark = 0;
				//   List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
				  		
//					    RadioGroup group = new RadioGroup(AddActivity.this);  
			   }  	
				}.start(); 	
			while (mark == 1) {
					//此处有等待上方线程结束作用
							
			}	
				
			 for(int i=0; i<list.size(); i++)  
			 {  
					 RadioButton tempButton = new RadioButton(AddActivity.this);  
					 tempButton.setTextColor(Color.BLACK);
					 tempButton.setPadding(80, 0, 0, 0);                 // 设置文字距离按钮四周的距离   
					 tempButton.setText(list.get(i).get("R_Type").toString().trim());  
					 tempButton.setId(i);
					 group.addView(tempButton);  
			} 
					    
				    
			  
		    wordWrapView.addView(group);
		
		Button button = (Button)findViewById(R.id.button1);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				
				new Thread(){  
					   @Override  
					   public void run()  
					   {
						   List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
						   
						   list = dbUtil.selectR_Type();
						//   List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
						   	EditText editText1 = (EditText)findViewById(R.id.editText1);
							String R_Name = editText1.getText().toString();
						   
							EditText editText2 = (EditText)findViewById(R.id.editText2);
							String R_Price = editText2.getText().toString();
							
							
							EditText editText3 =  (EditText)findViewById(R.id.editText3);
							String R_Type = editText3.getText().toString();
							
							if (R_Type.isEmpty()) {
								RadioButton tempButton =(RadioButton)findViewById(group.getCheckedRadioButtonId());
								R_Type = (String)tempButton.getText();
								if (R_Type.isEmpty()) {
									System.out.println(" addactivity------------>  :什么都没选中");
								}
								System.out.println(" addactivity------------>  :"+  tempButton.getText().toString() );
							}
							
							
							 Message message = Message.obtain();
							 
							
							if(TextUtils.isEmpty(R_Name) || TextUtils.isEmpty(R_Price))
							{
								message.arg1 = 1;
								  handler.sendMessage(message);
								  
							}else {
								dbUtil.insert_Recipe(R_Name, R_Price,R_Type);
							    System.out.println("insert_Recipe----->>"+R_Name );	
							    finish();
							    Intent intent1  = new Intent(AddActivity.this,RecipeActivity.class);
								startActivity(intent1);
							}
						    
					   }  	
					}.start(); 
				
			}		
			private Handler handler = new Handler() {

				@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					if (msg.arg1 == 1) {
						Toast.makeText(AddActivity.this, "请输入菜式名及其价格", 1).show();
					}
					
					
				}
				
			};	
		});
		
	}
	
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
		return true;
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO 自动生成的方法存根
	
		finish();
		Intent intent1  = new Intent(AddActivity.this,RecipeActivity.class);
		startActivity(intent1);
		
		return super.onKeyDown(keyCode, event);
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
