package com.example.administrator.jingdong.kuangjia;

import com.example.administrator.jingdong.bean.Sousuobean;

import java.util.List;

/**
 * Created by Administrator on 2018/2/24.
 */

public interface Isousuo {
    String getid();
    void setadapter(List<Sousuobean.DataBean> data);
    void jiazai(List<Sousuobean.DataBean> data);
}
