package com.example.administrator.jingdong.kuangjia;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jingdong.R;
import com.example.administrator.jingdong.bean.Sousuobean;
import com.example.administrator.jingdong.fenlei.adapter.Myadapterxiangqing;
import com.example.administrator.jingdong.fenlei.presenter.Fenleipresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;

public class Sousuo extends BaseActivity implements Isousuo{
    /*
    * 点击分类子条目  进入本页面
    * 本页面为搜索后  销量  价格  切换试图页面
    * */
    List<Sousuobean.DataBean> data;
    private String id;
    @BindView(R.id.xrc)
    XRecyclerView xrc;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.qie)
    ImageView qie;
    @BindView(R.id.fl_sousuo)
    TextView fl_sousuo;
    private  int p;
    private int q;
    private int i=R.layout.activity_sousuo;
    private Myadapterxiangqing myadapterxiangqing;
    private boolean flog=true;
    private Fenleipresenter fenleipresenter;

    @Override
    int getContentview() {
        return R.layout.sousuo;
    }

    @Override
    void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        fenleipresenter = new Fenleipresenter(this);
        q=1;
        fenleipresenter.Feileisousuo(p,q);

        //跳转到搜索框页面
        fl_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Sousuo.this,Sousuokuang.class);
                startActivity(intent);
            }
        });

        //下拉刷新  下拉加载
        xrc.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                q=1;
                fenleipresenter.Feileisousuo(p,q);
                xrc.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                q++;
                fenleipresenter.Feileisousuojiazai(p,q);
                Toast.makeText(Sousuo.this,"加载更多",Toast.LENGTH_SHORT).show();
                xrc.refreshComplete();
            }
        });



    }

    @Override
    protected void btclick() {
        //销量  价格的点击事件
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb:

                        p=0;
                        fenleipresenter.Feileisousuo(p,q);
                        break;
                    case R.id.rb1:

                        p=1;
                        fenleipresenter.Feileisousuo(p,q);
                        break;
                    case R.id.rb2:
                        p=2;
                        fenleipresenter.Feileisousuo(p,q);
                        break;
                }
            }
        });

        //点击切换图片的事件
        qie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flog){
                    qie.setImageResource(R.drawable.kind_grid);
                    i=R.layout.activity_sousuo1;
                    myadapterxiangqing = new Myadapterxiangqing(Sousuo.this,data,i);
                    xrc.setAdapter(myadapterxiangqing);
                    xrc.setLayoutManager(new GridLayoutManager(Sousuo.this,2));
                    flog=false;
                }else{
                    qie.setImageResource(R.drawable.kind_liner);
                    i=R.layout.activity_sousuo;
                    myadapterxiangqing = new Myadapterxiangqing(Sousuo.this,data,i);
                    xrc.setAdapter(myadapterxiangqing);
                    xrc.setLayoutManager(new LinearLayoutManager(Sousuo.this));
                    flog=true;
                }
            }
        });
    }

    @Override
    public String getid() {
        return id;
    }

    @Override
    public void setadapter(final List<Sousuobean.DataBean> data) {
        this.data=data;
        myadapterxiangqing = new Myadapterxiangqing(Sousuo.this, data, i);
        xrc.setAdapter(myadapterxiangqing);
        if (flog) {
            xrc.setLayoutManager(new LinearLayoutManager(Sousuo.this));
        }
        myadapterxiangqing.huidiao(new Myadapterxiangqing.Itemclick() {
            @Override
            public void itemclick(View view, int position) {
                int pid = (int) data.get(position).getPid();
                Intent intent = new Intent(Sousuo.this, Xiangqing.class);
                intent.putExtra("pid",pid+"");
                startActivity(intent);

            }
        });
    }

    @Override
    public void jiazai(List<Sousuobean.DataBean> data) {
        List<Sousuobean.DataBean> list = myadapterxiangqing.getList();
        list.addAll(data);
        if (data.size()==0){
            Toast.makeText(Sousuo.this,"没有数据了",Toast.LENGTH_SHORT).show();
        }
        myadapterxiangqing.notifyDataSetChanged();
    }
}
