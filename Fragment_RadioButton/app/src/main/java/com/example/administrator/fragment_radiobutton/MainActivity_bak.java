/*
package com.example.administrator.fragment_radiobutton;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity_bak extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rg_tab_bar;
    private RadioButton rb_discover;

    private MyFragment fg1,fg2,fg3;
    private android.app.FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fManager = getFragmentManager();
        rg_tab_bar = (RadioGroup)findViewById(R.id.rg_tab_bar);
        rg_tab_bar.setOnCheckedChangeListener(this);

        rb_discover = (RadioButton)findViewById(R.id.rb_discover);
        rb_discover.setChecked(true);

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (checkedId){
            case R.id.rb_discover:
                if(fg1 == null){
                    fg1 = new MyFragment("第一个Fragment");
                    fTransaction.add(R.id.ly_content,fg1);
                }else {
                    fTransaction.show(fg1);
                }
                break;
            case R.id.rb_news:
                if(fg2 == null){
                    fg2 = new MyFragment("第2个Fragment");
                    fTransaction.add(R.id.ly_content,fg2);
                }else {
                    fTransaction.show(fg2);
                }
                break;
            case R.id.rb_about_me:
                if(fg3 == null){
                    fg3 = new MyFragment("第3个Fragment");
                    fTransaction.add(R.id.ly_content,fg3);
                }else {
                    fTransaction.show(fg3);
                }
                break;
        }
        fTransaction.commit();
    }

    //隐藏所有Fragment
    private  void  hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1!=null)fragmentTransaction.hide(fg1);
        if(fg2!=null)fragmentTransaction.hide(fg2);
        if(fg3!=null)fragmentTransaction.hide(fg3);
    }

}
*/
