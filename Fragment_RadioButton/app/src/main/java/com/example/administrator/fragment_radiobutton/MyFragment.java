/*
package com.example.administrator.fragment_radiobutton;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

*/
/**
 * Created by Administrator on 2017/4/17.
 *//*

@SuppressLint("ValidFragment")
public class MyFragment extends Fragment {

    private String content;
    public MyFragment(String content) {
        this.content = content;
        Log.i("BECKY","MyFragment is "+ content.toString());
    }

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
*/
