package com.example.administrator.jingdong.shouye.presenter;

import android.content.Context;

import com.example.administrator.jingdong.kuangjia.Isousuoxaingqing;
import com.example.administrator.jingdong.shouye.bean.Feileileftbean;
import com.example.administrator.jingdong.shouye.bean.Shouyebean;
import com.example.administrator.jingdong.shouye.bean.Sousuoxiangqingbean;
import com.example.administrator.jingdong.shouye.mode.Shouyemode;
import com.example.administrator.jingdong.shouye.view.Ishouyeview;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/24.
 */

public class Shouyepresenter {
    private Context context;
    private Ishouyeview ishouyeview;
   private Shouyemode shouyemode;
   private Isousuoxaingqing isousuoxaingqing;

    public Shouyepresenter(Isousuoxaingqing isousuoxaingqing) {
        this.isousuoxaingqing = isousuoxaingqing;
        shouyemode=new Shouyemode();
    }

    public Shouyepresenter(Context context, Ishouyeview ishouyeview) {
        this.context = context;
        this.ishouyeview = ishouyeview;
        shouyemode=new Shouyemode();
    }

    public void getmv(){
        shouyemode.getshouye(new Shouyemode.Getshouye() {
            @Override
            public void getShouye(Shouyebean shouyebean) {
                ishouyeview.getshoyyebean(context,shouyebean);
            }
        });
    }


    public void getmv1(){
        shouyemode.getFeilei(new Shouyemode.Getfeileileftlisteneter() {
            @Override
            public void getFeieileft(Feileileftbean feileileftbean) {
                List<Feileileftbean.DataBean> data = feileileftbean.getData();
                ishouyeview.feileileft(data);
            }
        });
    }
     int i;
    //搜索详情
    public void sousuoxiangqing(int p){
        String getname = isousuoxaingqing.getname();
        Map<String,String> map=new HashMap<>();
        map.put("keywords",getname);
        i=0;
        map.put("page",i+"");
        map.put("sort",p+"");
        shouyemode.getshouyexiangqing(map, new Shouyemode.Sousuoxiangqinglisteneter() {
            @Override
            public void getSousuoxiangqingbean(List<Sousuoxiangqingbean.DataBean> data) {
                isousuoxaingqing.setmyadapter(data);
            }
        });
    }

    public void jiazai(int p){
        String getname = isousuoxaingqing.getname();
        Map<String,String> map=new HashMap<>();
        map.put("keywords",getname);
        i++;
        map.put("page",i+"");
        map.put("sort",p+"");
        shouyemode.getshouyexiangqing(map, new Shouyemode.Sousuoxiangqinglisteneter() {
            @Override
            public void getSousuoxiangqingbean(List<Sousuoxiangqingbean.DataBean> data) {
                isousuoxaingqing.jiazaigengduo(data);
            }
        });
    }
}
