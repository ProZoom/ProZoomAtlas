package com.taobao.demo.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.WindowManager;

import java.io.File;
import java.util.ArrayList;

public class AppUtils {

    /**
     * 获取VersionName
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        try {
            String pkName = context.getPackageName();
            return context.getPackageManager().getPackageInfo(pkName, 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据包名判断手机上是否安装了此app
     * @param context
     * @param packageName
     * @return
     */
    public static final boolean isApkInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 判断网络是否有效.
     * @param context the context
     * @return true, if is network available
     */
    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }



    /**
     * 获取屏幕尺寸
     */
    public static int[] getScreen(Context context){
        int[] screen=new int[]{};
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        Log.i("ScreenTAG","ScreenWidth---> "+width+"\nScreenHeight---> "+height);
        screen[0]=width;
        screen[1]=height;
        return screen;
    }


    // 根据手机的分辨率从 dip 的单位 转成为 px(像素)
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    //根据手机的分辨率从 px(像素) 的单位 转成为 dp

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static String fromMilliToSecond(int duration) {

        // 60000 ==> 60s
        // 1分钟 -》 60毫秒
        int minute = duration/60000;
        int sec = (duration - minute*60000)/1000;
        return (minute<10?"0":"")+minute+":"+(sec<10?"0":"")+sec;
    }

    /**
     * 分享多张图片
     */
    private void shareMoreImage(Context context,ArrayList image) {

        String path= Environment.getExternalStorageDirectory()+ File.separator;


        Intent shareIntent=new Intent();
        shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
        shareIntent.putExtra(Intent.EXTRA_STREAM,image);
        shareIntent.setType("image/*");
        context.startActivity(Intent.createChooser(shareIntent,"分享到"));

    }
    /**
     * 分享一张图片
     */
    private void shareOneImage(Context context,String imagepath) {


        //由文件得到uri
        Uri imageurl=Uri.fromFile(new File(imagepath));

        Intent shareIntent=new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM,imageurl);
        shareIntent.setType("image/*");
        context.startActivity(Intent.createChooser(shareIntent,"分享到"));

    }
    /**
     * 分享一段文字
     */
    private void shareText(Context context,String msg) {

        Intent shareIntent=new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT,msg);
        shareIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(shareIntent,"分享到"));
    }

}
