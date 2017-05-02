package com.example.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TableManagementActivity extends Activity {
	
	private WordWrapView wordWrapView;
	private DBUtil dbUtil;
	private List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table_management);
		
		dbUtil = new DBUtil(); 
		
		TextView textView4 = (TextView)findViewById(R.id.textView4);
//		textView4.setBackgroundColor(Color.DKGRAY);
		
		TextView textView3 = (TextView)findViewById(R.id.textView3);
//		textView3.setBackgroundColor(Color.GRAY);
		
		
		TextView textView5 = (TextView)findViewById(R.id.textView5);
//		textView5.setTextColor(android.graphics.Color.GREEN);
		textView5.setTextColor(0xff32CD32);
		TextView textView6 = (TextView)findViewById(R.id.textView6);
//		textView6.setTextColor(android.graphics.Color.YELLOW);
		textView6.setTextColor(0xffFFB90F);
		TextView textView7 = (TextView)findViewById(R.id.textView7);
	//	textView7.setTextColor(android.graphics.Color.RED);
		textView7.setTextColor(0xffEE0000);
		
		
	    wordWrapView = (WordWrapView) this.findViewById(R.id.view_wordwrap);
		new Thread(){  
			   @Override  
			   public void run()  
			   {
				   Message message = Message.obtain();
//				   list = adapter.getdate(); 							
				  list =  dbUtil.selectAllUser();  
				  message.arg1 = 1;
				  handler.sendMessage(message);
				  
			   }  
			}.start();
	
			
		
		
		
		Button button = (Button )findViewById(R.id.button1);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				 Intent intent  = new Intent(TableManagementActivity.this,AddTableActivity.class);
				 startActivity(intent);
				finish();
			}
		});
		
	}
	
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if (msg.arg1 == 1){
				
				 for ( int  i = 0; i < list.size(); i++) {
				      TextView textview = new TextView(TableManagementActivity.this);
				      textview.setText(list.get(i).get("User_Name").toString().trim());
				      textview.setId(i);
				      textview.setTextSize(40);
				      textview.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
				      int w =textview.getMeasuredWidth();
				      int h =textview.getMeasuredHeight();
				      if (list.get(i).get("User_Type").toString().equals("1")) {
//				    	  textview.setBackgroundColor(Color.DKGRAY);
				    	  Bitmap hall = BitmapFactory.decodeResource(getResources(),R.drawable.hall); 
				    	
				    	  //计算缩放比例
				    	  float heightScale = ((float) h) / hall.getHeight(); 
				    	  float widthScale = ((float) w) / hall.getWidth(); 

				    	  Matrix matrix = new Matrix(); 
				    	  matrix.postScale(heightScale, widthScale); 
				    	  System.out.println(h + " "+ w);
				    	  hall = Bitmap.createBitmap(hall, 0, 0,hall.getWidth(),hall.getHeight(), matrix, true); 
				    
				    	  Drawable newBitmapDrawable = new BitmapDrawable(hall); 
				    	//  hall.recycle();
				    	
				    	  if (hall != null && !hall.isRecycled()) 
				    	  { 
				    	       hall=null;
				    	  }     
				    	  
				    	  textview.setBackground(newBitmapDrawable);
				    	  
					}else {
//				    	  textview.setBackgroundColor(Color.DKGRAY);
				    	  Bitmap box = BitmapFactory.decodeResource(getResources(),R.drawable.box); 
				    	
				    	  //计算缩放比例
				    	  float heightScale = ((float) h) / box.getWidth(); 
				    	  float widthScale = ((float) w) / box.getHeight(); 

				    	  Matrix matrix = new Matrix(); 
				    	  matrix.postScale(heightScale, widthScale); 
				    	  System.out.println(h + " "+ w);
				    	  box = Bitmap.createBitmap(box, 0, 0,box.getWidth(),box.getHeight(), matrix, true); 
				    
				    	  Drawable newBitmapDrawable = new BitmapDrawable(box); 
				    	//  hall.recycle();
				    	
				    	  if (box != null && !box.isRecycled()) 
				    	  { 
				    		  box=null;
				    	  }     
				    	  
				    	  textview.setBackground(newBitmapDrawable);
				    	  
					}
				      
				      
				      
				      if (list.get(i).get("Logged_in").toString().endsWith("1")) {
				    	  textview.setTextColor(android.graphics.Color.GREEN);
					}else if (list.get(i).get("Logged_in").toString().endsWith("2")) {
						textview.setTextColor(android.graphics.Color.RED);
					}else {
						textview.setTextColor(android.graphics.Color.YELLOW);
					}
				     
				      wordWrapView.addView(textview);
				      
				      textview.setOnLongClickListener(new OnLongClickListener() {
							
							@Override
							public boolean onLongClick(View v) {
								// TODO 自动生成的方法存根
								
								Toast.makeText(TableManagementActivity.this, list.get(v.getId()).get("Memo").toString().trim(), 1).show();
								return false;
							}
						});
				      
				      textview.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO 自动生成的方法存根
							if (list.get(v.getId()).get("Logged_in").toString().equals("1")) {
								Intent intent  = new Intent(TableManagementActivity.this,TableActivity .class);
								 intent.putExtra("User_Name", list.get(v.getId()).get("User_Name"));
								
								startActivity(intent);
								finish();
							}else {
								 Intent intent  = new Intent(TableManagementActivity.this,Ordered2Activity.class);
	
									intent.putExtra("User_Name", list.get(v.getId()).get("User_Name"));
									startActivity(intent);
								 Toast.makeText(TableManagementActivity.this, "该餐桌使用中，请勿更改", 1).show();
							}
						
						}
					});

				    }
			}
		}
		
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.table_management, menu);
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
