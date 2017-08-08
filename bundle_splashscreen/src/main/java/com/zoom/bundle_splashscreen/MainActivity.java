package com.zoom.bundle_splashscreen;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zoom.bundle_splashscreen.Split.Activity1;
import com.zoom.bundle_splashscreen.Zaker.ZakerActivity;
import com.zoom.bundle_splashscreen.viewpager.ViewPagerActivity;

/*
* 作者：ProZoom
* 时间：${DATA} - 上午8:42
* 描述：
*/
public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void zaker(View view){
        startActivity(new Intent(this, ZakerActivity.class));
        //Toast.makeText(this,"wwww",Toast.LENGTH_LONG).show();
    }

    public void viewpager(View view){
        startActivity(new Intent(this, ViewPagerActivity.class));
        //Toast.makeText(this,"wwww",Toast.LENGTH_LONG).show();
    }

    public void split(View view){
        startActivity(new Intent(this, Activity1.class));
        //Toast.makeText(this,"wwww",Toast.LENGTH_LONG).show();
    }


    public void onBack(View view){
        onBackPressed();
    }

}
