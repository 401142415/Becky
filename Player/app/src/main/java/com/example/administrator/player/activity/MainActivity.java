package com.example.administrator.player.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.player.R;
import com.example.administrator.player.base.BasePager;
import com.example.administrator.player.pager.AudioPager;
import com.example.administrator.player.pager.NetAudioPager;
import com.example.administrator.player.pager.NetVideoPager;
import com.example.administrator.player.pager.VideoPager;

import java.util.ArrayList;
import java.util.BitSet;

/**
 * Created by Administrator on 2017/4/29.
 */
public class MainActivity extends FragmentActivity {

    private FrameLayout fl_main_content;
    private RadioGroup rg_bottom_tag;
    private ArrayList<BasePager> basePagers;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fl_main_content = (FrameLayout) findViewById(R.id.fl_main_content);
        rg_bottom_tag= (RadioGroup) findViewById(R.id.rg_bottom_tag);

        basePagers = new ArrayList<>();
        //添加本地视频页面
        basePagers.add(new VideoPager(this));
        basePagers.add(new AudioPager(this));
        basePagers.add(new NetVideoPager(this));
        basePagers.add(new NetAudioPager(this));
        //RadioGroup监听
        rg_bottom_tag.setOnCheckedChangeListener(new MyOnCheckedChangeListener());

        rg_bottom_tag.check(R.id.rb_video);
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                default:
                    position = 0;
                    break;
                case  R.id.rb_audio:
                    position = 1;
                    break;
                case R.id.rb_net_video:
                    position = 2;
                    break;
                case R.id.rb_net_audio:
                    position = 3;
                    break;

            }
            setFragment();
        }
    }
//将页面添加到fragmengt中
    private void setFragment(){
        //获得FragmentManager
        FragmentManager manager = getSupportFragmentManager();
        //开启事务
        android.support.v4.app.FragmentTransaction ft = manager.beginTransaction();

        ft.replace(R.id.fl_main_content,new ReplaceFragment(getBasePager()));
        //提交事务
        ft.commit();
    }

    public static class ReplaceFragment extends Fragment {
        private BasePager currPager;
        public ReplaceFragment(BasePager pager) {
            this.currPager=pager;
        }
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return currPager.rootview;
        }
    }

    /**
     * 根据位置得到对应页面
     * @return
     */
    private  BasePager getBasePager(){
        BasePager basePager = basePagers.get(position);
        //isInitDate若曾经加载过，不再次加载
        if(basePager != null && !basePager.isInitDate){
            basePager.initData();//联网请求等操作可在此处实现
            basePager.isInitDate = true;
        }
        return basePager;
    }
}

