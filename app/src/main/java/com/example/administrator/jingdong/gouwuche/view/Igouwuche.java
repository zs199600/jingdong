package com.example.administrator.jingdong.gouwuche.view;

import android.content.Context;

import com.example.administrator.jingdong.gouwuche.bean.Gouwuche;

import java.util.List;

/**
 * Created by Administrator on 2018/2/25.
 */

public interface Igouwuche {
    void setadapter(Context context, List<Gouwuche.DataBean> data);
    void heji();
}
