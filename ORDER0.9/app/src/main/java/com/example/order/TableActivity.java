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
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TableActivity extends Activity {

	private DBUtil dbUtil;
	private String User_Name;
	private String User_Type;
	private String Memo;
	private View view2;
	private View view;
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_table);
	
	Intent intent = getIntent();
	User_Name = intent.getStringExtra("User_Name");
	
	dbUtil = new DBUtil();
	
	new Thread(){  
		   @Override  
		   public void run()  
		   {
			   dbUtil = new DBUtil();
			   Message message = Message.obtain();
			   List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			   list = dbUtil.getUser(User_Name);
			   User_Name = list.get(0).get("User_Name").toString().trim();
			   User_Type = list.get(0).get("User_Type").toString().trim();
			   Memo = list.get(0).get("Memo").toString().trim();
			   
			   message.arg1 = 1;
				  handler.sendMessage(message);
				  
		   		}  
		   }.start(); 
		   
		   
		   
		   //���߳�����һ�롣�ȴ����߳̽���������ֵ���������¸�ֵ����������Ϊ�ն�����
		   //�����漰UI���¡��޷��������߳�
//		   SystemClock.sleep(1000); 
		   //����������textv�ȸ�ֵ����
		   
		
		
		
        view2 = this.findViewById(R.id.button2);
        
        view2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				TextView textview1 = (TextView)findViewById(R.id.textView1);
				User_Name = textview1.getText().toString();
			
				
				RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
				RadioButton radioButton =(RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
				final String tagString = (String)radioButton.getTag();
				
				EditText editText = (EditText)findViewById(R.id.editText1);
				Memo = editText.getText().toString();
				
				new Thread(){  
					   @Override  
					   public void run()  
					   {
						   dbUtil.Update_Type(User_Name, tagString,Memo);
						  
						   finish();
						   Intent intent  = new Intent(TableActivity.this,TableManagementActivity.class);
						   startActivity(intent);
						   
					   }  
					}.start(); 
					
			
			}
			
			
		});
		
        

        final Dialog alertDialog = new AlertDialog.Builder(this)
		.setMessage( "ɾ������Ϣ��")
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() { 
                   
                  @Override 
                  public void onClick(DialogInterface dialog, int which) { 
                      // TODO Auto-generated method stub  
               	   new Thread(){  
   					   @Override  
   					   public void run()  
   					   {
   						dbUtil.delete_Users(User_Name);
   					   }  
   					}.start(); 
              // 	   Toast.makeText(CookActivity.this, "", 1).show();
   					finish();
					Intent intent  = new Intent(TableActivity.this,TableManagementActivity.class);
					startActivity(intent);
                  } 
              }).
              setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
           	     
           	     @Override
           	     public void onClick(DialogInterface dialog, int which) {
           	      // TODO Auto-generated method stub
           	    	 
           	  //  	 Toast.makeText(CookActivity.this, "����δ���", 1).show();
           	     }
           	    })
           	    .create();
		
        
        view = this.findViewById(R.id.button1);
        
        view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
			 alertDialog.show();
			
			}
			
			
		});
        
        
	
}

private Handler handler = new Handler() {

	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		if (msg.arg1 == 1){
			System.out.println("tableactivity----------->>"+User_Name +User_Type);
		  TextView textview1 = (TextView)findViewById(R.id.textView1);
		  textview1.setText(User_Name);
		   
		   RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
		   
		   if (User_Type.equals("1")) {
			   radioGroup.check(R.id.radio0);
		}else if (User_Type.equals("2")) {
			 radioGroup.check(R.id.radio1);
		}
		   EditText editText1 = (EditText)findViewById(R.id.editText1);
		   editText1.setText(Memo);
		   
		}
	}
	
};

public boolean onKeyDown(int keyCode, KeyEvent event) {
	// TODO �Զ����ɵķ������

	finish();
	Intent intent  = new Intent(TableActivity .this,TableManagementActivity.class);
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
