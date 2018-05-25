package com.example.administrator.jingdong.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.administrator.jingdong.R;
import com.example.administrator.jingdong.faxian.adapter.Myfaxianadapter;
import com.example.administrator.jingdong.faxian.bean.User;
import com.example.administrator.jingdong.faxian.presenter.Faxianpresenter;
import com.example.administrator.jingdong.faxian.view.Ifaxian;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/2/8.
 */

public class Feilei2 extends Basefragment implements Ifaxian{
    @BindView(R.id.xrc1)
    XRecyclerView xrc;
   int i;
    private Myfaxianadapter myfaxianadapter;
    private Faxianpresenter faxianpresenter;

    @Override
    protected int getCintentview() {
        return R.layout.xrc;
    }

    @Override
    void initData() {
        faxianpresenter = new Faxianpresenter(this);
        i=1;
        faxianpresenter.faxain(i);
    }

    @Override
    void onclick() {
    xrc.setLoadingListener(new XRecyclerView.LoadingListener() {
        @Override
        public void onRefresh() {
            i=1;
            faxianpresenter.faxain(i);
            xrc.refreshComplete();
        }

        @Override
        public void onLoadMore() {
            Tost("加载更多");
            i++;
            faxianpresenter.jiazai(i);
            xrc.refreshComplete();
        }
    });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void setmyadapter(List<User.DataBean> data) {
        myfaxianadapter = new Myfaxianadapter(getContext(), data);
        xrc.setAdapter(myfaxianadapter);
        xrc.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void jiazaigengduo(List<User.DataBean> data) {
        List<User.DataBean> list = myfaxianadapter.getList();
        list.addAll(data);
        myfaxianadapter.notifyDataSetChanged();
    }
}
