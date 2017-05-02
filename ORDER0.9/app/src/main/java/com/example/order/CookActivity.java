package com.example.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.order.CashierActivity.MyTask;
import com.example.order.CashierActivity.ProductAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CookActivity extends Activity {

	
	private DBUtil dbUtil;
	private ListView listView;
	private ProductAdapter adapter;
	private String O_ID;
	private String O_Cook;
	private int Num;
	private int  mark = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cook);
		
		//确定烹饪完成

		 final Dialog alertDialog = new AlertDialog.Builder(this)
		.setMessage( "该菜式已完成？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() { 
                   
                  @Override 
                  public void onClick(DialogInterface dialog, int which) { 
                      // TODO Auto-generated method stub  
               	   new Thread(){  
   					   @Override  
   					   public void run()  
   					   {
   						dbUtil = new DBUtil();
   						dbUtil.Update_Cook(O_ID,"2");
   						System.out.println(O_ID + " Update_Cook ");
   						
   						  
   					   }  
   					}.start(); 
              // 	   Toast.makeText(CookActivity.this, "", 1).show();
               	   
               	   Intent intent  = new Intent(CookActivity.this,CookActivity.class);
						mark = 2;
						startActivity(intent);
						finish();
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
		 
		 //确定开始烹饪
		 
		 final Dialog alertDialog1 = new AlertDialog.Builder(this)
			.setMessage( "开始烹饪该菜式？")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() { 
	                   
	                  @Override 
	                  public void onClick(DialogInterface dialog, int which) { 
	                      // TODO Auto-generated method stub  
	               	   new Thread(){  
	   					   @Override  
	   					   public void run()  
	   					   {
	   						dbUtil = new DBUtil();
	   						dbUtil.Update_Cook(O_ID,"1");
	   						System.out.println(O_ID + " Update_Cook ");
	   						
	   						  
	   					   }  
	   					}.start(); 
	              // 	   Toast.makeText(CookActivity.this, "", 1).show();
	               	   //重启以刷新该页面
	               	   Intent intent  = new Intent(CookActivity.this,CookActivity.class);
							mark = 2;
							startActivity(intent);
							finish();
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
		 	dbUtil = new DBUtil();
			listView = (ListView)this.findViewById(R.id.listView1);
					
			adapter = new ProductAdapter();
			
			mHandler.sendEmptyMessageDelayed(1,0);
			Message message = Message.obtain();
			listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO 自动生成的方法存根
					Num = position;
					new Thread(){  
						   @Override  
						   public void run()  
						   {
							   Message message = Message.obtain();
								List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

								list = dbUtil.selectUnCook(); 
								
								O_ID = list.get(Num).get("O_ID").toString();
								O_Cook = list.get(Num).get("O_Cook").toString();
								System.out.println(O_ID + " and " +Num);
								if (O_Cook.equals("0")) {
									 message.arg1 = 0;
									 handler.sendMessage(message);
								}else {
									 message.arg1 = 1;
									 handler.sendMessage(message);
								}
								
						   }  
						}.start(); 
						
					
				}
				
				
				private Handler handler = new Handler() {

						@Override
						public void handleMessage(Message msg) {
							// TODO Auto-generated method stub
							if (msg.arg1 == 0) {
								alertDialog1.show();
							}else{
								alertDialog.show();
							}
							
							
						}
						
					};
		    });	
		
	}

	
Handler mHandler = new Handler(new Handler.Callback() {
		
		@Override
		public boolean handleMessage(Message msg) {
			// TODO 自动生成的方法存根
			
			switch (msg.what) {
			case 1 :
				new MyTask().execute();
				//递归，不断循环刷新
				
				mHandler.sendEmptyMessageDelayed(mark,10000);
			
			//	Toast.makeText(SeatActivity.this, "liseview", 1).show();
				break;

			default:
				break;
			}
			return false;
		}
	});
	
	
	
	class MyTask extends AsyncTask<String, Void, List<HashMap<String, String>>>{

		@Override
		protected List<HashMap<String, String>> doInBackground(String... params) {
			// TODO 自动生成的方法存根
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

			list = dbUtil.selectUnCook(); 
						
			return list;
		}
		
	
		
		@Override
		protected void onPostExecute(List<HashMap<String, String>> result) {
			// TODO 自动生成的方法存根
			super.onPostExecute(result);
			adapter.setDate(result);
			listView.setAdapter(adapter);
			
			adapter.notifyDataSetChanged();
			
		}
		
	}
	
	
	public class ProductAdapter extends BaseAdapter{

		
		List<HashMap<String, String>> list;
				
		public void setDate(List<HashMap<String, String>> list){
			
			this.list = list;
			
		}
		
		public List<HashMap<String, String>> getdate() {
			
			return list;
			
		}
		@Override
		public int getCount() {
			// TODO 自动生成的方法存根
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO 自动生成的方法存根
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO 自动生成的方法存根
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO 自动生成的方法存根
			
			View view = null;
			if(convertView == null){
				view = LayoutInflater.from(CookActivity.this).inflate(R.layout.cooklistview,null);				
			}else {
				view = convertView;
			}
//			System.out.println("ORDERACTIVITY list ----->>"+ list.toString());
		//	Toast.makeText(RecipeActivity.this, list.get(position).get("R_Name").toString(), 1).show();
			TextView textview1 = (TextView)view.findViewById(R.id.textView4);
			textview1.setText(list.get(position).get("R_Name").toString());
			String  cook = list.get(position).get("O_Cook").toString();
			
			if (cook.equals("0")) {
				textview1.setTextColor(0xff32CD32);
			}else if (cook.equals("1")) {
				textview1.setTextColor(0xffFFB90F);
			}
			
			
			TextView textview2 = (TextView)view.findViewById(R.id.textView5);
			textview2.setText(list.get(position).get("User_Name").toString());
			
			return view;
		}
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO 自动生成的方法存根
	
		finish();
		mark = 2;
		
		return super.onKeyDown(keyCode, event);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cook, menu);
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
