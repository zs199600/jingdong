package com.example.administrator.jingdong.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.jingdong.R;
import com.example.administrator.jingdong.kuangjia.Sousuokuang;
import com.example.administrator.jingdong.shouye.adapter.Myshouyeadapter;
import com.example.administrator.jingdong.shouye.bean.Feileileftbean;
import com.example.administrator.jingdong.shouye.bean.Shouyebean;
import com.example.administrator.jingdong.shouye.presenter.Shouyepresenter;
import com.example.administrator.jingdong.shouye.view.Ishouyeview;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/2/24.
 */

public class Shouyefragment extends Basefragment implements Ishouyeview{
    List<Feileileftbean.DataBean> data;
    @BindView(R.id.shouye_xrc)
    XRecyclerView shouye_xrc;
    @BindView(R.id.fg)
    FrameLayout fg;
    @BindView(R.id.sousuo)
    TextView sousuo;
    private int mDistanceY;
    private XBanner myxbanner;
    Handler handler=new Handler();
    List<String> list=new ArrayList<>();
    @Override
    protected int getCintentview() {
        return R.layout.shouye;
    }

    @Override
    void initData() {
        Shouyepresenter shouyepresenter=new Shouyepresenter(getContext(),this);
        shouyepresenter.getmv1();
        shouyepresenter.getmv();
        View view1 = View.inflate(getContext(), R.layout.shouye_xbanner, null);
        shouye_xrc.addHeaderView(view1);
        myxbanner = view1.findViewById(R.id.myxbanner);
    }

    @Override
    void onclick() {
     sousuo.setOnClickListener(this);
    }

    @Override
    public void getshoyyebean(Context context, Shouyebean shouyebean) {
        Myshouyeadapter myshouyeadapter=new Myshouyeadapter(context,shouyebean,data);
        shouye_xrc.setAdapter(myshouyeadapter);
        shouye_xrc.setLayoutManager(new LinearLayoutManager(context));
        final List<Shouyebean.DataBean> data = shouyebean.getData();
        for (int i = 0; i <data.size() ; i++) {
            list.add(data.get(i).getIcon());
        }
        myxbanner.setData(list, null);
        myxbanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(list.get(position)).into((ImageView) view);
            }

        });
        shouye_xrc.setLoadingMoreEnabled(false);
        shouye_xrc.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //滑动的距离
                mDistanceY += dy;
                //toolbar的高度
                int toolbarHeight = fg.getBottom();

                //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
                if (mDistanceY <= 200) {
                    float scale = (float) mDistanceY / 200;
                    float alpha = scale * 255;
                    fg.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                } else {
                    //将标题栏的颜色设置为完全不透明状态
                    fg.setBackgroundResource(R.color.bai);
                }
            }
        });
        shouye_xrc.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                fg.setVisibility(View.GONE);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fg.setVisibility(View.VISIBLE);
                        shouye_xrc.refreshComplete();
                    }
                },1000);
            }

            @Override
            public void onLoadMore() {

            }
        });

    }

    @Override
    public void feileileft(List<Feileileftbean.DataBean> data) {
    this.data=data;
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getContext(),Sousuokuang.class);
        startActivity(intent);
    }
}
