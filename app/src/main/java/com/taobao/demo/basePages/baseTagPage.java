package com.taobao.demo.basePages;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.taobao.demo.MainActivity;
import com.taobao.demo.R;

import static com.taobao.demo.R.id.tv_title;


/**
 * @author 李阳
 * @创建时间 2017/4/22 - 上午9:21
 * @描述
 * @ 当前版本:
 */

public class baseTagPage {

    public MainActivity mainActivity;
    public FrameLayout fl_main;
    private View root;
    private Toolbar toolbar;
    public TextView tv_title;


    public baseTagPage(Context context) {
        this.mainActivity = (MainActivity) context;

        initView();
        initData();
        initEvent();
    }


    public void initView() {
        root=View.inflate(mainActivity, R.layout.fragment_main_basepage,null);

        fl_main= (FrameLayout) root.findViewById(R.id.fl_main_basepage_frame);
        tv_title= (TextView) root.findViewById(R.id.tv_title);

        initActionBar();

    }
    private void initActionBar() {
        toolbar= (Toolbar) root.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        tv_title.setText("UI控件");
        mainActivity.setSupportActionBar(toolbar);
    }
    public void initData() {
    }

    public void initEvent() {
    }


    public View getRoot() {
        return root;
    }
}
