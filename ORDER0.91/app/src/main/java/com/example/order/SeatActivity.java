package com.example.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.cookie.SetCookie;
import org.apache.http.cookie.SetCookie2;

import com.example.order.OrderActivity.MyTask;

import android.R.color;
import android.R.integer;
import android.R.layout;
import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SeatActivity extends Activity {
	
	private ListView listView;
	private ProductAdapter adapter;
	private ProductAdapter2 adapter2;
	private DBUtil dbUtil;  
	private String User_Name;
	private String O_ID;
	int[] image = {
            R.drawable.blue,
            R.drawable.green,
            R.drawable.red,
            R.drawable.yellow,
    };
	
	private int  mark = 1;
	private String show = "������";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seat);
		Intent intent = getIntent();
		
		dbUtil = new DBUtil();
		
//		String User_Name = intent.getStringExtra("User_Name");
//		System.out.println("seat get User_Name----->>"+User_Name);		
		
		listView = (ListView)this.findViewById(R.id.listView1);
		
		
		
		adapter = new ProductAdapter();
		adapter2 = new ProductAdapter2();
		//��MYTASK����һ��������Ϊ��½�˽�����û����ݲ���Ҫ�õ�
		//ʮ��һ�θ������ݸ���
		
		
		final Dialog alertDialog = new AlertDialog.Builder(this)
		.setMessage( "�ò���ʹ���У��Ƿ�Ϊ�任̨��")
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() { 
                  
                 @Override 
                 public void onClick(DialogInterface dialog, int which) { 
                     // TODO Auto-generated method stub  
              	   new Thread(){  
  					   @Override  
  					   public void run()  
  					   {
  						Intent intent  = new Intent(SeatActivity.this,ChangeActivity.class);
  						System.out.println("seatactivity----->>"+User_Name);
  						intent.putExtra("User_Name", User_Name);
  						startActivity(intent);
  						finish();
  						mark = 2;
  						
  					   }  
  					}.start(); 
             // 	   Toast.makeText(CookActivity.this, "", 1).show();
              	  
                 } 
             }).setNeutralButton("�鿴����", new DialogInterface.OnClickListener() {
                 
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                  // TODO Auto-generated method stub
                	 
                	 Intent intent  = new Intent(SeatActivity.this,Ordered2Activity.class);
						System.out.println("seatactivity----->>"+User_Name);
						intent.putExtra("User_Name", User_Name);
						startActivity(intent);

                 }
                }).
             setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
          	     
          	     @Override
          	     public void onClick(DialogInterface dialog, int which) {
          	      // TODO Auto-generated method stub
          	    	
          	    	 
          	     }
          	    })
          	    .create();
		
		
		
		
		final Dialog alertDialog2 = new AlertDialog.Builder(this)
		.setMessage( "�ò�ʽ��������")
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() { 
                  
                 @Override 
                 public void onClick(DialogInterface dialog, int which) { 
                     // TODO Auto-generated method stub  
              	   new Thread(){  
  					   @Override  
  					   public void run()  
  					   {
  						dbUtil.Update_Cook(O_ID, "3");
  						
  					   }  
  					}.start(); 
             // 	   Toast.makeText(CookActivity.this, "", 1).show();
              	  
                 } 
             }).
             setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
          	     
          	     @Override
          	     public void onClick(DialogInterface dialog, int which) {
          	      // TODO Auto-generated method stub

          	     }
          	    })
          	    .create();
		
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO �Զ����ɵķ������
				dbUtil = new DBUtil();
				List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			//	String User_Type = "1";
			
				list = adapter.getdate(); 
				
				Toast.makeText(SeatActivity.this, list.get(position).get("Memo").toString().trim(), 1).show();
				
				
				return false;
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO �Զ����ɵķ������
				dbUtil = new DBUtil();
				List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			//	String User_Type = "1";
				if (show.trim().equals("������")) {
					
				list = adapter.getdate(); 

				if (list.get(position).get("Logged_in").toString().trim().equals("2")) {
					User_Name = list.get(position).get("User_Name");
					alertDialog.show();
					System.out.println("  SEATACTIVITY -------->>ʹ���С����ɲ���");
				}else {
					Intent intent  = new Intent(SeatActivity.this,BookingActivity.class);
					//		 System.out.println("list----->>"+list.get(1).get("User_Name"));
					User_Name = list.get(position).get("User_Name");
					intent.putExtra("User_Name", User_Name);
					startActivity(intent);
				}
				}else {
					
					list = adapter2.getdate(); 
					
					O_ID = list.get(position).get("O_ID");
					alertDialog2.show();
				}
				
			}
	    });	
		
		new MyTask().execute();
		
		final RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
		

		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO �Զ����ɵķ������
				
				RadioButton radioButton =(RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
				show = (String)radioButton.getText().toString().trim();
				if (show.trim().equals("������")) {
					mark =1;
					mHandler.sendEmptyMessageDelayed(mark,0);
					
				}else {
					mark = 3;
					mHandler2.sendEmptyMessageDelayed(mark,0);
					
				}
				
				
				
			}
		});
		
