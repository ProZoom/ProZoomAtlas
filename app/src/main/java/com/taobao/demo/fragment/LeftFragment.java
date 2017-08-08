package com.taobao.demo.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taobao.demo.MainActivity;
import com.taobao.demo.R;

/**
 * Created by liyang on 2017/7/14.
 */

public class LeftFragment extends Fragment {
    public MainActivity mainActivity;

    private View root;



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
        root = inflater.inflate(R.layout.fragment_left,container,false);

        return root;
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
