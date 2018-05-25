package com.example.administrator.jingdong.fenlei.presenter;

import android.content.Context;

import com.example.administrator.jingdong.bean.Shop_xiangqing;
import com.example.administrator.jingdong.bean.Sousuobean;
import com.example.administrator.jingdong.fenlei.bean.Addgood;
import com.example.administrator.jingdong.fenlei.bean.Feileirightbean;
import com.example.administrator.jingdong.fenlei.mode.Fenleimode;
import com.example.administrator.jingdong.fenlei.view.Ifenlei;
import com.example.administrator.jingdong.kuangjia.Isousuo;
import com.example.administrator.jingdong.kuangjia.Ixaingqing;
import com.example.administrator.jingdong.shouye.bean.Feileileftbean;
import com.example.administrator.jingdong.shouye.mode.Shouyemode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/24.
 */

public class Fenleipresenter {
     private Context context;
     private Ifenlei ifenlei;
     private Fenleimode fenleimode;
     private Isousuo isousuo;
     private Ixaingqing ixaingqing;

    public Fenleipresenter(Ixaingqing ixaingqing) {
        this.ixaingqing = ixaingqing;
        fenleimode=new Fenleimode();
    }

    public Fenleipresenter(Context context, Ifenlei ifenlei) {
        this.context = context;
        this.ifenlei = ifenlei;
        fenleimode=new Fenleimode();
    }

    public Fenleipresenter(Isousuo isousuo) {
        this.isousuo = isousuo;
        fenleimode=new Fenleimode();
    }
     //分类左数据
    public void fenleileft(){
        fenleimode.getFeilei(new Shouyemode.Getfeileileftlisteneter() {
            @Override
            public void getFeieileft(Feileileftbean feileileftbean) {
                List<Feileileftbean.DataBean> data = feileileftbean.getData();
                ifenlei.setleftadapter(data);
            }
        });
    }
    //分类右数据
    public void fenleiright(String cid){
        Map<String,String> map=new HashMap<>();
        map.put("cid",cid);
        fenleimode.getfenleiright(map, new Fenleimode.Getfeileirighelisteneter() {
            @Override
            public void getFenleiright(Feileirightbean feileirightbean) {
                List<Feileirightbean.DataBean> data = feileirightbean.getData();
                ifenlei.setrightadapter(data);
            }
        });
    }

    //分类搜索数据
    public void Feileisousuo(int p,int i){
        String pscid = isousuo.getid();
        Map<String,String> map=new HashMap<>();
        map.put("pscid",pscid);
        map.put("page",i+"");
        map.put("sort",p+"");
        fenleimode.getsousuo(map, new Fenleimode.Getspusuolisteneter() {
            @Override
            public void getSousuo(Sousuobean sousuobean) {
                List<Sousuobean.DataBean> data = sousuobean.getData();
                isousuo.setadapter(data);
            }
        });
    }
    //分类搜索加载更多
    public void Feileisousuojiazai(int p,int i){
        String pscid = isousuo.getid();
        Map<String,String> map=new HashMap<>();
        map.put("pscid",pscid);
        map.put("page",i+"");
        map.put("sort",p+"");
        fenleimode.getsousuo(map, new Fenleimode.Getspusuolisteneter() {
            @Override
            public void getSousuo(Sousuobean sousuobean) {
                List<Sousuobean.DataBean> data = sousuobean.getData();
                isousuo.jiazai(data);
            }
        });
    }

    //分类详情
    public void xiangqing(){
        String pid = ixaingqing.getpid();
        Map<String,String> map=new HashMap<>();
        map.put("pid",pid);
        fenleimode.getshopxaingqiang(map, new Fenleimode.Getshoplisteneter() {
            @Override
            public void getShop(Shop_xiangqing.DataBean data) {
                ixaingqing.getxiangqingjson(data);
            }
        });
    }

    //添加购物车
    public void addgouwuche(String pid){
        Map<String,String> map=new HashMap<>();
        map.put("pid",pid);
        fenleimode.addgou(map, new Fenleimode.Addgoulisteneter() {
            @Override
            public void addGouwuche(Addgood addgood) {
                if (addgood.getCode().equals("0")){
                ixaingqing.chenggong("加购成功");
                }
            }
        });
    }
}
