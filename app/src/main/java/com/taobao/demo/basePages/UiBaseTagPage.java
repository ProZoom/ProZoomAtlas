package com.taobao.demo.basePages;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.taobao.demo.R;

import static com.taobao.demo.utils.Contants.uiItemTitle;


/**
 * @author 李阳
 * @创建时间 2017/4/22 - 上午10:41
 * @描述
 * @ 当前版本:
 */

public class UiBaseTagPage extends baseTagPage implements AdapterView.OnItemClickListener {


    private ListView lv_ui;


    public UiBaseTagPage(Context context) {
        super(context);
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void initData() {
        super.initData();
        tv_title.setText("UI控件");

        View view=View.inflate(mainActivity, R.layout.fragment_main_ui, null);
        fl_main.addView(view);
        lv_ui= (ListView) view.findViewById(R.id.lv_ui);

        //装备数据
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                mainActivity,
                R.layout.list_item,
                uiItemTitle);
        lv_ui.setAdapter(adapter);

    }

    @Override
    public void initEvent() {
        super.initEvent();
        lv_ui.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {





        switch (position){
            case 0://Animation动画效果
                switchToActivity("com.zoom.bundle_animation.AnimationActivity");
                //Toast.makeText(mainActivity,"c测试",Toast.LENGTH_SHORT).show();
                break;
            case 1://Splash引导动画
                switchToActivity("com.zoom.bundle_splashscreen.MainActivity");
                break;
            case 2: //TextView特效
                switchToActivity("com.zoom.bundle_textview.SurperTextViewActivity");
                //Toast.makeText(mainActivity,"c测试",Toast.LENGTH_SHORT).show();
                break;
            case 3://动态权限申请
                switchToActivity("com.zoom.bundle_permission.PermissionActivity");
                break;

            case 5: //图片缓存
                switchToActivity("com.zoom.bundle_imagecache.MainActivity");
                //switchToActivity("com.zoom.bundle_imagecache.image3cache.Image3CacheActivity");
                break;
            case 6: //加载动画
                switchToActivity("com.zoom.bundle_loading.loadActivity");
                //switchToActivity("com.zoom.bundle_imagecache.image3cache.Image3CacheActivity");
                break;
            case 7: //标签云
                switchToActivity("com.zoom.bundle_tagcloud.SearchFlyActivity");
                //switchToActivity("com.zoom.bundle_imagecache.image3cache.Image3CacheActivity");
                break;
        }
    }

    public void switchToActivity(String activityName){
        Intent intent = new Intent();
        intent.setClassName(mainActivity.getBaseContext(),activityName);
        mainActivity.startActivity(intent);
    }
}
