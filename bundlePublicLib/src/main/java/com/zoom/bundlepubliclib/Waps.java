package com.zoom.bundlepubliclib;
/*
* 作者：ProZoom
* 时间：2017/7/16-上午11:28
* 描述：
*/

import android.content.Context;
import android.util.Log;

import cn.waps.AppConnect;
import cn.waps.AppListener;

public class Waps {

    private final String WAPSAPPID="a9828012c221edaea62dcd4642f10ec0";

    public Waps(Context context) {
        AppConnect.getInstance(WAPSAPPID,"waps",context);
        AppConnect.getInstance(context).initPopAd(context);


    }


    /*
    * 描述：插屏广告
    * @param
    * @return
    */
    public static void screenAds(Context context){
        AppConnect.getInstance(context).setPopAdNoDataListener(new AppListener() {
            @Override
            public void onPopNoData() {
                Log.i("debug", "插屏广告暂无可用数据");
            }
        });
        // 显示插屏广告
        AppConnect.getInstance(context).showPopAd(context);
    }


}
