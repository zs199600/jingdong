package com.example.administrator.jingdong.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.jingdong.R;
import com.example.administrator.jingdong.bean.Shop_xiangqing;
import com.example.administrator.jingdong.fenlei.presenter.Fenleipresenter;
import com.example.administrator.jingdong.kuangjia.Ixaingqing;
import com.example.administrator.jingdong.kuangjia.Lunbotuxiangqing;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/2/8.
 */

public class Feilei1 extends Basefragment implements Ixaingqing{
     @BindView(R.id.xbnr)
     XBanner xbnr;
     @BindView(R.id.feilei_xiangqing_fragment_title)
    TextView title;
    @BindView(R.id.feilei_xiangqing_fragment_price)
    TextView price;
    @BindView(R.id.addgou)
    Button addgou;
    @BindView(R.id.web)
    WebView web;

    private String pid;
    List<String> list=new ArrayList<>();
    Shop_xiangqing.DataBean data;
    private Fenleipresenter fenleipresenter;

    @Override
    protected int getCintentview() {
        return R.layout.feileixiangqing_fragment;
    }

    @Override
    void initData() {
        Bundle bundle = getArguments();//从activity传过来的Bundle
        if(bundle!=null){
            pid = bundle.getString("str");
        }
        fenleipresenter = new Fenleipresenter(this);
        fenleipresenter.xiangqing();

    }

    @Override
    void onclick() {
        //添加购物车
      addgou.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int pid = (int) data.getPid();
        fenleipresenter.addgouwuche(pid+"");
    }

    @Override
    public String getpid() {
        return pid;
    }

    @Override
    public void getxiangqingjson(Shop_xiangqing.DataBean data) {
      this.data=data;
        String images = data.getImages();
        String[] split = images.split("\\|");
        for (int i = 0; i <split.length ; i++) {
            list.add(split[i]);
        }
        xbnr.setData(list, null);
        xbnr.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(list.get(position)).into((ImageView) view);
            }

        });

        title.setText(data.getTitle());
        price.setText(data.getPrice()+"");
        web.loadUrl(data.getDetailUrl());
        web.setWebViewClient(new WebViewClient());

        //xbanner的点击事件
        xbnr.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                Intent intent=new Intent(getActivity(), Lunbotuxiangqing.class);
                String s = list.get(position);
                intent.putStringArrayListExtra("list", (ArrayList<String>) list);
                intent.putExtra("name",position);
                startActivity(intent);
            }
        });
    }

    @Override
    public void chenggong(String s) {
        Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
    }
}
