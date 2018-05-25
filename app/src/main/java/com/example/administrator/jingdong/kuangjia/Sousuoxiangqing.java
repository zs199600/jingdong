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
import com.example.administrator.jingdong.shouye.adapter.Mysousuoadapter;
import com.example.administrator.jingdong.shouye.bean.Sousuoxiangqingbean;
import com.example.administrator.jingdong.shouye.presenter.Shouyepresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;

public class Sousuoxiangqing extends BaseActivity implements Isousuoxaingqing{
    private String name;
    private int i=R.layout.activity_sousuo;
    @BindView(R.id.xrc)
    XRecyclerView xrc;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.qie)
    ImageView qie;
    @BindView(R.id.fl_sousuo)
    TextView fl_sousuo;
    private boolean flog=true;
    private  int p;
    List<Sousuoxiangqingbean.DataBean> data;
    private Mysousuoadapter mysousuoadapter;
    private Shouyepresenter shouyepresenter;

    @Override
    int getContentview() {
        return R.layout.sousuo;
    }

    @Override
    void initData() {
        getSupportActionBar().hide();
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        shouyepresenter = new Shouyepresenter(this);
        shouyepresenter.sousuoxiangqing(p);

        //刷新加载的方法
        xrc.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                shouyepresenter.sousuoxiangqing(p);
                xrc.refreshComplete();

            }

            @Override
            public void onLoadMore() {
                shouyepresenter.jiazai(p);
                xrc.refreshComplete();
            }
        });

    }

    @Override
    protected void btclick() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb:
                        p=0;
                        shouyepresenter.sousuoxiangqing(p);
                        break;
                    case R.id.rb1:
                        p=1;
                        shouyepresenter.sousuoxiangqing(p);
                        break;
                    case R.id.rb2:
                        p=2;
                        shouyepresenter.sousuoxiangqing(p);
                        break;
                }
            }
        });

        qie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flog){
                    qie.setImageResource(R.drawable.kind_grid);
                    i=R.layout.activity_sousuo1;
                    mysousuoadapter = new Mysousuoadapter(Sousuoxiangqing.this,data,i);
                    xrc.setAdapter(mysousuoadapter);
                    xrc.setLayoutManager(new GridLayoutManager(Sousuoxiangqing.this,2));
                    flog=false;
                }else{
                    qie.setImageResource(R.drawable.kind_liner);
                    i=R.layout.activity_sousuo;
                    mysousuoadapter = new Mysousuoadapter(Sousuoxiangqing.this,data,i);
                    xrc.setAdapter(mysousuoadapter);
                    xrc.setLayoutManager(new LinearLayoutManager(Sousuoxiangqing.this));
                    flog=true;
                }
            }
        });
    }

    @Override
    public String getname() {
        return name;
    }

    @Override
    public void setmyadapter(final List<Sousuoxiangqingbean.DataBean> data) {
        this.data=data;
        mysousuoadapter = new Mysousuoadapter(Sousuoxiangqing.this,data,i);
        xrc.setAdapter(mysousuoadapter);
        if (flog) {
            xrc.setLayoutManager(new LinearLayoutManager(Sousuoxiangqing.this));
        }
        mysousuoadapter.huidiao(new Mysousuoadapter.Itemclick() {
            @Override
            public void itemclick(View view, int position) {
                int pid = (int) data.get(position).getPid();
                Intent intent = new Intent(Sousuoxiangqing.this, Xiangqing.class);
                intent.putExtra("pid",pid+"");
                startActivity(intent);
            }
        });
    }

    @Override
    public void jiazaigengduo(List<Sousuoxiangqingbean.DataBean> data) {
        List<Sousuoxiangqingbean.DataBean> list = mysousuoadapter.getList();
        list.addAll(data);
        if (data.size()==0){
            Toast.makeText(Sousuoxiangqing.this,"没有数据了",Toast.LENGTH_SHORT).show();
        }
        mysousuoadapter.notifyDataSetChanged();
    }
}
