package com.example.administrator.jingdong.wode.mode;

import com.example.administrator.jingdong.util.Getnet;
import com.example.administrator.jingdong.util.Util;
import com.example.administrator.jingdong.wode.bean.Denglubean;
import com.example.administrator.jingdong.wode.bean.Tuijianbean;
import com.example.administrator.jingdong.wode.bean.Upnamebean;
import com.example.administrator.jingdong.wode.bean.Zhucebean;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/2/25.
 */

public class Mymode {
    //登录
    public void getdenglu(Map<String,String> map, final Getdenglulisteneter getdenglulisteneter){
        Observable<Denglubean> denglu = Util.getmInstance().getnetjson(Getnet.shouye).denglu(map);
        denglu.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Consumer<Denglubean>() {
            @Override
            public void accept(Denglubean denglubean) throws Exception {
                getdenglulisteneter.getDenglu(denglubean);
            }
        });
    }
    public interface Getdenglulisteneter{
        void getDenglu(Denglubean denglubean);
    }

    //获取用户信息
    public void getuser(Map<String,String> map, final Getuserlisteneter getuserlisteneter){
        Observable<Denglubean> getuser = Util.getmInstance().getnetjson(Getnet.shouye).getuser(map);
        getuser.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Denglubean>() {
                    @Override
                    public void accept(Denglubean denglubean) throws Exception {
                        getuserlisteneter.getUser(denglubean);
                    }
                });
    }

    public interface Getuserlisteneter{
        void getUser(Denglubean denglubean);
    }

    //获取推荐
    public void gettuijianjson(final Gettuijianlisteneter gettuijianlisteneter){
        Observable<Tuijianbean> gettuijian = Util.getmInstance().getnetjson(Getnet.shouye).gettuijian();
        gettuijian.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Tuijianbean>() {
                    @Override
                    public void accept(Tuijianbean tuijianbean) throws Exception {
                        List<Tuijianbean.TuijianBean.ListBean> list = tuijianbean.getTuijian().getList();
                        gettuijianlisteneter.getTuijian(list);
                    }
                });
    }

    public interface Gettuijianlisteneter{
          void getTuijian(List<Tuijianbean.TuijianBean.ListBean> list);
    }

    //注册
    public void zhuce(Map<String,String> map, final Zhucelisteneter zhucelisteneter){
        Observable<Zhucebean> zhuce = Util.getmInstance().getnetjson(Getnet.shouye).zhuce(map);
        zhuce.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Zhucebean>() {
                    @Override
                    public void accept(Zhucebean zhucebean) throws Exception {
                        zhucelisteneter.getZhuce(zhucebean);
                    }
                });
    }
     public interface Zhucelisteneter{
        void getZhuce(Zhucebean zhucebean);
     }

     //更改昵称
  public void updataname(Map<String,String> map, final Upnamelisteneter upnamelisteneter){
      Observable<Upnamebean> myupname =Util.getmInstance().getnetjson(Getnet.shouye).myupname(map);
      myupname.subscribeOn(Schedulers.newThread())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Consumer<Upnamebean>() {
                  @Override
                  public void accept(Upnamebean upname) throws Exception {
                    upnamelisteneter.updataName(upname);
                  }
              });

  }
  public interface Upnamelisteneter{
     void updataName(Upnamebean upname);
  }

  //更换头像
   public void uptouxaing(String uid, File file, final Uptouxianglisteneter uptouxianglisteneter){
       MultipartBody.Part filePart = MultipartBody.Part.createFormData("file",file.getName(), RequestBody.create(
               MediaType.parse("application/octet-stream"),file));
       Observable<Upnamebean> upload2 = Util.getmInstance().getnetjson(Getnet.shouye).upload2(uid, filePart);
       upload2.subscribeOn(Schedulers.newThread())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Consumer<Upnamebean>() {
                   @Override
                   public void accept(Upnamebean upnamebean) throws Exception {
                  uptouxianglisteneter.upTouxiang(upnamebean);
                   }
               });

   }
    public interface Uptouxianglisteneter{
       void upTouxiang(Upnamebean upnamebean);
    }
}
