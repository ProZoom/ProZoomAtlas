package com.taobao.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.taobao.demo.R;
import com.taobao.demo.bean.AndroidQuestionBean;
import com.taobao.demo.utils.CacheUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/*
* 作者：ProZoom
* 时间：2017/7/23-上午9:42
* 描述：
*/

public class AndroidQuestionActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int UPDATE_ITEM = 0;
    private static final String ANDROID_BASE_DIR = '/' + "androidBaseListCache";//缓存路径
    private String CACHE_DIR = null;//默认缓存目录

    boolean isSee=false;

    private ImageView iv_back,iv_setting,iv_cellection;
    private TextView tv_title,tv_sum,tv_see,tv_anwser;

    private ViewPager vp_question;

    private List<View> viewList=new ArrayList<>();
    viewpagerAdapter ad;

    /*private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case UPDATE_ITEM:
                    Log.i("TAG","测试Handler");
                    break;
            }

        }
    };*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        //缓存目录
        CACHE_DIR = this.getCacheDir().getPath();
        initView();

        initHeadBar();
        initBottomBar();

        getData();

        initData();
    }

    private void initData() {
        tv_title.setText("Android面试题");

        ad = new viewpagerAdapter();


       vp_question.setAdapter(ad);

    }

    private void initView() {
        vp_question=(ViewPager) findViewById(R.id.vp_question);
        tv_sum=(TextView) findViewById(R.id.tv_sum);
        tv_title=(TextView) findViewById(R.id.tv_title);

    }


    private void initHeadBar() {
        iv_back= (ImageView) findViewById(R.id.iv_back);
        iv_setting= (ImageView) findViewById(R.id.iv_setting);
        tv_title= (TextView) findViewById(R.id.tv_title);

        iv_back.setOnClickListener(this);
        iv_setting.setOnClickListener(this);

    }

    private void initBottomBar() {
        //iv_cellection= (ImageView) findViewById(R.id.iv_collection);
        tv_sum= (TextView) findViewById(R.id.tv_sum);

        //iv_cellection.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                onBackPressed();
                break;
            case R.id.iv_setting:
                //Toast.makeText(this,"点击了设置，此处用于设置字体，主题等",Toast.LENGTH_SHORT).show();
                Toast.makeText(this,"还未支持，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            /*case R.id.iv_collection:
                iv_cellection.setBackgroundResource(R.mipmap.ic_nocollection);
                Toast.makeText(this,"点击了收藏",Toast.LENGTH_SHORT).show();
                break;*/
        }
    }

    /*
   * 描述：查询数据
   * @param
   * @return
   */
    public  void getData(){
        //首先加载缓存
        if(CacheUtils.load(CACHE_DIR)!=null)
            loadCache();
        else
            loadDataFromBmob();
    }


    /**
     * 首先加载缓存数据
     */
    private void loadCache() {
    }

    private void loadDataFromBmob(){

        BmobQuery<AndroidQuestionBean> query = new BmobQuery<>();
        query.order("-createdAt");
        //返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(50);
        //执行查询方法
        query.findObjects(new FindListener<AndroidQuestionBean>() {
            @Override
            public void done(List<AndroidQuestionBean> object, BmobException e) {
                if(e==null){

                    for (int i=0; i<object.size(); i++) {
                        View view=View.inflate(getApplicationContext(),R.layout.question_layout,null);

                        TextView tv_question= (TextView) view.findViewById(R.id.tv_question);

                        tv_anwser = (TextView) view.findViewById(R.id.tv_anwser);

                        tv_question.setText(object.get(i).getQuestion());
                        tv_anwser.setText(object.get(i).getAnswer());

                        viewList.add(view);
                    }
                    ad.notifyDataSetChanged();
                    CacheUtils.save(object,CACHE_DIR);
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }


    private class viewpagerAdapter extends PagerAdapter{


        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            tv_sum.setText((vp_question.getCurrentItem()+1)+"/"+viewList.size());

            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            Log.i("Tag","pos---->"+position);

            /*Message msg=new Message();
            msg.arg1=UPDATE_ITEM;
            mHandler.sendMessage(msg);*/
            return viewList.get(position);
        }
    }
}
