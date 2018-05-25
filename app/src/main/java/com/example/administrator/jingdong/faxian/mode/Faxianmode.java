package com.example.administrator.jingdong.faxian.mode;

import com.example.administrator.jingdong.faxian.bean.User;
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

public class Faxianmode {

 public void getfaxian(Map<String,String> map, final Getfaxian getfaxian){
     Observable<User> faxianjson = Util.getmInstance().getnetjson(Getnet.net).faxianjson(map);
     faxianjson.subscribeOn(Schedulers.newThread())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(new Consumer<User>() {
         @Override
         public void accept(User user) throws Exception {
             List<User.DataBean> data = user.getData();
             getfaxian.getFaxian(data);
         }
     });
 }

 public interface Getfaxian{
     void getFaxian(List<User.DataBean> data);
 }

}
