package com.taobao.demo.basePages;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.taobao.demo.R;
import com.taobao.demo.activity.AndroidQuestionActivity;
import com.taobao.demo.activity.H5QuestionActivity;
import com.taobao.demo.activity.JavaQuestionActivity;
import com.taobao.demo.activity.kotlinQuestionActivity;

/**
 * @author 李阳
 * @创建时间 2017/4/22 - 上午10:41
 * @描述
 * @ 当前版本:
 */

public class FunctionBaseTagPage extends baseTagPage implements View.OnClickListener{

    private static ImageView img_java;
    private static ImageView img_android;
    private static ImageView img_kotlin;
    private static ImageView img_html;
    private RelativeLayout btn_java,btn_android,btn_kotlin,btn_html;
    private TextView stv_java,stv_android,stv_kotlin,stv_html;


    public FunctionBaseTagPage(Context context) {
        super(context);
    }


    @Override
    public void initView() {
        super.initView();
        View view=View.inflate(mainActivity, R.layout.fragment_main_function, null);
        fl_main.addView(view);

        img_java= (ImageView) view.findViewById(R.id.img_java);
        img_android=(ImageView) view.findViewById(R.id.img_android);
        img_kotlin= (ImageView) view.findViewById(R.id.img_kotlin);
        img_html= (ImageView) view.findViewById(R.id.img_html);

        btn_java= (RelativeLayout) view.findViewById(R.id.btn_java);
        btn_kotlin= (RelativeLayout) view.findViewById(R.id.btn_kotlin);
        btn_android= (RelativeLayout) view.findViewById(R.id.btn_android);
        btn_html= (RelativeLayout) view.findViewById(R.id.btn_html);

        stv_java= (TextView) view.findViewById(R.id.stv_java);
        stv_kotlin= (TextView) view.findViewById(R.id.stv_kotlin);
        stv_android= (TextView) view.findViewById(R.id.stv_android);
        stv_html= (TextView) view.findViewById(R.id.stv_html);

        /*stv_java.setAdjuster(new MoveEffectAdjuster()).setAutoAdjust(true).startAnim();
        stv_kotlin.setAdjuster(new MoveEffectAdjuster()).setAutoAdjust(true).startAnim();
        stv_android.setAdjuster(new MoveEffectAdjuster()).setAutoAdjust(true).startAnim();
        stv_html.setAdjuster(new MoveEffectAdjuster()).setAutoAdjust(true).startAnim();*/
    }

    @Override
    public void initData() {
        super.initData();
        tv_title.setText("IT面试");


    }

    @Override
    public void initEvent() {
        super.initEvent();

        btn_java.setOnClickListener(this);
        btn_kotlin.setOnClickListener(this);
        btn_android.setOnClickListener(this);
        btn_html.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_java:
                mainActivity.startActivity(new Intent(mainActivity,JavaQuestionActivity.class));
                break;
            case R.id.btn_kotlin:
                mainActivity.startActivity(new Intent(mainActivity,kotlinQuestionActivity.class));
                break;
            case R.id.btn_android:
                mainActivity.startActivity(new Intent(mainActivity,AndroidQuestionActivity.class));
                break;
            case R.id.btn_html:
                mainActivity.startActivity(new Intent(mainActivity,H5QuestionActivity.class));
                break;
        }
    }

    /**
     * 启动动画
     *
     * @param imageView
     */
    public static void startAnimator(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

    public static void initAnimation(){
        startAnimator(img_android);
        startAnimator(img_kotlin);
        startAnimator(img_java);
        startAnimator(img_html);
    }

}
