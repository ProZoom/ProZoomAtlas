package com.taobao.demo.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.taobao.demo.MainActivity;
import com.taobao.demo.R;
import com.taobao.demo.basePages.FunctionBaseTagPage;
import com.taobao.demo.basePages.UiBaseTagPage;
import com.taobao.demo.basePages.baseTagPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyang on 2017/7/14.
 */

public class MainFragment extends Fragment{
    public MainActivity mainActivity;

    private View root;

    private ViewPager vp_main;

    private MyAdapter adapter;

    private List<baseTagPage> page=new ArrayList();



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取Fragment所在的Activity
        mainActivity =(MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //绑定布局
        root = inflater.inflate(R.layout.fragment_main,container,false);
        vp_main= (ViewPager) root.findViewById(R.id.vp_main);
        ((BottomNavigationView)root.findViewById(R.id.navigation)).setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        return root;
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        page.add(new UiBaseTagPage(mainActivity));
        page.add(new FunctionBaseTagPage(mainActivity));
        //page.add(new SettingBaseTagPage(mainActivity));

        adapter=new MyAdapter();
        vp_main.setAdapter(adapter);
    }



    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return page.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            baseTagPage baseTagpage=page.get(position);
            View root=baseTagpage.getRoot();
            container.addView(root);
            return root;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    vp_main.setCurrentItem(0);
                   // Toast.makeText(mainActivity,"UI",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_dashboard:
                    vp_main.setCurrentItem(1);
                    FunctionBaseTagPage.initAnimation();
                    //Toast.makeText(mainActivity,"IT",Toast.LENGTH_SHORT).show();
                    return true;

            }
            return false;
        }

    };


}
