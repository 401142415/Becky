package com.example.administrator.testapplication;

//import android.support.v4.app.FragmentManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView txt_topbar;
    private TextView txt_discover;
    private TextView txt_news;
    private TextView txt_about_me;
    private FrameLayout ly_content;

    private MyFragment fg1,fg2,fg3;
    private FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fManager = getFragmentManager();
        bindViews();
        txt_discover.performClick();//模拟点击一次
    }

    //UI初始化及事件绑定
    private void bindViews(){
        txt_topbar = (TextView)findViewById(R.id.txt_topbar);
        txt_discover = (TextView)findViewById(R.id.txt_discover);
        txt_news = (TextView)findViewById(R.id.txt_news);
        txt_about_me = (TextView)findViewById(R.id.txt_about_me);
        ly_content = (FrameLayout)findViewById(R.id.ly_content);

        txt_discover.setOnClickListener(this);
        txt_news.setOnClickListener(this);
        txt_about_me.setOnClickListener(this);
    }
    //重置所有文本未选中
    private void setSelected(){
        txt_discover.setSelected(false);
        txt_news.setSelected(false);
        txt_about_me.setSelected(false);
    }
    //隐藏所有Fragment
    private  void  hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1!=null)fragmentTransaction.hide(fg1);
        if(fg2!=null)fragmentTransaction.hide(fg2);
        if(fg3!=null)fragmentTransaction.hide(fg3);

    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch ((v.getId())){
            case R.id.txt_discover:
                setSelected();
                txt_discover.setSelected(true);
                if(fg1==null){
                    fg1=new MyFragment("这是第1个Fragment");
                    fTransaction.add(R.id.ly_content,fg1);
                }else{
                    fTransaction.show(fg1);
                }
                break;
            case R.id.txt_news:
                setSelected();
                txt_news.setSelected(true);
                if(fg2==null){
                    fg2=new MyFragment("这是第二个Fragment");
                    fTransaction.add(R.id.ly_content,fg2);
                }else{
                    fTransaction.show(fg2);
                }
                break;
            case R.id.txt_about_me:
                setSelected();
                txt_about_me.setSelected(true);
                if(fg3==null){
                    fg3=new MyFragment("这是第三个Fragment");
                    fTransaction.add(R.id.ly_content,fg3);
                }else{
                    fTransaction.show(fg3);
                }
                break;
        }
        //最后必须调用
        fTransaction.commit();
    }
}
