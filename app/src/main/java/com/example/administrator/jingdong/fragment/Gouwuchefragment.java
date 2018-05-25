package com.example.administrator.jingdong.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jingdong.R;
import com.example.administrator.jingdong.bean.Countentbean;
import com.example.administrator.jingdong.gouwuche.adapter.Myadapter;
import com.example.administrator.jingdong.gouwuche.bean.Gouwuche;
import com.example.administrator.jingdong.gouwuche.presenter.Gouwuchepresenter;
import com.example.administrator.jingdong.gouwuche.view.Igouwuche;
import com.example.administrator.jingdong.wode.view.Denglu;
import com.example.administrator.jingdong.wode.view.Frag2;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2018/2/24.
 */

public class Gouwuchefragment extends Basefragment implements Igouwuche{
    @BindView(R.id.edlist)
    ExpandableListView edlist;
    @BindView(R.id.hj)
    TextView hj;
    @BindView(R.id.jiesuan)
    Button jiesuan;
    @BindView(R.id.cb)
    CheckBox cb;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.wancheng)
    LinearLayout wancheng;
    @BindView(R.id.bianji)
    LinearLayout bianji;
    @BindView(R.id.shanchu)
    Button shanchu;
    @BindView(R.id.denglu)
    Button denglu;
    @BindView(R.id.goubutton)
   LinearLayout goubutton;
    @BindView(R.id.deng)
   LinearLayout deng;
    private Myadapter myadapter;
    private List<Gouwuche.DataBean> data;
    boolean flog = true;
    private Countentbean countentbean;
    private boolean flog1;
    private Intent intent;


    @Override
    protected int getCintentview() {
        return R.layout.gouwuche;
    }

    @Override
    void initData() {
        countentbean = new Countentbean();
        Gouwuchepresenter gouwuchepresenter=new Gouwuchepresenter(getContext(),this);
        gouwuchepresenter.getmv();
        SharedPreferences ji = getContext().getSharedPreferences("ji",getContext(). MODE_PRIVATE);
       boolean flog = ji.getBoolean("flog", false);
        if (flog){
            deng.setVisibility(View.GONE);
            goubutton.setVisibility(View.VISIBLE);
            edlist.setVisibility(View.VISIBLE);
        }
        else {
            goubutton.setVisibility(View.GONE);
            edlist.setVisibility(View.GONE);
        }



        edlist.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getContext(),data.get(groupPosition).getList().get(childPosition).getTitle(),Toast.LENGTH_SHORT).show();
                return true;

            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
       SharedPreferences ji = getContext().getSharedPreferences("ji",getContext(). MODE_PRIVATE);
        flog1 = ji.getBoolean("flog", false);
        if (flog1){
            deng.setVisibility(View.GONE);
            goubutton.setVisibility(View.VISIBLE);
            edlist.setVisibility(View.VISIBLE);
        }
        else {
            deng.setVisibility(View.VISIBLE);
            goubutton.setVisibility(View.GONE);
            edlist.setVisibility(View.GONE);

        }
    }



    @Override
    void onclick() {
    cb.setOnClickListener(this);
    tv.setOnClickListener(this);
        shanchu.setOnClickListener(this);
        cb.setOnClickListener(this);
        denglu.setOnClickListener(this);
        jiesuan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    	switch (v.getId()) {
    			case R.id.cb:
                    for (int i = 0; i <data.size() ; i++) {
                        boolean presenterck = data.get(i).isPresenterck();
                        data.get(i).setPresenterck(!presenterck);
                        List<Gouwuche.Mybean> list = data.get(i).getList();
                        for (int j = 0; j <list.size() ; j++) {
                            boolean  childck = list.get(j).isChildck();
                            list.get(j).setChildck(!childck);
                        }
                    }
                    heji();
                    myadapter.notifyDataSetChanged();
    				break;
            case R.id.tv:
                if (flog){
                    tv.setText("完成");
                    flog=false;
                    wancheng.setVisibility(View.VISIBLE);
                    bianji.setVisibility(View.GONE);

                }else {
                    tv.setText("编辑");
                    flog=true;
                    bianji.setVisibility(View.VISIBLE);
                    wancheng.setVisibility(View.GONE);
                }
                break;
            case R.id.shanchu:
                for (int i = 0; i < data.size(); i++) {
                    List<Gouwuche.Mybean> list = data.get(i).getList();
                    boolean presenterck = data.get(i).isPresenterck();
                    if (presenterck){
                        data.remove(i);
                        heji();
                        myadapter.notifyDataSetChanged();
                    }

                    for (int j = 0; j < list.size(); j++) {
                        boolean childck = list.get(j).isChildck();
                        if (childck) {
                            list.remove(j);
                            heji();
                            myadapter.notifyDataSetChanged();
                        }
                    }

                }
                break;
            case R.id.denglu:
                intent = new Intent(getContext(), Denglu.class);
                startActivityForResult(intent,1);
                    break;

            case R.id.jiesuan:
                int count = countentbean.getCount();
                if (count==0){
                    Toast.makeText(getContext(),"您还没有选择商品哦",Toast.LENGTH_SHORT).show();
                }else {

                }

        }
    }
    private SharedPreferences ji;
    private Frag2 frag2;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==2){
            frag2 = new Frag2();
            ji = getContext().getSharedPreferences("ji", MODE_PRIVATE);
            String uid = data.getStringExtra("uid");
            ji.edit().putString("uid",uid).commit();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment, frag2).commit();
            ji.edit().putBoolean("flog",true).commit();
            deng.setVisibility(View.GONE);
            goubutton.setVisibility(View.VISIBLE);
            edlist.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void setadapter(Context context, List<Gouwuche.DataBean> data) {
        this.data=data;
        myadapter = new Myadapter(context, data, this);
        edlist.setAdapter(myadapter);
    }

    @Override
    public void heji() {
        double add=0;
        int count=0;
        for (int i = 0; i <data.size() ; i++) {
            List<Gouwuche.Mybean> list = data.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                boolean childck = list.get(j).isChildck();
                if (childck){
                    count++;
                    int mycount = list.get(j).getCount() + 1;
                    double mul = mul(list.get(j).getPrice(), mycount);
                    add = add(add, mul);
                    countentbean.setCount(count);

                }
            }
        }
        hj.setText(add+"");
        jiesuan.setText("去结算"+"("+count+")");

    }
    //乘法
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    //加法
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

}
