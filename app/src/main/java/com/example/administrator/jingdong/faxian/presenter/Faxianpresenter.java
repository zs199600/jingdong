package com.example.administrator.jingdong.faxian.presenter;

import com.example.administrator.jingdong.faxian.bean.User;
import com.example.administrator.jingdong.faxian.mode.Faxianmode;
import com.example.administrator.jingdong.faxian.view.Ifaxian;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/24.
 */

public class Faxianpresenter {
    private Ifaxian ifaxian;
    private Faxianmode faxianmode;
    public Faxianpresenter(Ifaxian ifaxian) {
        this.ifaxian = ifaxian;
        faxianmode=new Faxianmode();
    }
    public void faxain(int i){
        Map<String,String> map=new HashMap<>();
        map.put("page",""+i);
        faxianmode.getfaxian(map, new Faxianmode.Getfaxian() {
            @Override
            public void getFaxian(List<User.DataBean> data) {
                ifaxian.setmyadapter(data);
            }
        });
    }

    public void jiazai(int i){
        Map<String,String> map=new HashMap<>();
        map.put("page",""+i);
        faxianmode.getfaxian(map, new Faxianmode.Getfaxian() {
            @Override
            public void getFaxian(List<User.DataBean> data) {
                ifaxian.jiazaigengduo(data);
            }
        });
    }
}
