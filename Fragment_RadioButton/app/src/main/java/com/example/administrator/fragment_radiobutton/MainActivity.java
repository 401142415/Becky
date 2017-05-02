package com.example.administrator.fragment_radiobutton;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/4/18.
 */

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,ViewPager.OnPageChangeListener{

    private TextView txt_top_bar;
    private RadioGroup rg_tab_bar;
    private RadioButton rb_discover;
    private RadioButton rb_news;
    private RadioButton rb_about_me;
    private ViewPager vpager;

    private MyFragmentPagerAdapter mAdapter;

    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //    mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        bindViews();
        rb_discover.setChecked(true);

    }

    private void bindViews() {
        txt_top_bar = (TextView) findViewById(R.id.txt_top_bar);
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rb_discover = (RadioButton) findViewById(R.id.rb_discover);
        rb_news = (RadioButton) findViewById(R.id.rb_news);
        rb_about_me = (RadioButton) findViewById(R.id.rb_about_me);
        rg_tab_bar.setOnCheckedChangeListener(this);

        vpager = (ViewPager) findViewById(R.id.vpager);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        vpager.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_discover:
                vpager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.rb_news:
                vpager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.rb_about_me:
                vpager.setCurrentItem(PAGE_THREE);
                break;
        }
    }

    //重写ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (vpager.getCurrentItem()) {
                case PAGE_ONE:
                    rb_discover.setChecked(true);
                    break;
                case PAGE_TWO:
                    rb_news.setChecked(true);
                    break;
                case PAGE_THREE:
                    rb_about_me.setChecked(true);
                    break;

            }
        }
    }
}
