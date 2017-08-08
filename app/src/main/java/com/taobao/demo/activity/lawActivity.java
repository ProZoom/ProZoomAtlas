package com.taobao.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.taobao.demo.R;

/**
 * Created by liyang on 2017/7/16.
 */

public class lawActivity extends Activity implements View.OnClickListener {


    private ImageView iv_law_back;
    private TextView tv_law;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law);

        initView();
        initData();
    }

    private void initData() {
        tv_law.setText(getResources().getText(R.string.law));
        tv_law.setMovementMethod(ScrollingMovementMethod.getInstance());

    }

    private void initView() {
        iv_law_back= (ImageView) findViewById(R.id.iv_law_back);
        tv_law= (TextView) findViewById(R.id.tv_law);

        iv_law_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_law_back:
                onBackPressed();
                break;
        }
    }
}
