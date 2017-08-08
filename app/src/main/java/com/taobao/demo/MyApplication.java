package com.taobao.demo;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.stat.StatConfig;

import cn.bmob.v3.Bmob;


/**
 * @author 李阳
 * @创建时间 2017/4/21 - 上午11:43
 * @描述
 * @ 当前版本:
 */

public class MyApplication extends Application {

    final private String BuglyAPPID="0d9b8cbbdc";
    final private String BmobAPPID="ab4709ce59076a48fbdfebb64f9f9c8e";

    final private String QQAPPID="1106290418";
    final private String QQAPPKEY="xZa8DJzBvrDBgf9c";

    @Override
    public void onCreate() {
        super.onCreate();
       // ShareSDK.initSDK(this,ShareSDKAPPID);

        //第一：默认初始化
        Bmob.initialize(this, BmobAPPID);

        CrashReport.initCrashReport(this,BuglyAPPID,true);


        StatConfig.setAppKey(this, "Aqc" + QQAPPKEY);

    }
}
