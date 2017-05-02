package com.example.administrator.fragment_radiobutton;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/4/18.
 */

public class MyFragment1 extends android.support.v4.app.Fragment{

    public MyFragment1(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fg_content,container,false);
        TextView txt_content = (TextView)view.findViewById(R.id.txt_content);
        txt_content.setText("first Fragment");
        txt_content.setBackgroundColor(Color.parseColor("#DC143C"));

        return view;
    }
}
