package com.example.administrator.jingdong.gouwuche.mode;

import com.example.administrator.jingdong.gouwuche.bean.Gouwuche;
import com.example.administrator.jingdong.util.Getnet;
import com.example.administrator.jingdong.util.Util;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/2/22.
 */

public class Gouwuchemode {

    public void getgouwuche(final Getgouwuchebean getgouwuchebean){
        Observable<Gouwuche> user = Util.getmInstance().getnetjson(Getnet.shouye).getUser();
        user.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Gouwuche>() {
                    @Override
                    public void accept(Gouwuche gouwuche) throws Exception {
                       getgouwuchebean.getgouwuchejson(gouwuche);
                    }
                });
    }
   public interface Getgouwuchebean{
        void getgouwuchejson(Gouwuche gouwuche);
   }
}
