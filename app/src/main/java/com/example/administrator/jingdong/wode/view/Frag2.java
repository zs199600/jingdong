package com.example.administrator.jingdong.wode.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.jingdong.R;
import com.example.administrator.jingdong.kuangjia.Xiangqing;
import com.example.administrator.jingdong.wode.adapter.Tuijianadapter;
import com.example.administrator.jingdong.wode.bean.Denglubean;
import com.example.administrator.jingdong.wode.bean.Tuijianbean;
import com.example.administrator.jingdong.wode.presenter.Mypresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/2/26.
 */

public class Frag2 extends Basefragment1 implements Ishow{
    @BindView(R.id.xrc)
    XRecyclerView xrc;
    @BindView(R.id.wd_touxiang)
    ImageView wd_touxiang;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.fg)
    FrameLayout fg;
    private SharedPreferences ji;
    private TextView wode_tv;
    private SimpleDraweeView sd;
    private String uid;

    private int mDistanceY;
    Handler handler=new Handler();

    private EditText updata;
    private View inflate;

    private String icon;
    private String nickname;
    private String username;
    private Mypresenter mypresenter;

    @Override
    int getCintentview() {
        return R.layout.wode;
    }

    @Override
    void initData() {
        mypresenter = new Mypresenter(this);
        mypresenter.getuser();
        wd_touxiang.setVisibility(View.INVISIBLE);
        tv.setVisibility(View.INVISIBLE);
        View inflate = View.inflate(getContext(), R.layout.dengluchenggong, null);
        xrc.addHeaderView(inflate);
        sd = inflate.findViewById(R.id.sd);
        wode_tv = inflate.findViewById(R.id.wode_tv);

         mypresenter.gettuijian();


    }

    @Override
    void onclick() {

        wode_tv.setOnClickListener(this);
        sd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
     	switch (v.getId()) {

            case R.id.sd:
                Intent intent = new Intent(getContext(), User_info.class);
                intent.putExtra("touxiang",icon);
                intent.putExtra("name",nickname);
                intent.putExtra("username",username);
                startActivityForResult(intent,7);
                break;
     			}
    }

    @Override
    public String getuid() {
        ji = getActivity().getSharedPreferences("ji", Context.MODE_PRIVATE);
        uid = ji.getString("uid", "");
        return uid;
    }

    @Override
    public void getuser(Denglubean denglubean) {

        icon = (String) denglubean.getData().getIcon();
        nickname = (String) denglubean.getData().getNickname();
        username = denglubean.getData().getUsername();
        if (icon !=null|| nickname !=null){
            wode_tv.setText(nickname);
            if (icon ==null){
                sd.setImageURI(Uri.parse("res://com.example.wode/" + R.drawable.user));
            }else {
                sd.setImageURI(icon);
            }
        }
        else {
            sd.setImageURI(Uri.parse("res://com.example.wode/" + R.drawable.user));
            wode_tv.setText(username);

        }
    }

    @Override
    public void setMyadapter(final List<Tuijianbean.TuijianBean.ListBean> list) {
        xrc.setLoadingMoreEnabled(false);
        Tuijianadapter tuijianadapter=new Tuijianadapter(getContext(),list);
        xrc.setAdapter(tuijianadapter);
        xrc.setLayoutManager(new GridLayoutManager(getContext(),2));
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

        xrc.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                    wd_touxiang.setVisibility(View.INVISIBLE);
                    tv.setVisibility(View.INVISIBLE);
                } else {
                    wd_touxiang.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                    //将标题栏的颜色设置为完全不透明状态
                    fg.setBackgroundResource(R.color.bai);

                }
            }
        });

        tuijianadapter.huidiao(new Tuijianadapter.Itemclick() {
            @Override
            public void itemclick(View view, int position) {
                  int pid = (int) list.get(position).getPid();
                Intent intent = new Intent(getContext(), Xiangqing.class);
                intent.putExtra("pid",pid+"");
                getContext().startActivity(intent);
            }
        });

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==8){
            ji.edit().clear().commit();
            Frag1  frag1 = new Frag1();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment, frag1).commit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mypresenter.getuser();

    }

}