//		mHandler.sendEmptyMessageDelayed(1,0);
//		
		
		//new MyTask().execute();		
		
		
	}
	
	//----------------���������
	 
	
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
	
	//�����ְ��ǡ�execute������������ȣ����ؽ������
	class MyTask extends AsyncTask<String, Void, List<HashMap<String, String>>>{

		@Override
		protected List<HashMap<String, String>> doInBackground(String... params) {
			// TODO �Զ����ɵķ������
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			String User_Type = "4";
			list = dbUtil.selectAllUser(); 
			System.out.println(User_Type +"  SEATACTIVITY list.size-------->>"+  list.size());
			
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
				view = LayoutInflater.from(SeatActivity.this).inflate(R.layout.seatlistview,null);				
			}else {
				view = convertView;
			}
			TextView textview1 = (TextView)view.findViewById(R.id.textview1);
			textview1.setText(list.get(position).get("User_Name").toString().trim());
			ImageView  imageView = (ImageView)view.findViewById(R.id.imageView1);
//			imageView.setImageResource(R.drawable.yellow);
			//image,ͼƬ���顣�Ѿ���R.DRAWABLE�е���Դ��ʼ��
			String Logged_in = list.get(position).get("Logged_in").toString().trim();
//			Toast.makeText(SeatActivity.this, list.get(position).get("Logged_in").toString(), 1).show();
			switch (Logged_in) {
			case "0":
				imageView.setImageDrawable(getResources().getDrawable(image[0]));
				break;
			case "1":
				imageView.setImageDrawable(getResources().getDrawable(image[1]));
				break;
			case "2":
				imageView.setImageDrawable(getResources().getDrawable(image[2]));
				break;
			case "3":
				imageView.setImageDrawable(getResources().getDrawable(image[3]));
				break;
			default:
				break;
			}
			/*TextView textView2 = (TextView)view.findViewById(R.id.textView2);
			textView2.setText(list.get(position).get("Logged_in").toString());*/
//			EditText edittext2 = (EditText)view.findViewById(R.id.edittext2);
//			edittext2.setText(list.get(position).get("Note").toString());
			TextView textview2 = (TextView)view.findViewById(R.id.textview2);
			textview2.setText(list.get(position).get("Note").toString().trim());
			return view;
		}
		
		
	}
	
	
	
	//---------------------�Ͳͱ����
	
	Handler mHandler2 = new Handler(new Handler.Callback() {
		
		@Override
		public boolean handleMessage(Message msg) {
			// TODO �Զ����ɵķ������
			
			switch (msg.what) {
			case 3 :
				new MyTask2().execute();
				//�ݹ飬����ѭ��ˢ��
				
				mHandler2.sendEmptyMessageDelayed(mark,10000);
			
			//	Toast.makeText(SeatActivity.this, "liseview", 1).show();
				break;

			default:
				break;
			}
			return false;
		}
	});
	
	//�����ְ��ǡ�execute������������ȣ����ؽ������
	class MyTask2 extends AsyncTask<String, Void, List<HashMap<String, String>>>{

		@Override
		protected List<HashMap<String, String>> doInBackground(String... params) {
			// TODO �Զ����ɵķ������
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			
			list = dbUtil.selectCooked(); 
			
			
			return list;
		}
		
	
		
		@Override
		protected void onPostExecute(List<HashMap<String, String>> result) {
			// TODO �Զ����ɵķ������
			super.onPostExecute(result);
			adapter2.setDate(result);
			listView.setAdapter(adapter2);
			
			adapter2.notifyDataSetChanged();
			
		}
		
	}
	
	

	
	public class ProductAdapter2 extends BaseAdapter{

		
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
				view = LayoutInflater.from(SeatActivity.this).inflate(R.layout.cooklistview,null);				
			}else {
				view = convertView;
			}
			TextView textview4 = (TextView)view.findViewById(R.id.textView4);
			textview4.setText(list.get(position).get("R_Name").toString().trim());

			TextView textview5 = (TextView)view.findViewById(R.id.textView5);
			textview5.setText(list.get(position).get("User_Name").toString().trim());
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
		getMenuInflater().inflate(R.menu.seat, menu);
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
