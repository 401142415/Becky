package com.example.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PersonnelActivity extends Activity {

		private DBUtil dbUtil;
		private String P_Name;
		private String P_Password;
		private String P_Function;
		private View view2;
		private View view;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personnel);
		Intent intent = getIntent();
		P_Name = intent.getStringExtra("P_Name");
		
		dbUtil = new DBUtil();
		
		new Thread(){  
			   @Override  
			   public void run()  
			   {
				   dbUtil = new DBUtil();
				   Message message = Message.obtain();
				   List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
				   list = dbUtil.selectPersonnel(P_Name);
				   System.out.println("PersonnelActivity------list----->>"+list.toString());
				   P_Name = list.get(0).get("P_Name").toString().trim();
				   P_Password = list.get(0).get("P_Password").toString().trim();
				   P_Function =  list.get(0).get("P_Function").toString();
				   
				   message.arg1 = 1;
					  handler.sendMessage(message);
					  
			   		}  
			   }.start(); 
			   
			   
			   
			   //主线程休眠一秒。等待子线程结束返回数值，否则以下赋值会由于数据为空而出错
			   //以下涉及UI更新。无法放入子线程
//			   SystemClock.sleep(1000); 
			   //操作：对于textv等赋值输入
			   
			
			
			
	        view2 = this.findViewById(R.id.button1);
	        
	        view2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					TextView textview1 = (TextView)findViewById(R.id.textView1);
					final String P_Name1 = textview1.getText().toString();
					
					EditText editText = (EditText)findViewById(R.id.editText2);
					final String P_Password = editText.getText().toString();
					
					RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
					RadioButton radioButton =(RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
					final String tagString = (String)radioButton.getTag();
					
					if( TextUtils.isEmpty(P_Password))
					{
						Toast.makeText(PersonnelActivity.this, "请输入密码", 1).show();	
					}else {
						
					new Thread(){  
						   @Override  
						   public void run()  
						   {
							   dbUtil.Update_Personnel(P_Name1, P_Password, tagString);
							   System.out.println("PersonnelActivity----------->>"+P_Name1+P_Password+tagString);
							   finish();
							   Intent intent  = new Intent(PersonnelActivity.this,PersonnelManagementActivity.class);
							   startActivity(intent);
							   
						   }  
						}.start(); 
						
				}
				}
				
				
			});
			
	        

	        final Dialog alertDialog = new AlertDialog.Builder(this)
			.setMessage( "删除该信息？")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() { 
	                   
	                  @Override 
	                  public void onClick(DialogInterface dialog, int which) { 
	                      // TODO Auto-generated method stub  
	               	   new Thread(){  
	   					   @Override  
	   					   public void run()  
	   					   {
	   						dbUtil.delete_Personnel(P_Name);
	   					   }  
	   					}.start(); 
	              // 	   Toast.makeText(CookActivity.this, "", 1).show();
	   					finish();
						Intent intent  = new Intent(PersonnelActivity.this,PersonnelManagementActivity.class);
						startActivity(intent);
	                  } 
	              }).
	              setNegativeButton("取消", new DialogInterface.OnClickListener() {
	           	     
	           	     @Override
	           	     public void onClick(DialogInterface dialog, int which) {
	           	      // TODO Auto-generated method stub
	           	    	 
	           	  //  	 Toast.makeText(CookActivity.this, "结账未完成", 1).show();
	           	     }
	           	    })
	           	    .create();
			
	        
	        view = this.findViewById(R.id.button2);
	        
	        view.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
				 alertDialog.show();
				
				}
				
				
			});
	        
	        
		
	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if (msg.arg1 == 1){
			  TextView textview1 = (TextView)findViewById(R.id.textView1);
			   textview1.setText(P_Name);
			   
			   EditText editText = (EditText)findViewById(R.id.editText2);
			   editText.setText(P_Password);
			   
			   System.out.println("PersonnelActivity----------->>"+P_Name +P_Password+ P_Function);
			   RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
			   
			   if (P_Function.equals("0")) {
				   radioGroup.check(R.id.radio0);
			}else if (P_Function.equals("1")) {
				 radioGroup.check(R.id.radio1);
			}else if (P_Function.equals("2")) {
				 radioGroup.check(R.id.radio2);
			}else if (P_Function.equals("3")) {
				 radioGroup.check(R.id.radio3);
			}else if (P_Function.equals("4")) {
				 radioGroup.check(R.id.radio4);
			}
			
			}
		}
		
	};
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO 自动生成的方法存根
	
		finish();
		Intent intent  = new Intent(PersonnelActivity.this,PersonnelManagementActivity.class);
		startActivity(intent);
		
		return super.onKeyDown(keyCode, event);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.personnel, menu);
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
