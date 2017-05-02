package com.example.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.order.R.drawable;
import com.example.order.SeatActivity.ProductAdapter;

import android.animation.RectEvaluator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RecipeActivity extends Activity {

	private ListView listView;
	private ProductAdapter adapter;
	private DBUtil dbUtil;  
	private String R_Type = "" ;
	private int mark = 1;
	private List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	   
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe);
		
		dbUtil = new DBUtil();
		listView = (ListView)this.findViewById(R.id.listView1);
				
		adapter = new ProductAdapter();
		
		
		final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayout3) ;
		final RadioGroup group = new RadioGroup(RecipeActivity.this);  
		
		new Thread(){  
			   @Override  
			   public void run()  
			   {
				   
				   list = dbUtil.selectR_Type();
				//   List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
				   if(list.size() != 0){
				   R_Type = list.get(0).get("R_Type").toString();
				   mark = 0;
				   }else {
					mark = 0;
				}
			   } 	
						}.start(); 
//					    
//			 RadioGroup group = new RadioGroup(AddActivity.this);  
			while (mark == 1) {
					//此处有等待上方线程结束作用
							
			}
								
			for(int i=0; i<list.size(); i++)  
			{  
				RadioButton tempButton = new RadioButton(RecipeActivity.this);  
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
					    
				    
		//	group.setBackgroundResource(R.drawable.recipe_background);
			
			group.setOrientation(RadioGroup.HORIZONTAL);
			linearLayout.addView(group);
		
//		final RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
//		RadioButton radioButton =(RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
//		R_Type = (String)radioButton.getTag();
//		
		while (mark == 1) {
			//此处有等待上方线程结束作用
			
		}
		
		new MyTask().execute();
		
		group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO 自动生成的方法存根
				
				RadioButton radioButton =(RadioButton)findViewById(group.getCheckedRadioButtonId());
				R_Type = (String)radioButton.getText();
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
				
			//	System.out.println("view.toString() ----->>"+view.toString() );	
				Toast.makeText(RecipeActivity.this,"第" + (position + 1)+ "   " + 
						list.get(position).get("R_Name").toString() ,3).show();
				
				Intent intent  = new Intent(RecipeActivity.this,DelActivity.class);
		//		 System.out.println("list----->>"+list.get(1).get("User_Name"));
				 intent.putExtra("R_Name", list.get(position).get("R_Name"));
				 intent.putExtra("R_Price", list.get(position).get("R_Price"));
				 startActivity(intent);
				 finish();
				
			}
	    });	
		
		
		Button button = (Button)findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				
				Intent intent  = new Intent(RecipeActivity.this,AddActivity.class);
				startActivity(intent);
				finish();
				
			}			
		});
				//	new MyTask().execute();
		
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
				view = LayoutInflater.from(RecipeActivity.this).inflate(R.layout.recipelistview,null);				
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recipe, menu);
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
