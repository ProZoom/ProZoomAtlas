package com.taobao.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.taobao.demo.R;
import com.taobao.demo.bean.userBean;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by liyang on 2017/7/14.
 */

public class feedbackActivity extends Activity implements View.OnClickListener {

    private EditText et_feedback,et_phone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        initView();

        initHeadBar();
    }

    private void initView() {
        et_feedback= (EditText) findViewById(R.id.et_feedback);
        et_phone= (EditText) findViewById(R.id.tv_phone);

        et_feedback.setOnClickListener(this);
        et_phone.setOnClickListener(this);
    }

    private void initHeadBar() {
        ImageView iv_back= (ImageView) findViewById(R.id.iv_feedback_back);
        ImageView iv_add= (ImageView) findViewById(R.id.iv_add);
        Button btn_commit= (Button) findViewById(R.id.btn_commit);

        iv_back.setOnClickListener(this);
        iv_add.setOnClickListener(this);
        btn_commit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_feedback_back:
                finish();
                onBackPressed();
                break;
            case R.id.iv_add:
                Toast.makeText(this,"尚未支持，敬请期待",Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_commit:
                //Toast.makeText(this,"提交的内容："+et_feedback.getText(),Toast.LENGTH_SHORT).show();

                bombInsertData();
                break;
        }
    }

    private void bombInsertData() {
        userBean p = new userBean();
        p.setPhoneNumber(et_phone.getText().toString());
        p.setAnnex("附件");
        p.setFeedback(et_feedback.getText().toString());
        p.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    Toast.makeText(feedbackActivity.this,"添加数据成功",Toast.LENGTH_LONG).show();
                    //Toast.makeText(feedbackActivity.this,"添加数据成功，返回objectId为："+objectId,Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(feedbackActivity.this,"创建数据失败：" + e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
