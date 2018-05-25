package com.example.administrator.jingdong.shouye.view;

import android.content.Context;

import com.example.administrator.jingdong.shouye.bean.Feileileftbean;
import com.example.administrator.jingdong.shouye.bean.Shouyebean;

import java.util.List;

/**
 * Created by Administrator on 2018/2/24.
 */

public interface Ishouyeview {
    ////获取首页   轮播图+京东秒杀+最底部的为你推荐
    void getshoyyebean(Context context,Shouyebean shouyebean);
    //获取首页分类数据
    void feileileft(List<Feileileftbean.DataBean> data);
}
