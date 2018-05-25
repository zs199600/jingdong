package com.example.administrator.jingdong.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.jingdong.R;
import com.example.administrator.jingdong.fenlei.adapter.Myadapterright;
import com.example.administrator.jingdong.fenlei.adapter.Myfeileileftadapter;
import com.example.administrator.jingdong.fenlei.bean.Feileirightbean;
import com.example.administrator.jingdong.fenlei.presenter.Fenleipresenter;
import com.example.administrator.jingdong.fenlei.view.Ifenlei;
import com.example.administrator.jingdong.shouye.bean.Feileileftbean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/2/24.
 */

public class Fenleifragment extends Basefragment implements Ifenlei{
    @BindView(R.id.sousuo)
    TextView sousuo;
    @BindView(R.id.fenlei_left)
    RecyclerView fenlei_left;
    @BindView(R.id.fenlei_right)
    RecyclerView fenlei_right;
    private Fenleipresenter fenleipresenter;

    @Override
    protected int getCintentview() {
        return R.layout.fenlei_main;
    }

    @Override
    void initData() {
        fenleipresenter = new Fenleipresenter(getContext(),this);
        fenleipresenter.fenleileft();
        fenleipresenter.fenleiright(1+"");
    }

    @Override
    void onclick() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void setleftadapter(final List<Feileileftbean.DataBean> data) {
        Myfeileileftadapter myfeileileftadapter=new Myfeileileftadapter(getContext(),data);
        fenlei_left.setAdapter(myfeileileftadapter);
        fenlei_left.setLayoutManager(new LinearLayoutManager(getActivity()));
        myfeileileftadapter.huidiao(new Myfeileileftadapter.Itemclick() {
            @Override
            public void itemclick(View view, int position) {
                int cid = data.get(position).getCid();
                fenleipresenter.fenleiright(cid+"");
            }
        });
    }

    @Override
    public void setrightadapter(List<Feileirightbean.DataBean> data) {
        Myadapterright myadapterright=new Myadapterright(getContext(),data);
        fenlei_right.setAdapter(myadapterright);
        fenlei_right.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
