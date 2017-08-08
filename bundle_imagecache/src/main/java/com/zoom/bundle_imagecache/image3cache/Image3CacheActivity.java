package com.zoom.bundle_imagecache.image3cache;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.GridView;

import com.zoom.bundle_imagecache.R;
import com.zoom.bundle_imagecache.image3cache.adapter.ImageAdapter;
import com.zoom.bundle_imagecache.image3cache.image3chche.ImageCache;

/**
 * @author 李阳
 * @创建时间 2017/5/11 - 上午8:24
 * @描述
        什么是图片的三级缓存
        1、内存缓存 优先加载，速度最快
        2、本地缓存 次优先加载 速度稍快
        3、网络缓存 最后加载 速度由网络速度决定（浪费流量）
 * @ 当前版本:
 */

public class Image3CacheActivity extends Activity {


    private GridView gv_photos;

    //GridView的适配器
    private ImageAdapter mAdapter;

    private int mImageThumbSize;

    private int mImageThumbSpacing;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
        initListenerEvent();
    }

    public void initView() {
        setContentView(R.layout.activity_image3cache);

        gv_photos= (GridView) findViewById(R.id.gv_photos);

    }



    public void initData() {

        mImageThumbSize = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);

        mImageThumbSpacing = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_spacing);

        mAdapter = new ImageAdapter(this, 0, ImageResource.imageThumbUrls, gv_photos);

        gv_photos.setAdapter(mAdapter);
    }


    public void initListenerEvent() {
        //
        gv_photos.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {

                final int numColumns = (int) Math.floor(gv_photos.getWidth() / (mImageThumbSize + mImageThumbSpacing));
                Log.i("TAG","numColumns------>"+numColumns);
                if (numColumns > 0) {
                    int columnWidth = (gv_photos.getWidth() / numColumns) - mImageThumbSpacing;

                    mAdapter.setItemHeight(columnWidth);

                    gv_photos.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageCache.getInstanse(this).onDestroy();
    }


    public void onBack(View view){
        onBackPressed();
    }
}
