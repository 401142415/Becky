package com.example.order;

import java.util.ArrayList;
 




import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
 
public class GuideActivity extends Activity {
	
	private DBUtil dbUtil;
    private ViewPager viewPager;
     
    /**װ��ҳ��ʾ��view������*/
    private ArrayList<View> pageViews;    
    private ImageView imageView;
     
    /**��СԲ���ͼƬ�������ʾ*/
    private ImageView[] imageViews;
     
    //��������ͼƬ��LinearLayout
    private ViewGroup viewPics;
     
    //����СԲ���LinearLayout
    private ViewGroup viewPoints;
     
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         

		dbUtil = new DBUtil();
        //��Ҫ��ҳ��ʾ��Viewװ��������
        LayoutInflater inflater = getLayoutInflater();
        pageViews = new ArrayList<View>();
        pageViews.add(inflater.inflate(R.layout.viewpager_page1, null));
        pageViews.add(inflater.inflate(R.layout.viewpager_page2, null));
         
        //����imageviews���飬��С��Ҫ��ʾ��ͼƬ������
        imageViews = new ImageView[pageViews.size()];
        
        System.out.println("GuideActivity------->>"+ pageViews.size());
        //��ָ����XML�ļ�������ͼ
        viewPics = (ViewGroup)inflater.inflate(R.layout.activity_guide,null);
         
        //ʵ����СԲ���linearLayout��viewpager
        viewPoints = (ViewGroup)viewPics.findViewById(R.id.viewGroup);
        viewPager = (ViewPager)viewPics.findViewById(R.id.guidePages);
         
        if (viewPoints == null) {
			System.out.println("GuideActivity---null--->>");
		}
        
        //���СԲ���ͼƬ
        for(int i=0;i<pageViews.size();i++){
            imageView = new ImageView(GuideActivity.this);
            //����СԲ��imageview�Ĳ���
            imageView.setLayoutParams(new LayoutParams(20,20));//����һ����߾�Ϊ20 �Ĳ���
            imageView.setPadding(20, 0, 20, 0);
            //��СԲ��layout��ӵ�������
            imageViews[i] = imageView;
             
            //Ĭ��ѡ�е��ǵ�һ��ͼƬ����ʱ��һ��СԲ����ѡ��״̬����������
            if(i==0){
            	System.out.println("GuideActivity----i--->>"+ i);
                imageViews[i].setBackgroundResource(R.drawable.guide1);
            }else{
                imageViews[i].setBackgroundResource(R.drawable.guide2);
            }
             
            //��imageviews��ӵ�СԲ����ͼ��
            
           
            viewPoints.addView(imageViews[i]);
        }
         
        //��ʾ����ͼƬ����ͼ
        setContentView(viewPics);
         
        //����viewpager���������ͼ����¼�
        viewPager.setAdapter(new GuidePageAdapter());
        viewPager.setOnPageChangeListener(new GuidePageChangeListener());        
    }
     
    private Button.OnClickListener  Button_OnClickListener = new Button.OnClickListener() {
        public void onClick(View v) {
            //�����Ѿ�����
            setGuided();
            dbUtil = new DBUtil();
            //��ת
            new Thread(){  
				   @Override  
				   public void run()  
				   {
					   dbUtil = new DBUtil();
					   List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
//					   list = adapter.getdate(); 				
					   //���������������Ա�û�������ת���ù���Ա
					   list = dbUtil.selectKindsofPersonnel("4");  
					   if (list.size() == 0 ) {
						   Intent nIntent = new Intent();
				           nIntent.setClass(GuideActivity.this, SetAdminActivity.class);
				           GuideActivity.this.startActivity(nIntent);
				           GuideActivity.this.finish();
					}else {
						Intent mIntent = new Intent();
			            mIntent.setClass(GuideActivity.this, AllTableActivity.class);
			            GuideActivity.this.startActivity(mIntent);
			            GuideActivity.this.finish();
					}
					  
				   }  
				}.start(); 
            
            
        }
    }; 
     
    private static final String SHAREDPREFERENCES_NAME = "my_pref";
    private static final String KEY_GUIDE_ACTIVITY = "guide_activity";
    private void setGuided(){
        SharedPreferences settings = getSharedPreferences(SHAREDPREFERENCES_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_GUIDE_ACTIVITY, "false");
        editor.commit();
    }
     
     
    class GuidePageAdapter extends PagerAdapter{
 
        //����positionλ�õĽ���
        @Override
        public void destroyItem(View v, int position, Object arg2) {
            // TODO Auto-generated method stub
            ((ViewPager)v).removeView(pageViews.get(position));
             
        }
 
        @Override
        public void finishUpdate(View arg0) {
            // TODO Auto-generated method stub
             
        }
         
        //��ȡ��ǰ���������
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return pageViews.size();
        }
 
        //��ʼ��positionλ�õĽ���
        @Override
        public Object instantiateItem(View v, int position) {
            // TODO Auto-generated method stub
            ((ViewPager) v).addView(pageViews.get(position));  
             
             // ����ҳ��1�ڵİ�ť�¼�  
            if (position == 1) {  
                Button btn = (Button) v.findViewById(R.id.btn_close_guide);  
                btn.setOnClickListener(Button_OnClickListener);  
            }  
             
            return pageViews.get(position);  
        }
 
        // �ж��Ƿ��ɶ������ɽ���
        @Override
        public boolean isViewFromObject(View v, Object arg1) {
            // TODO Auto-generated method stub
            return v == arg1;
        }
 
 
 
        @Override
        public void startUpdate(View arg0) {
            // TODO Auto-generated method stub
             
        }
 
        @Override
        public int getItemPosition(Object object) {
            // TODO Auto-generated method stub
            return super.getItemPosition(object);
        }
 
        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // TODO Auto-generated method stub
             
        }
 
        @Override
        public Parcelable saveState() {
            // TODO Auto-generated method stub
            return null;
        }
    }
     
     
    class GuidePageChangeListener implements OnPageChangeListener{
 
        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
             
        }
 
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
             
        }
 
        @Override
        public void onPageSelected(int position) {
            // TODO Auto-generated method stub
            for(int i=0;i<imageViews.length;i++){
                imageViews[position].setBackgroundResource(R.drawable.guide1);
                //���ǵ�ǰѡ�е�page����СԲ������Ϊδѡ�е�״̬
                if(position !=i){
                    imageViews[i].setBackgroundResource(R.drawable.guide2);
                }
            }
             
        }
    }   
 }