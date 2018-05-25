package com.example.administrator.jingdong.wode.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/2/4.
 */

public abstract class Basefragment1 extends Fragment implements View.OnClickListener{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getCintentview(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        onclick();
    }

    abstract int getCintentview();
    //初始化数据
    abstract void initData();
    //初始化点击事件
    abstract void onclick();

    public void Tost(String s){
        Toast.makeText(getActivity(),s,Toast.LENGTH_SHORT).show();
    }

    public void startintent(Class<?> c){
        startActivity(new Intent(getActivity(),c));
    }
}
