package com.example.administrator.jingdong.wode.presenter;

import android.util.Log;

import com.example.administrator.jingdong.wode.bean.Denglubean;
import com.example.administrator.jingdong.wode.bean.Tuijianbean;
import com.example.administrator.jingdong.wode.bean.Upnamebean;
import com.example.administrator.jingdong.wode.bean.User;
import com.example.administrator.jingdong.wode.bean.Zhucebean;
import com.example.administrator.jingdong.wode.mode.Mymode;
import com.example.administrator.jingdong.wode.view.Idenglu;
import com.example.administrator.jingdong.wode.view.Ishow;
import com.example.administrator.jingdong.wode.view.Iupnameinfo;
import com.example.administrator.jingdong.wode.view.Iupuser;
import com.example.administrator.jingdong.wode.view.Izhuce;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/25.
 */

public class Mypresenter {
     private Idenglu idenglu;
     private Mymode denglumode;
     private Ishow ishow;
     private Izhuce izhuce;
     private Iupuser iupuser;
     private Iupnameinfo iupnameinfo;

    public Mypresenter(Iupnameinfo iupnameinfo) {
        this.iupnameinfo = iupnameinfo;
        denglumode=new Mymode();
    }

    public Mypresenter(Iupuser iupuser) {
        this.iupuser = iupuser;
        denglumode=new Mymode();
    }

    public Mypresenter(Izhuce izhuce) {
        this.izhuce = izhuce;
        denglumode=new Mymode();
    }

    public Mypresenter(Ishow ishow) {
        this.ishow = ishow;
        denglumode=new Mymode();
    }

    public Mypresenter(Idenglu idenglu) {
        this.idenglu = idenglu;
        denglumode=new Mymode();
    }

    public void getdengmv(User user){
        Log.d("你猜p", "getdengmv: "+user.getTel()+"   "+user.getPwd());
        Map<String,String> map=new HashMap<>();
        map.put("mobile",user.getTel());
        map.put("password",user.getPwd());
        denglumode.getdenglu(map, new Mymode.Getdenglulisteneter() {
            @Override
            public void getDenglu(Denglubean denglubean) {
                if (denglubean.getCode().equals("0")){
                    int uid = denglubean.getData().getUid();
                    idenglu.chenggong(uid+"");
                }else {
                    idenglu.shibai(denglubean.getMsg());
                }
            }
        });
    }

    public void getuser(){

        String getuid = ishow.getuid();
        Map<String,String> map=new HashMap<>();
        map.put("uid",getuid);
        denglumode.getuser(map, new Mymode.Getuserlisteneter() {
            @Override
            public void getUser(Denglubean denglubean) {
                ishow.getuser(denglubean);
            }
        });
    }

    public void gettuijian(){
        denglumode.gettuijianjson(new Mymode.Gettuijianlisteneter() {
            @Override
            public void getTuijian(List<Tuijianbean.TuijianBean.ListBean> list) {
                ishow.setMyadapter(list);
            }
        });
    }

    public void zhuce(){
        User getuser = izhuce.getuser();
        Map<String,String> map=new HashMap<>();
        map.put("mobile",getuser.getTel());
        map.put("password",getuser.getPwd());
        denglumode.zhuce(map, new Mymode.Zhucelisteneter() {
            @Override
            public void getZhuce(Zhucebean zhucebean) {
                if (zhucebean.getCode().equals("0")){
                    izhuce.chenggong();
                }else {
                    izhuce.shibai(zhucebean.getMsg());
                }
            }
        });
    }

    //修改昵称
    public void upname(String uid,String nickname){
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("nickname",nickname);
        denglumode.updataname(map, new Mymode.Upnamelisteneter() {
            @Override
            public void updataName(Upnamebean upname) {
              if (upname.getCode().equals("0")){
                  iupnameinfo.upnamechenggong();
              }
            }
        });
    }

    //修改头像
    public void uptouxiang(String uid, File file){
        denglumode.uptouxaing(uid, file, new Mymode.Uptouxianglisteneter() {
            @Override
            public void upTouxiang(Upnamebean upnamebean) {
                if (upnamebean.getCode().equals("0")){
                    iupuser.uptouxiang();
                }
            }
        });

    }

}
