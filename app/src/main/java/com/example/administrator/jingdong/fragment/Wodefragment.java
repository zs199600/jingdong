package com.example.administrator.jingdong.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.example.administrator.jingdong.R;
import com.example.administrator.jingdong.wode.view.Frag1;
import com.example.administrator.jingdong.wode.view.Frag2;

/**
 * Created by Administrator on 2018/2/24.
 */

public class Wodefragment extends Basefragment {
    private SharedPreferences ji;
    private Frag1 frag1;
    private Frag2 frag2;
    @Override
    protected int getCintentview() {
        return R.layout.activity_wode;
    }

    @Override
    void initData() {
        frag1 = new Frag1();
        frag2 = new Frag2();
        ji = getContext().getSharedPreferences("ji", Context.MODE_PRIVATE);
        boolean flog = ji.getBoolean("flog", false);
        if (flog){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment, frag2).commit();
        }else {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment, frag1).commit();
        }
    }

    @Override
    void onclick() {

    }

    @Override
    public void onClick(View v) {

    }
}
