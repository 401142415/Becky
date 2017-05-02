package com.example.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.order.RecipeActivity.MyTask;
import com.example.order.RecipeActivity.ProductAdapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.NetworkInfo.State;
import android.os.AsyncTask;
import android.os.Bundle;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class OrderActivity extends Activity {
	static Activity orderActivity1; 
	private DBUtil dbUtil;
	private ListView listView;
	private ProductAdapter adapter;
	private String R_Type = "";
	private float P_Amount = 0;
	private List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		Intent intent = getIntent();
		final String User_Name = intent.getStringExtra("User_Name");
		//用于在另一个ACTIVITY结束本ACTIVITY
		orderActivity1 = this;
		
		dbUtil = new DBUtil();
		listView = (ListView)this.findViewById(R.id.listView1);
				
		adapter = new ProductAdapter();
			
//		
//		final RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
//		RadioButton radioButton =(RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
//		R_Type = (String)radioButton.getTag();
//				
		final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayout3) ;
		final RadioGroup group = new RadioGroup(OrderActivity.this);  
		
		new Thread(){  
			   @Override  
			   public void run()  
			   {
				   
				   list = dbUtil.selectR_Type();
				//   List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
				   R_Type = list.get(0).get("R_Type").toString();
			   }  	
						}.start(); 
//					    
//			 RadioGroup group = new RadioGroup(AddActivity.this);  
			while (list.size() == 0) {
					//此处有等待上方线程结束作用
							
			}
								
			for(int i=0; i<list.size(); i++)  
			{  
				RadioButton tempButton = new RadioButton(OrderActivity.this);  
				tempButton.setTextColor(Color.BLACK);
				tempButton.setTextSize(30);
			//	tempButton.setTextColor(Color.WHITE);
				tempButton.setPadding(40, 0, 40, 0);                 // 设置文字距离按钮四周的距离   
				tempButton.setText(list.get(i).get("R_Type").toString().trim());  
				tempButton.setId(i);
				
				if (i== 0) {
					tempButton.setChecked(true);
				}else {
					tempButton.setChecked(false);
				}
				
			    
			tempButton.setBackgroundResource(R.drawable.recipe);
			    
		    	  //设置透明色以隐藏radiobutton前方圆圈
				tempButton.setButtonDrawable(getResources().getDrawable(android.R.color.transparent));
				
				group.addView(tempButton); 
					        
			} 
					    
				    
			
			group.setOrientation(RadioGroup.HORIZONTAL);
			linearLayout.addView(group);
		
//		final RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
//		RadioButton radioButton =(RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
//		R_Type = (String)radioButton.getTag();
//		
		while (R_Type.isEmpty()) {
			//此处有等待上方线程结束作用
			
		}
		new MyTask().execute();
		
		group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO 自动生成的方法存根
				
				RadioButton radioButton =(RadioButton)findViewById(group.getCheckedRadioButtonId());
				R_Type = (String)radioButton.getText();
				System.out.println("ORDERACTIVITY R_Type----->>"+R_Type);
				new MyTask().execute();
				
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自动生成的方法存根
				dbUtil = new DBUtil();
				List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			//	String User_Type = "1";
				list = adapter.getdate(); 
				System.out.println("ORDERACTIVITY LIST----->>"+list.toString());
				final String R_ID = list.get(position).get("R_ID");
				final String R_Price = list.get(position).get("R_Price");
				final String R_Name = list.get(position).get("R_Name");
				
				new Thread(){  
					   @Override  
					   public void run()  
					   {
//						   List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
//						   list = adapter.getdate(); 							
						   dbUtil.insert_Order(R_ID,User_Name,R_Price,R_Name);  
						  
					   }  
					}.start(); 
					Toast.makeText(OrderActivity.this,"添加" + 
							list.get(position).get("R_Name").toString().trim(),1).show();
				
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
						   List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
//						   List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
//						   list = adapter.getdate(); 							
						   list = dbUtil.selectOrder(User_Name); 
						  
						   P_Amount = 0 ;
						   System.out.println(" OrderActivity list :"+  list.toString() );
							for(int i = 0;i < list.size();i++)
							{
								System.out.println(" OrderActivity R_Price :"+  list.get(i).get("R_Price") );
								P_Amount = P_Amount + Float.parseFloat(list.get(i).get("R_Price"));
							}
							System.out.println("OrderActivity P_Amount :"+  P_Amount);
							
							Intent intent  = new Intent(OrderActivity.this,OrderedActivity.class);
							intent.putExtra("User_Name", User_Name);
							
							intent.putExtra("P_Amount", P_Amount);
							
							startActivity(intent);
							
					   }  
					}.start(); 
					
				
			}
	    });	
				
				
	
		
	}
	
	class MyTask extends AsyncTask<String, Void, List<HashMap<String, String>>>{

		@Override
		protected List<HashMap<String, String>> doInBackground(String... params) {
			// TODO 自动生成的方法存根
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

			list = dbUtil.selectKindsofReciper(R_Type); 
			System.out.println(R_Type +"  recipeactivity  list.size-------->>"+  list.size());
			System.out.println("R_Type"+  R_Type);
			
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
				view = LayoutInflater.from(OrderActivity.this).inflate(R.layout.cooklistview,null);				
			}else {
				view = convertView;
			}
			
		//	Toast.makeText(RecipeActivity.this, list.get(position).get("R_Name").toString(), 1).show();
			TextView textview1 = (TextView)view.findViewById(R.id.textView4);
			textview1.setText(list.get(position).get("R_Name").toString());
			
			TextView textview2 = (TextView)view.findViewById(R.id.textView5);
			textview2.setText(list.get(position).get("R_Price").toString());
			
			return view;
		}
		
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			moveTaskToBack(false);
			return true;
		}
		
		if (keyCode == KeyEvent.KEYCODE_HOME) {
			moveTaskToBack(false);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order, menu);
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
