package com.taobao.splashscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/*
* 作者：ProZoom
* 时间：2017/7/29-下午7:46
* 描述：欢迎界面
*/
public class WelcomeActivity extends Activity {

    private View mContentView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);
        mContentView = findViewById(R.id.fullscreen_content);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mContentView.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClassName(getBaseContext(),"com.taobao.demo.MainActivity");
                startActivity(intent);
                finish();
                overridePendingTransition(-1,android.R.anim.slide_out_right);
            }
        },3000);
    }


}
