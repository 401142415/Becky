package com.example.administrator.testapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/4/17.
 */

public class MyFragment extends Fragment{
    private  String content;
    public MyFragment(String content){
        this.content = content;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflater类似findbyid，前者用于加载xml布局，后者用于加载控件
       View view =inflater.inflate(R.layout.fg_content,container,false);
        TextView txt_content = (TextView)view.findViewById(R.id.txt_content);
        //加载fg_content.xml布局，获取文本框ID。
        txt_content.setText(content);
        return view;
    }
}
