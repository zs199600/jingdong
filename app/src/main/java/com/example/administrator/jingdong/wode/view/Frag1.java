package com.example.administrator.jingdong.wode.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.jingdong.R;
import com.example.administrator.jingdong.wode.adapter.Myadapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2018/2/26.
 */

public class Frag1 extends Basefragment1 {
   @BindView(R.id.xrc)
   XRecyclerView xrc;
    @BindView(R.id.wd_touxiang)
    ImageView wd_touxiang;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.fg)
    FrameLayout fg;
    private SharedPreferences ji;
    private Frag1 frag1;
    private Frag2 frag2;
    private TextView wode_tv;
    private ImageView wode_iv1;
    private ImageView wode_iv2;
    private ImageView wode_iv3;
    private ImageView wode_iv4;
    private ImageView wode_iv5;
    private SimpleDraweeView sd;
   Handler handler=new Handler();

    @Override
    int getCintentview() {
        return R.layout.wode;
    }

    @Override
    void initData() {
        wd_touxiang.setVisibility(View.INVISIBLE);
        tv.setVisibility(View.INVISIBLE);
        View inflate = View.inflate(getContext(), R.layout.f1, null);
        xrc.addHeaderView(inflate);
        sd = inflate.findViewById(R.id.sd);
        wode_tv = inflate.findViewById(R.id.wode_tv);
        wode_iv1 = inflate.findViewById(R.id.wode_iv1);
        wode_iv2 = inflate.findViewById(R.id.wode_iv2);
        wode_iv3 = inflate.findViewById(R.id.wode_iv3);
        wode_iv4 = inflate.findViewById(R.id.wode_iv4);
        wode_iv5 = inflate.findViewById(R.id.wode_iv5);
        sd.setImageURI(Uri.parse("res://com.example.f1/"+R.drawable.user));
        ji = getContext().getSharedPreferences("ji", MODE_PRIVATE);
        frag1 = new Frag1();
        frag2 = new Frag2();

        xrc.setAdapter(new Myadapter());
        xrc.setLayoutManager(new LinearLayoutManager(getContext()));
        xrc.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                fg.setVisibility(View.GONE);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fg.setVisibility(View.VISIBLE);
                        xrc.refreshComplete();
                    }
                },1000);

            }

            @Override
            public void onLoadMore() {
                xrc.refreshComplete();
            }
        });
    }

    @Override
    void onclick() {
        sd.setOnClickListener(this);
        wode_tv.setOnClickListener(this);
        wode_iv1.setOnClickListener(this);
        wode_iv2.setOnClickListener(this);
        wode_iv3.setOnClickListener(this);
        wode_iv4.setOnClickListener(this);
        wode_iv5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), Denglu.class);
        startActivityForResult(intent,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==2){
            String uid = data.getStringExtra("uid");
            ji.edit().putString("uid",uid).commit();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment, frag2).commit();
            ji.edit().putBoolean("flog",true).commit();
        }
    }
}
