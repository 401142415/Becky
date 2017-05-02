package com.example.administrator.player.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.player.R;

/**
 * Created by Administrator on 2017/5/2.
 */

public class TitleBar extends LinearLayout implements View.OnClickListener{

    private View tv_search;
    private View rl_game;
    private View iv_record;
    private Context context;

//在代码中实例化该类使用
    public TitleBar(Context context) {
        this(context,null);
    }
//当在布局文件使用该类的时候，系统通过该构造方法实例化
    public TitleBar(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }
//设置样式的时候调用
    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }
//布局文件加载时调用该方法
    //当View中所有的子控件均被映射成xml后触发
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //获得孩子实例
        tv_search = getChildAt(1);
        rl_game = getChildAt(2);
        iv_record = getChildAt(3);

        //设置点击事件
        tv_search.setOnClickListener(this);
        rl_game.setOnClickListener(this);
        iv_record.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_search:
                Toast.makeText(context,"搜索",Toast.LENGTH_LONG).show();
                break;
            case R.id.rl_game:
                Toast.makeText(context,"游戏",Toast.LENGTH_LONG).show();
                break;
            case R.id.iv_record:
                Toast.makeText(context,"历史记录",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
