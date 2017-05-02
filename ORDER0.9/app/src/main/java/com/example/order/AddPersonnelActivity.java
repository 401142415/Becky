package com.example.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.R.integer;
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

public class AddPersonnelActivity extends Activity {

	private View view;
	private DBUtil dbUtil; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registered);
		//DBUTILʵ��������ʵ�����ᱨ��
		dbUtil = new DBUtil(); 
		view = this.findViewById(R.id.button1);
		
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				EditText editText1 = (EditText)findViewById(R.id.editText1);
				final String P_Name = editText1.getText().toString();
				
				EditText editText2 = (EditText)findViewById(R.id.editText2);
				final String P_Password = editText2.getText().toString();
				
				EditText editText3 = (EditText)findViewById(R.id.editText3);
				final String P_Password2 = editText3.getText().toString();
				
				RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
				RadioButton radioButton =(RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
				final String tagString = (String)radioButton.getTag();
				
		//		String show = P_Name + "," + User_Password + "," + User_Password2 +","+tagString;
		//		Toast.makeText(RegisteredActivity.this, show, 1).show();
				
				if(TextUtils.isEmpty(P_Name) || TextUtils.isEmpty(P_Password)|| TextUtils.isEmpty(P_Password2))
				{
					Toast.makeText(AddPersonnelActivity.this, "�������û������û�����", 1).show();	
				}else if(!P_Password.equals(P_Password2) )
				{
					Toast.makeText(AddPersonnelActivity.this, "�������벻һ��", 1).show();		
					
				}else {
					//�漰�����������Ӵ���߳��н���
					new Thread(){  
						   @Override  
						   public void run()  
						   {  
							   List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
							   System.out.println("P_Name-------->>"+ P_Name);	
							//   list = dbUtil.getUser("2");
							   list = dbUtil.selectPersonnel(P_Name);
							   Message message = Message.obtain();
							   System.out.println("list.size-------->>"+ list.size());
							   if (list.size() == 0 ) {
								   dbUtil.insert_Personnel(P_Name, P_Password, tagString);
								   //�漰UI�������������߳��в������ᵼ�����������߳��޷��ı�UI���������ֵ��
								   //�첽��ȡ����ˢ��UI�ؼ�����ʱ���ȡ�ķ�������Handler��Ϣ����
								   //�������ʹ��AsyncTask�첽�������ֽ��������
								  message.arg1 = 1;
								  handler.sendMessage(message);
								  finish();
								Intent intent  = new Intent(AddPersonnelActivity.this,PersonnelManagementActivity.class);
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
						Toast.makeText(AddPersonnelActivity.this, "���ӳɹ�", 1).show();
						finish();
					}else {
						Toast.makeText(AddPersonnelActivity.this, "�û��Ѵ��ڣ����޸��û���", 1).show();
					}
					
					
				}
				
			};
		
		});
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO �Զ����ɵķ������
	
		finish();
		Intent intent  = new Intent(AddPersonnelActivity.this,PersonnelManagementActivity.class);
		startActivity(intent);
		
		return super.onKeyDown(keyCode, event);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_personnel, menu);
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
