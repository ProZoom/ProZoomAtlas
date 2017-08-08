package com.taobao.demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.taobao.demo.activity.feedbackActivity;
import com.taobao.demo.activity.lawActivity;
import com.taobao.demo.activity.AndroidQuestionActivity;
import com.taobao.demo.fragment.LeftFragment;
import com.taobao.demo.fragment.MainFragment;
import com.taobao.demo.utils.AppUtils;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private static final String MAIN_FRAGMENT_TAG ="MAIN_TAG" ;
    private static final String LEFTMENU_FRAGMENT_TAG ="LEFTMENU_TAG" ;


    private DrawerLayout mDrawerlayout;

    private ImageView iv_figure;
    private TextView tv_head_title;

    public static Tencent mTencent;
    final private String QQAPPID="1106290418";
    private BaseUiListener mIUiListener;
    private UserInfo mInfo;
//获取Fragment里的ID未解决

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getSupportActionBar().hide();
        steepStatusBar();
        setContentView(R.layout.fragment_main_content);
        mTencent = Tencent.createInstance(QQAPPID,MainActivity.this);

        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.fragment_main);
        super.onCreate(savedInstanceState);
        initView();
        initFragment();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTencent.logout(MainActivity.this);

    }

    private void initView() {
        iv_figure= (ImageView) findViewById(R.id.ic_left_head);
        tv_head_title= (TextView) findViewById(R.id.tv_head_title);

        mDrawerlayout= (DrawerLayout) findViewById(R.id.dl_left);
    }


    public void initFragment() {
        //获取Fragment管理器
        FragmentManager fragmentManager=getSupportFragmentManager();
        //打开事务
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        //把内容显示至帧布局
        fragmentTransaction.replace(R.id.fl_main_content, new MainFragment(), MAIN_FRAGMENT_TAG);
        fragmentTransaction.replace(R.id.fl_left_menu_content, new LeftFragment(), LEFTMENU_FRAGMENT_TAG);
        //提交事物
        fragmentTransaction.commit();

    }

    /***********沉浸状态栏***********/
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mTencent.onActivityResultData(requestCode, resultCode, data, mIUiListener);
    }
    /********** 双击退出程序 ************/
    private long exitTime=0;
    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        //判断是否是在1秒内连续点击返回键，是则退出，不是不退出
        if(System.currentTimeMillis()-exitTime>800){
            Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_LONG).show();
            exitTime=System.currentTimeMillis();
            if(mDrawerlayout.isDrawerOpen(Gravity.LEFT))
                mDrawerlayout.closeDrawer(Gravity.LEFT);
        }else {
            super.onBackPressed();
        }
    }


    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
            Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
            Log.i("tag", "response:" + response);

            //获取openid和token
            initOpenIdAndToken(response);
            //获取用户信息
            getUserInfo();
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(getApplicationContext(), "登录取消", Toast.LENGTH_SHORT).show();
        }
    }

    private void initOpenIdAndToken(Object object) {
        JSONObject jb = (JSONObject) object;
        try {
            String openID = jb.getString("openid");  //openid用户唯一标识
            String access_token = jb.getString("access_token");
            String expires = jb.getString("expires_in");

            mTencent.setOpenId(openID);
            mTencent.setAccessToken(access_token, expires);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getUserInfo() {
        QQToken token = mTencent.getQQToken();
        mInfo = new UserInfo(MainActivity.this, token);
        mInfo.getUserInfo(new IUiListener() {
            @Override
            public void onComplete(Object object) {
                JSONObject jo = (JSONObject) object;
                Log.i("TAG",object.toString());

                try {
                    String head_title = jo.getString("nickname");
                    String figureurl = jo.getString("figureurl_qq_2");

                    tv_head_title.setText(head_title);
                    Picasso.with(getApplicationContext()).load(figureurl).into(iv_figure);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(UiError uiError) {
            }

            @Override
            public void onCancel() {
            }
        });
    }

    public void ImageLogOff(View v){
        //ToastShowShort("点击注销按钮");
        if (mTencent.isSessionValid()) {
            mTencent.logout(MainActivity.this);
            Toast.makeText(this,"账号注销成功！",Toast.LENGTH_SHORT).show();
            iv_figure.setImageResource(R.mipmap.ic_qq);
            tv_head_title.setText("QQ 昵称");
        }else {
            Toast.makeText(this,"账号已经注销了！",Toast.LENGTH_SHORT).show();
        }
    }
    public void ImageLogin(View v){
        //ToastShowShort("点击头像登录");
        /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
         官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
         第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
        mIUiListener = new BaseUiListener();

        if (!mTencent.isSessionValid()) {
            mTencent.login(MainActivity.this,"all", mIUiListener);
        }else {
            Toast.makeText(this,"已经登陆",Toast.LENGTH_SHORT).show();
        }

    }
    public void github(View v){
        //ToastShowShort("github");

        Intent intent= new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse("http://professorzoom.top");
        intent.setData(content_url);
        startActivity(intent);
    }
    public void play(View v){
        //ToastShowShort("play");
        //支付宝捐赠
        if (AppUtils.isApkInstalled(this, "com.eg.android.AlipayGphone")) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            String alipayUrl = "HTTPS://QR.ALIPAY.COM/FKX004526F5IDY1I6VVA5F";
            intent.setData(Uri.parse("alipayqr://platformapi/startapp?saId=10000007&clientVersion=3.7.0.0718&qrcode=" + alipayUrl));
            if (intent.resolveActivity(this.getPackageManager()) != null) {
                startActivity(intent);
            } else {
                intent.setData(Uri.parse(alipayUrl));
                startActivity(intent);
            }
        } else {
            Toast.makeText(this,"手机上未安装支付宝!",Toast.LENGTH_LONG).show();
        }
    }
    public void law(View v){
       // Toast.makeText(this,"lawActivity!",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(MainActivity.this,lawActivity.class);
        startActivity(intent);

    }
    public void feedback(View v){
        Intent intent=new Intent(MainActivity.this,feedbackActivity.class);
        startActivity(intent);
        /*Intent i=new Intent(Intent.ACTION_SEND);
        String[] tos={"719947897@qq.com"};
        String[] ccs={"2849791937@qq.com"};
        i.putExtra(Intent.EXTRA_EMAIL,tos);
        i.putExtra(Intent.EXTRA_CC,ccs);
        i.putExtra(Intent.EXTRA_TEXT,"邮件内容");
        i.putExtra(Intent.EXTRA_SUBJECT,"邮件主题");
        i.setType("message/rfc822");
        startActivity(Intent.createChooser(i,"你的邮件"));*/

    }
    public void share(View v){
        shareText();

    }
    public void update(View v){
        Intent intent=new Intent(MainActivity.this,AndroidQuestionActivity.class);
        startActivity(intent);
    }

    private void shareText() {

        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,"这是一段文字");
        intent.setType("text/plain");

        startActivity(Intent.createChooser(intent,"分享到"));

    }

}

