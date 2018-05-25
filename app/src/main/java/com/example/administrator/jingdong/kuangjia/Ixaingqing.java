package com.example.administrator.jingdong.kuangjia;

import com.example.administrator.jingdong.bean.Shop_xiangqing;

/**
 * Created by Administrator on 2018/2/24.
 */

public interface Ixaingqing {
    String getpid();
   void getxiangqingjson(Shop_xiangqing.DataBean data);

   //添加购物车成功
    void chenggong(String s);

}
