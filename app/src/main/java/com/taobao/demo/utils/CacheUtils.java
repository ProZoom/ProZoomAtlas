package com.taobao.demo.utils;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
* 作者：ProZoom
* 时间：2017/7/24-上午11:37
* 描述：缓存工具类,注意：这种缓存方法，在使用一些手机垃圾清理软件时，会将缓存数据清除！
*/
public class CacheUtils {

    private static final String TAG = "erro";


    /*
    * 描述：缓存数据
    * @param [obj, path]
    * @return void
    */
    public static void save(Object obj, String path) {
        try {
            File f = new File(path);
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            Log.i(TAG, "save: " + e.toString());
        }
    }


    /*
    * 描述：读取缓存的数据
    * @param [path]
    * @return java.lang.Object
    */
    public static Object load(String path) {
        Object obj = null;
        File file = new File(path);
        try {
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                try {
                    obj = ois.readObject();
                } catch (ClassNotFoundException e) {
                }
                ois.close();
            }
        } catch (IOException e) {
            Log.i(TAG, "save: " + e.toString());
        }
        return obj;
    }
}
