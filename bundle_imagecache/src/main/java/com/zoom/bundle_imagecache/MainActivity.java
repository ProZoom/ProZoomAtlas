package com.zoom.bundle_imagecache;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zoom.bundle_imagecache.image3cache.Image3CacheActivity;

/*
* 作者：ProZoom
* 时间：2017/7/20-上午8:02
* 描述：
*/
public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
    }


    public void one(View view){
        Intent intent=new Intent(this,Image3CacheActivity.class);
        startActivity(intent);
    }



    public void onBack(View view){
        onBackPressed();
    }
}
