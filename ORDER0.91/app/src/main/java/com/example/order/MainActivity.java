package com.example.order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.location.GpsStatus.Listener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.style.ClickableSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	private View view;
	private View view2;
	private DBUtil dbUtil;  
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        dbUtil = new DBUtil();  
        view = this.findViewById(R.id.button1);
        view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				EditText editText1 = (EditText)findViewById(R.id.editText1);
				final String User_Name = editText1.getText().toString();
				
				EditText editText2 = (EditText)findViewById(R.id.editText2);
				final String User_Password = editText2.getText().toString();
				
				new Thread(){  
					   @Override  
					   public void run()  
					   {  
						   List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
						   
						//   list = dbUtil.getUser("2");
						   dbUtil = new DBUtil(); 
						   list = dbUtil.getUser(User_Name);
				//		   System.out.println("list----->>"+list.get(1).toString());
				//		   System.out.println("list----->>"+list.get(1).get("User_Name"));
				
						   Message message = Message.obtain();
						   
						   if (list.size() == 0 ) {
							   message.arg1 = 1;
								  handler.sendMessage(message);
								
						}else if (!User_Password.equals(list.get(0).get("User_Password").trim())) {
							message.arg1 = 2;
							  handler.sendMessage(message);
						}else {
							
							Intent intent  = new Intent(MainActivity.this,WelcomeActivity.class);
							intent.putExtra("User_Name", list.get(0).get("User_Name"));
							startActivity(intent);
							finish();
						}
						   	  }  
					}.start(); 
					
			}
			
			private Handler handler = new Handler() {

				@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					if (msg.arg1 == 1) {
						Toast.makeText(MainActivity.this, "用户不存在，请重新输入", 1).show();
					}else {
						Toast.makeText(MainActivity.this, "用户不存在或密码错误，请重新输入", 1).show();
					}
					
					
				}
				
			};
		});
        
        
        view2 = this.findViewById(R.id.button2);
        
        view2.setOnClickListener(new OnClickListener() {
			
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
						   List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
						   System.out.println("P_name-------->>"+ P_Name);	
						//   list = dbUtil.getUser("2");
						   dbUtil = new DBUtil(); 
						   list = dbUtil.selectPersonnel(P_Name);
				//		   System.out.println("list----->>"+list.get(1).toString());
				//		   System.out.println("list----->>"+list.get(1).get("User_Name"));
				
						   Message message = Message.obtain();
						   System.out.println("list.size-------->>"+ list.size());
						   if (list.size() == 0 ) {
							   message.arg1 = 1;
								  handler.sendMessage(message);
								
						}else if (!P_Password.equals(list.get(0).get("P_Password").trim())) {
							message.arg1 = 2;
							  handler.sendMessage(message);
						}else {
							
							switch (list.get(0).get("P_Function")) {
							case "0":
								 System.out.println("准备跳转服务生");
								 Intent intent  = new Intent(MainActivity.this,SeatActivity.class);
								
								 intent.putExtra("User_Name", list.get(0).get("User_Name"));
								 startActivity(intent);
								 finish();
								break;
							case "1":
								 System.out.println("准备菜谱");
								 Intent intent1  = new Intent(MainActivity.this,RecipeActivity.class);
								 
								 intent1.putExtra("User_Name", list.get(0).get("User_Name"));
								 startActivity(intent1);
								 finish();
								break;
							case "2":
								 System.out.println("准备厨师");
								 Intent intent4  = new Intent(MainActivity.this,CookActivity.class);
								 intent4.putExtra("User_Name", list.get(0).get("User_Name"));
								 startActivity(intent4);
								 finish();
								break;
							case "3":
								 System.out.println("准备收银");
								 Intent intent3  = new Intent(MainActivity.this,CashierActivity.class);
								 intent3.putExtra("User_Name", list.get(0).get("User_Name"));
								 startActivity(intent3);
								 finish();
								break;	
								
//							case "4":
//								 System.out.println("欢迎界面");
//								 Intent intent2  = new Intent(MainActivity.this,WelcomeActivity.class);
//								 System.out.println("list----->>"+list.get(1).get("User_Name"));
//								 intent2.putExtra("User_Name", list.get(1).get("User_Name"));
//								 startActivity(intent2);
//								 finish();
//								break;
							case "4":
								 
								 Intent intent2  = new Intent(MainActivity.this,AdminActivity.class);
								
								 startActivity(intent2);
								 finish();
								break;
								
							default:
								 System.out.println("暂不跳转");
								break;
							}
						}
						   	  }  
					}.start(); 
					
			}
			
			private Handler handler = new Handler() {

				@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					if (msg.arg1 == 1) {
						Toast.makeText(MainActivity.this, "/用户不存在/或密码错误，请重新输入", 1).show();
					}else {
						Toast.makeText(MainActivity.this, "用户不存在或/密码/错误，请重新输入", 1).show();
					}
					
					
				}
				
			};
		});
        
    }
    
   
}
