package com.example.administrator.jingdong.shouye.mode;

import com.example.administrator.jingdong.shouye.bean.Feileileftbean;
import com.example.administrator.jingdong.shouye.bean.Shouyebean;
import com.example.administrator.jingdong.shouye.bean.Sousuoxiangqingbean;
import com.example.administrator.jingdong.util.Getnet;
import com.example.administrator.jingdong.util.Util;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/2/24.
 */

public class Shouyemode {
    //获取首页   轮播图+京东秒杀+最底部的为你推荐
    public void getshouye(final Getshouye getshouye){
        Observable<Shouyebean> shouye = Util.getmInstance().getnetjson(Getnet.shouye).getShouye();
        shouye.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Shouyebean>() {
                    @Override
                    public void accept(Shouyebean shouyebean) throws Exception {
                        getshouye.getShouye(shouyebean);
                    }
                });
    }
    public interface Getshouye{
       void getShouye(Shouyebean shouyebean);
    }

    //获取首页分类数据
      public void getFeilei(final Getfeileileftlisteneter getfeileileftlisteneter){
          Observable<Feileileftbean> getfeileileft = Util.getmInstance().getnetjson(Getnet.shouye).getfeileileft();
          getfeileileft.subscribeOn(Schedulers.newThread())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Consumer<Feileileftbean>() {
                      @Override
                      public void accept(Feileileftbean feileileftbean) throws Exception {
                          getfeileileftlisteneter.getFeieileft(feileileftbean);
                      }
                  });
      }
      public interface Getfeileileftlisteneter{
          void getFeieileft(Feileileftbean feileileftbean);
      }

      //首页搜索详情的数据
       public void getshouyexiangqing(Map<String,String> map, final Sousuoxiangqinglisteneter sousuoxiangqinglisteneter){
           Observable<Sousuoxiangqingbean> getsouxaing = Util.getmInstance().getnetjson(Getnet.shouye).getsouxaing(map);
           getsouxaing.subscribeOn(Schedulers.newThread())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(new Consumer<Sousuoxiangqingbean>() {
                       @Override
                       public void accept(Sousuoxiangqingbean sousuoxiangqingbean) throws Exception {
                           List<Sousuoxiangqingbean.DataBean> data = sousuoxiangqingbean.getData();
                           sousuoxiangqinglisteneter.getSousuoxiangqingbean(data);
                       }
                   });

       }

       public interface Sousuoxiangqinglisteneter{
           void getSousuoxiangqingbean(List<Sousuoxiangqingbean.DataBean> data );
    }
}
