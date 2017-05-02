package com.example.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.order.CookActivity.MyTask;
import com.example.order.CookActivity.ProductAdapter;

import android.app.Activity;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class PersonnelManagementActivity extends Activity {
	
	private DBUtil dbUtil;
	private ListView listView;
	private ProductAdapter adapter;
	private int  mark = 1;
	private int Num;
	private View view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personnel_management);
		
		
		dbUtil = new DBUtil();
		listView = (ListView)this.findViewById(R.id.listView1);
				
		adapter = new ProductAdapter();
		
		new MyTask().execute();
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自动生成的方法存根
				List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

				list = adapter.getdate(); 
				Intent intent  = new Intent(PersonnelManagementActivity.this,PersonnelActivity.class);
				//		 System.out.println("list----->>"+list.get(1).get("User_Name"));
						 intent.putExtra("P_Name", list.get(position).get("P_Name"));
						 startActivity(intent);
						finish();
				
			}
		
	    });	
	
		
		 view = this.findViewById(R.id.button1);
	        
	        view.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					Intent intent1  = new Intent(PersonnelManagementActivity.this,AddPersonnelActivity.class);
					//		 System.out.println("list----->>"+list.get(1).get("User_Name"));
							 
							startActivity(intent1);
							finish();
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

		list = dbUtil.selectAllPersonnel(); 
					
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
			view = LayoutInflater.from(PersonnelManagementActivity.this).inflate(R.layout.cooklistview,null);				
		}else {
			view = convertView;
		}
//		System.out.println("ORDERACTIVITY list ----->>"+ list.toString());
	//	Toast.makeText(RecipeActivity.this, list.get(position).get("R_Name").toString(), 1).show();
		TextView textview1 = (TextView)view.findViewById(R.id.textView4);
		textview1.setText(list.get(position).get("P_Name").toString());
		
		String  P_Function = list.get(position).get("P_Function").toString();
		TextView textview2 = (TextView)view.findViewById(R.id.textView5);
		if (P_Function.equals("0")) {
			textview2.setText("大堂服务生");
		}else if (P_Function.equals("1")) {
			textview2.setText("后勤人员");
		}else if (P_Function.equals("2")) {
			textview2.setText("厨房人员");
		}else if (P_Function.equals("3")) {
			textview2.setText("收银员");
		}else if (P_Function.equals("4")) {
			textview2.setText("管理员");
		}
			
	
		
		return view;
	}
}

	
}

	