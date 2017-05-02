package com.example.administrator.player.base;

import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2017/4/30.
 */

public abstract class BasePager {

    public final Context context;
    public View rootview;
    public boolean isInitDate = false;

    public BasePager(Context context){
        this.context = context;
        rootview = initView();

    }

    /**
     * 抽象类，强制由子类实现
     * @return
     */
    public abstract View initView();

    /**
     * 当子页面需要初始化数据时，如联网请求数据或绑定数据的时候
     * 重写该方法
     */
    public void initData(){

    }
}
