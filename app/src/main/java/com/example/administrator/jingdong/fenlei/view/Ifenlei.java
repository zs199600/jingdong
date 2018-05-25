package com.example.administrator.jingdong.fenlei.view;

import com.example.administrator.jingdong.fenlei.bean.Feileirightbean;
import com.example.administrator.jingdong.shouye.bean.Feileileftbean;

import java.util.List;

/**
 * Created by Administrator on 2018/2/24.
 */

public interface Ifenlei {
    void setleftadapter(List<Feileileftbean.DataBean> data);

    void setrightadapter(List<Feileirightbean.DataBean> data);
}
