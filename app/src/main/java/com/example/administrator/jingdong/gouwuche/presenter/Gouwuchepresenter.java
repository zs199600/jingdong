package com.example.administrator.jingdong.gouwuche.presenter;

import android.content.Context;

import com.example.administrator.jingdong.gouwuche.bean.Gouwuche;
import com.example.administrator.jingdong.gouwuche.mode.Gouwuchemode;
import com.example.administrator.jingdong.gouwuche.view.Igouwuche;

import java.util.List;

/**
 * Created by Administrator on 2018/2/22.
 */

public class Gouwuchepresenter {
    private Context context;
    private Igouwuche iview;
    private Gouwuchemode getgouwuche;

    public Gouwuchepresenter(Context context, Igouwuche iview) {
        this.context = context;
        this.iview = iview;
        getgouwuche=new Gouwuchemode();
    }

    public void getmv(){

        getgouwuche.getgouwuche(new Gouwuchemode.Getgouwuchebean() {
            @Override
            public void getgouwuchejson(Gouwuche gouwuche) {
                List<Gouwuche.DataBean> data = gouwuche.getData();
                iview.setadapter(context,data);
            }
        });
    }
}
