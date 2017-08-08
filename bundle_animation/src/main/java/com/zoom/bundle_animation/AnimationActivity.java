package com.zoom.bundle_animation;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 作者：ProZoom
* 时间：2017/7/18-下午11:41
* 描述：
*/
public class AnimationActivity extends Activity {

    private GridView gview;
    private List<Map<String, Object>> data_list;

    private SimpleAdapter sim_adapter;

    private String[] iconName = { "简单动画" ,"复杂动画","动画框架"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        gview = (GridView) findViewById(R.id.gv_animation);
        data_list = new ArrayList<Map<String, Object>>();
        getData();
        String [] from ={"text"};
        int [] to = {R.id.tv_gridview};
        sim_adapter = new SimpleAdapter(this, data_list, R.layout.item, from, to);
        gview.setAdapter(sim_adapter);


    }
    public List<Map<String, Object>> getData(){
        //cion和iconName的长度是相同的，这里任选其一都可以
        for(int i=0;i<iconName.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            //map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }

        return data_list;
    }
    public void onBack(View v){
        onBackPressed();
    }
}
