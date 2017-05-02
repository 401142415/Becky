package com.example.administrator.player.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.example.administrator.player.R;

public class SplashActivity extends Activity {

    private  static final String TAG = SplashActivity.class.getSimpleName();
    private  Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
       handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //延时两秒后执行（200）
                //在主线程中执行
                startMainActivity();
                Log.i(TAG,"当前线程名字"+Thread.currentThread().getName());
            }
        },2000);
    }
    private  void startMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG,"onTouchEvent"+event.getAction());
        startMainActivity();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        //销毁activity时销毁handler
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
