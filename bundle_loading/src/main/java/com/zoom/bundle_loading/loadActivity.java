package com.zoom.bundle_loading;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zoom.bundle_loading.ProgressBar.ProgressBar_Color;
import com.zoom.bundle_loading.loadingDialog.LoadingDialog;

/*
* 作者：ProZoom
* 时间：2017/7/23 - 下午3:06
* 描述：
*/
public class loadActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void loadingDialog(View view){
        LoadingDialog dialog;
        dialog = new LoadingDialog(this);
        dialog.show();
    }

    public void LoadingProgressBar(View view){

        startActivity(new Intent(this, ProgressBar_Color.class));
    }
}
