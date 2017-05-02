package com.example.administrator.player.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.player.base.BasePager;

/**
 * Created by Administrator on 2017/4/30.
 */

public class NetAudioPager extends BasePager{
    private TextView textView;
    public NetAudioPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        textView = new TextView(context);
        textView.setTextSize(25);
        textView.setTextColor(Color.rgb(0,0,128));
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("网络音乐数据设置");
    }
}
