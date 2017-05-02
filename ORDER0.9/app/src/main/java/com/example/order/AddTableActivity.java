package com.example.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddTableActivity extends Activity {

	
	private View view;
	private DBUtil dbUtil; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_table);
		
		
		
		dbUtil = new DBUtil(); 
		view = this.findViewById(R.id.button1);
		
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				EditText editText1 = (EditText)findViewById(R.id.editText1);
				final String User_Name = editText1.getText().toString();
				
				RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
				RadioButton radioButton =(RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
				final String tagString = (String)radioButton.getTag();
				
				EditText editText2 = (EditText)findViewById(R.id.editText2);
				final String Memo = editText2.getText().toString();
		//		String show = P_Name + "," + User_Password + "," + User_Password2 +","+tagString;
		//		Toast.makeText(RegisteredActivity.this, show, 1).show();
				
				if(TextUtils.isEmpty(User_Name)||TextUtils.isEmpty(Memo))
				{
					Toast.makeText(AddTableActivity .this, "����������,��ע", 1).show();	
				}else {
					//�漰�����������Ӵ���߳��н���
					new Thread(){  
						   @Override  
						   public void run()  
						   {  
							   List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
							   
							//   list = dbUtil.getUser("2");
							   list = dbUtil.getUser(User_Name);
							   Message message = Message.obtain();
							   System.out.println("list.size-------->>"+ list.size());
							   if (list.size() == 0 ) {
								   dbUtil.insert_User(User_Name, "0", tagString, "1",Memo);
								   //�漰UI�������������߳��в������ᵼ�����������߳��޷��ı�UI���������ֵ��
								   //�첽��ȡ����ˢ��UI�ؼ�����ʱ���ȡ�ķ�������Handler��Ϣ����
								   //�������ʹ��AsyncTask�첽�������ֽ��������
								  message.arg1 = 1;
								  handler.sendMessage(message);
								  finish();
								Intent intent  = new Intent(AddTableActivity .this,TableManagementActivity.class);
								startActivity(intent);
								  	
							} else {
								message.arg1 = 2;
								handler.sendMessage(message);
							}
							   	  }  
						}.start(); 
						
						
				}
			}
			
			private Handler handler = new Handler() {

				@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					if (msg.arg1 == 1) {
						Toast.makeText(AddTableActivity.this, "���ӳɹ�", 1).show();
						finish();
					}else {
						Toast.makeText(AddTableActivity .this, "�����Ѵ��ڣ����޸�����", 1).show();
					}
					
					
				}
				
			};
		
		});
		
		
		
		
	}

	

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO �Զ����ɵķ������
	
		finish();
		Intent intent  = new Intent(AddTableActivity .this,TableManagementActivity .class);
		startActivity(intent);
		
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_table, menu);
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
