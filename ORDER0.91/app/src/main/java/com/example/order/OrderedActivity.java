package com.example.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.order.OrderActivity.MyTask;
import com.example.order.OrderActivity.ProductAdapter;

import android.R.color;
import android.R.string;
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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class OrderedActivity extends Activity {

	
	private DBUtil dbUtil;
	private ListView listView;
	private ProductAdapter adapter;
	private float P_Amount = 0;
	private String User_Name;
	private String O_ID;
	private int  mark = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ordered);
		
		Intent intent = getIntent();
		User_Name = intent.getStringExtra("User_Name");
	//	P_Amount = Float.parseFloat(intent.getStringExtra("P_Amount"));
		P_Amount = intent.getFloatExtra("P_Amount", 0);
		System.out.println("OrderedActivity P_Amount :"+  P_Amount);
		
		TextView textView = (TextView)findViewById(R.id.textView2);
		textView.setText(String.valueOf(P_Amount));
		
		dbUtil = new DBUtil();
		listView = (ListView)this.findViewById(R.id.listView1);
				
		adapter = new ProductAdapter();
		
		mHandler.sendEmptyMessageDelayed(1,0);
		
		
		
		 final Dialog alertDialog = new AlertDialog.Builder(this)
			.setMessage( "删除改菜式？？")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() { 
	                   
	                  @Override 
	                  public void onClick(DialogInterface dialog, int which) { 
	                      // TODO Auto-generated method stub  
	                		new Thread(){  
	     					   @Override  
	     					   public void run()  
	     					   {
	     						   List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
//	     						   list = adapter.getdate(); 
	     						   
	     						   
	     						   dbUtil.delete_Order(O_ID); 
	     						   System.out.println("ORDERACTIVITY delete_Order----->>"+O_ID);
	     						   
	     						   
	     						   //重启UI以更新
	     						   Intent intent = new Intent(OrderedActivity.this, OrderedActivity.class);
	     						   intent.putExtra("User_Name", User_Name);

	     						   list = dbUtil.selectOrder(User_Name); 
	     							  
	     						   P_Amount = 0 ;
	     							for(int i = 0;i < list.size();i++)
	     							{
	     							//	System.out.println(" OrderActivity R_Price :"+  list.get(i).get("R_Price") );
	     								P_Amount = P_Amount + Float.parseFloat(list.get(i).get("R_Price"));
	     							}
	     						   
	     						   intent.putExtra("P_Amount", P_Amount);
	     						   startActivity(intent);                                    
	     						   
	     						   finish();        
	     						   mark = 2;
	     					   }  
	     					}.start(); 
	   					
	              // 	   Toast.makeText(CookActivity.this, "", 1).show();
//	               	   
//	               	   Intent intent  = new Intent(OrderedActivity.this,OrderedActivity.class);
//							mark = 2;
//							startActivity(intent);
//							finish();
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
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自动生成的方法存根
				dbUtil = new DBUtil();
				List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			//	String User_Type = "1";
				list = adapter.getdate(); 
			//	System.out.println("ORDERACTIVITY LIST----->>"+list.toString());
				O_ID = list.get(position).get("O_ID");
//				final String R_Price = list.get(position).get("R_Price");
//				final String R_Name = list.get(position).get("R_Name");
				
				System.out.println("ORDERACTIVITY LIST----->>"+list.get(position).get("O_Cook").toString());
				
				if (list.get(position).get("O_Cook").toString().equals("3") ||
						list.get(position).get("O_Cook").toString().equals("1") || 
						list.get(position).get("O_Cook").toString().equals("2")) {
					Toast.makeText(OrderedActivity.this,"该菜式已经烹饪，不可删除",1).show();
					
				} else {

					alertDialog.show();
				}
				
			}
	    });	
		
		
		Button button = (Button)findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				
				dbUtil = new DBUtil();
				List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			//	String User_Type = "1";
//				list = dbUtil.selectOrder(User_Name); 
				
				new Thread(){  
					   @Override  
					   public void run()  
					   {
						   
						   dbUtil.insert_Pay(User_Name,String.valueOf(P_Amount));
						   Intent intent = new Intent(OrderedActivity.this, EndActivity.class);
						   intent.putExtra("User_Name", User_Name);

						   startActivity(intent);  
						   finish();        
						   mark = 2;
							
					   }  
					}.start(); 
					
				
			}
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
				System.out.println("doInBackground   User_Name" +  User_Name);
				list = dbUtil.selectOrder(User_Name); 
			//	System.out.println(R_Type +"  recipeactivity  list.size-------->>"+  list.size());
			//	System.out.println("R_Type"+  R_Type);
				System.out.println("orderedactivity list"+  list.toString());
				
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
					view = LayoutInflater.from(OrderedActivity.this).inflate(R.layout.recipelistview,null);				
				}else {
					view = convertView;
				}
				
			//	Toast.makeText(RecipeActivity.this, list.get(position).get("R_Name").toString(), 1).show();
				TextView textview1 = (TextView)view.findViewById(R.id.textView4);
				textview1.setText(list.get(position).get("R_Name").toString());
				
				
				
				TextView textview2 = (TextView)view.findViewById(R.id.textView5);
				textview2.setText(list.get(position).get("R_Price").toString());
				
				TextView textview3 = (TextView)view.findViewById(R.id.textView1);
				String  cook = list.get(position).get("O_Cook").toString();
				
				if (cook.equals("0")) {
					textview3.setText("未烹饪");
					textview3.setTextColor(0xff32CD32);
					TextView textview8 = (TextView)view.findViewById(R.id.textView8);
					textview8.setText(list.get(position).get("Order_Time").toString()/*.subSequence(9, list.get(position).get("Order_Time").toString().length())*/);
					textview8.setTextColor(0xff32CD32);
					System.out.println("ORDERACTIVITY cook ----->>"+ cook+ "未烹饪");
				}else if (cook.equals("1")) {
					textview3.setText("烹饪中");
					textview3.setTextColor(0xffFFB90F);
					TextView textview8 = (TextView)view.findViewById(R.id.textView8);
					textview8.setText(list.get(position).get("Cook_Time").toString()/*.subSequence(9, list.get(position).get("Cook_Time").toString().length())*/);
					textview8.setTextColor(0xffFFB90F);
				}else if (cook.equals("2")){
					textview3.setText("已完成");
					textview3.setTextColor(android.graphics.Color.RED);
					TextView textview8 = (TextView)view.findViewById(R.id.textView8);
					textview8.setText("正在送餐");
					textview8.setTextColor(android.graphics.Color.RED);
				}else {
					textview3.setText("已上菜");
					textview3.setTextColor(android.graphics.Color.BLACK);
					TextView textview8 = (TextView)view.findViewById(R.id.textView8);
					textview8.setText(list.get(position).get("Sent_Time").toString()/*.subSequence(9, list.get(position).get("Sent_Time").toString().length())*/);
					textview8.setTextColor(android.graphics.Color.BLACK);
				}

				
				
				return view;
			}
		
	}

		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			
			if (keyCode == KeyEvent.KEYCODE_HOME) {
				moveTaskToBack(false);
				return true;
			}
			return super.onKeyDown(keyCode, event);
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ordered, menu);
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
