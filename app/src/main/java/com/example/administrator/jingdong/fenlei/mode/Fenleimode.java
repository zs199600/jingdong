package com.example.administrator.jingdong.fenlei.mode;

import com.example.administrator.jingdong.bean.Shop_xiangqing;
import com.example.administrator.jingdong.bean.Sousuobean;
import com.example.administrator.jingdong.fenlei.bean.Addgood;
import com.example.administrator.jingdong.fenlei.bean.Feileirightbean;
import com.example.administrator.jingdong.shouye.bean.Feileileftbean;
import com.example.administrator.jingdong.shouye.mode.Shouyemode;
import com.example.administrator.jingdong.util.Getnet;
import com.example.administrator.jingdong.util.Util;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/2/24.
 */

public class Fenleimode {
     //获取分类左数据
     public void getFeilei(final Shouyemode.Getfeileileftlisteneter getfeileileftlisteneter){

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
     //获取分类右
    public void getfenleiright(Map<String,String> map, final Getfeileirighelisteneter getfeileirighelisteneter ){
        Observable<Feileirightbean> getfenleiright = Util.getmInstance().getnetjson(Getnet.shouye).getfenleiright(map);
        getfenleiright.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Feileirightbean>() {
                    @Override
                    public void accept(Feileirightbean feileirightbean) throws Exception {
                        getfeileirighelisteneter.getFenleiright(feileirightbean);
                    }
                });
     }

     public interface Getfeileirighelisteneter{
        void getFenleiright(Feileirightbean feileirightbean);
     }

     //当前子分类下的商品列表
    public void getsousuo(Map<String,String> map, final Getspusuolisteneter getspusuolisteneter){
        Observable<Sousuobean> postsousuo = Util.getmInstance().getnetjson(Getnet.shouye).postsousuo(map);
        postsousuo.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Sousuobean>() {
            @Override
            public void accept(Sousuobean sousuobean) throws Exception {
                getspusuolisteneter.getSousuo(sousuobean);
            }
        });
    }
   public interface Getspusuolisteneter{
        void getSousuo(Sousuobean sousuobean);
    }
    
    //商品详情
    public void getshopxaingqiang(Map<String,String> map, final Getshoplisteneter getshoplisteneter){
        Observable<Shop_xiangqing> postgetshop = Util.getmInstance().getnetjson(Getnet.shouye).postgetshop(map);
        postgetshop.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Shop_xiangqing>() {
                    @Override
                    public void accept(Shop_xiangqing shop_xiangqing) throws Exception {
                        Shop_xiangqing.DataBean data = shop_xiangqing.getData();
                        getshoplisteneter.getShop(data);
                    }
                });
    }

    public interface Getshoplisteneter{
        void getShop(Shop_xiangqing.DataBean data);
    }

    //添加购物车
    public void addgou(Map<String,String> map, final Addgoulisteneter addgoulisteneter){
        Observable<Addgood> addshop = Util.getmInstance().getnetjson(Getnet.shouye).addshop(map);
        addshop.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Addgood>() {
                    @Override
                    public void accept(Addgood addgood) throws Exception {
                        addgoulisteneter.addGouwuche(addgood);
                    }
                });
    }

    public interface Addgoulisteneter{
        void addGouwuche(Addgood addgood);
    }

}
