package com.example.administrator.jingdong.wode.view;


import com.example.administrator.jingdong.wode.bean.Denglubean;
import com.example.administrator.jingdong.wode.bean.Tuijianbean;

import java.util.List;

/**
 * Created by Administrator on 2018/2/25.
 */

public interface Ishow {
    String getuid();
    void getuser(Denglubean denglubean);

    void setMyadapter(List<Tuijianbean.TuijianBean.ListBean> list);



}
