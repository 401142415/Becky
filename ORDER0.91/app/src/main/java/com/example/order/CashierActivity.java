package com.example.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.order.OrderActivity.MyTask;
import com.example.order.OrderActivity.ProductAdapter;

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

public class CashierActivity extends Activity {

	
	private DBUtil dbUtil;
	private ListView listView;
	private ProductAdapter adapter;
	private String User_Name;
	private int Num;
	private int  mark = 1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cashier);
		
		
		
	
		 final Dialog alertDialog = new AlertDialog.Builder(this)
		.setMessage( "���û������ѽ�����")
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() { 
                    
                   @Override 
                   public void onClick(DialogInterface dialog, int which) { 
                       // TODO Auto-generated method stub  
                	   new Thread(){  
    					   @Override  
    					   public void run()  
    					   {
    						   dbUtil.Update_Pay(User_Name);
    						   dbUtil.Update_Order(User_Name);
    						   dbUtil.Update_Logged(User_Name,"1","��");
    						  
    					   }  
    					}.start(); 
                	   Toast.makeText(CashierActivity.this, "���˽���", 1).show();
                	   
                	   Intent intent  = new Intent(CashierActivity.this,CashierActivity.class);
						mark = 2;
						startActivity(intent);
						finish();
                   } 
               }).setNeutralButton("�鿴����", new DialogInterface.OnClickListener() {
                   
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                  	 
                  	 Intent intent  = new Intent(CashierActivity.this,Ordered2Activity.class);
  						System.out.println("seatactivity----->>"+User_Name);
  						intent.putExtra("User_Name", User_Name);
  						startActivity(intent);
                   }
                  }).
               setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
            	     
            	     @Override
            	     public void onClick(DialogInterface dialog, int which) {
            	      // TODO Auto-generated method stub
            	    	
            	    	 
            	    	 Toast.makeText(CashierActivity.this, "����δ���", 1).show();
            	     }
            	    })
            	    .create();
		
		
		dbUtil = new DBUtil();
		listView = (ListView)this.findViewById(R.id.listView1);
				
		adapter = new ProductAdapter();
		
		mHandler.sendEmptyMessageDelayed(1,0);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO �Զ����ɵķ������
				Num = position;
				new Thread(){  
					   @Override  
					   public void run()  
					   {
						   
							List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

							list = dbUtil.selectPay(); 
							
							User_Name = list.get(Num).get("User_Name").toString();
							System.out.println(User_Name+ " and " +Num);
					   }  
					}.start(); 
				
				alertDialog.show();
				
			}
	    });	
		
		
		
	}

	
	Handler mHandler = new Handler(new Handler.Callback() {
		
		@Override
		public boolean handleMessage(Message msg) {
			// TODO �Զ����ɵķ������
			
			switch (msg.what) {
			case 1 :
				new MyTask().execute();
				//�ݹ飬����ѭ��ˢ��
				
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
			// TODO �Զ����ɵķ������
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

			list = dbUtil.selectPay(); 
						
			return list;
		}
		
	
		
		@Override
		protected void onPostExecute(List<HashMap<String, String>> result) {
			// TODO �Զ����ɵķ������
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
			// TODO �Զ����ɵķ������
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO �Զ����ɵķ������
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO �Զ����ɵķ������
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO �Զ����ɵķ������
			
			View view = null;
			if(convertView == null){
				view = LayoutInflater.from(CashierActivity.this).inflate(R.layout.recipelistview,null);				
			}else {
				view = convertView;
			}
			
		//	Toast.makeText(RecipeActivity.this, list.get(position).get("R_Name").toString(), 1).show();
			TextView textview1 = (TextView)view.findViewById(R.id.textView4);
			textview1.setText(list.get(position).get("User_Name").toString());
			
			
			
			TextView textview2 = (TextView)view.findViewById(R.id.textView5);
			textview2.setText(list.get(position).get("P_Amount").toString());
			
//			TextView textview3 = (TextView)view.findViewById(R.id.textView1);
//			String  cook = list.get(position).get("O_Cook").toString();
//			if (cook == "0") {
//				textview3.setText("δ���");
//			}else if (cook == "1") {
//				textview3.setText("�����");
//			}else {
//				textview3.setText("�����");
//			}
			
			return view;
		}
	}
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO �Զ����ɵķ������
	
		finish();
		mark = 2;
		
		return super.onKeyDown(keyCode, event);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cashier, menu);
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
