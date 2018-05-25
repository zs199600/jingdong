package com.example.administrator.jingdong.kuangjia;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.administrator.jingdong.R;
import com.example.administrator.jingdong.fragment.Feilei1;
import com.example.administrator.jingdong.fragment.Feilei2;
import com.example.administrator.jingdong.fragment.Feilei3;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Xiangqing extends BaseActivity {
    @BindView(R.id.tab)
    TabLayout tb;

    @BindView(R.id.vp)
    ViewPager vp;

    List<Fragment> list=new ArrayList<>();
    List<String> listtile=new ArrayList<>();
    private String pid;
    private Feilei1 feilei1;

    @Override
    int getContentview() {
        return R.layout.activity_xiangqing;
    }

    @Override
    void initData() {
        Intent intent =getIntent();
        pid = intent.getStringExtra("pid");
        feilei1 = new Feilei1();
        list.add(feilei1);
        list.add(new Feilei2());
        list.add(new Feilei3());
        listtile.add("商品");
        listtile.add("详情");
        listtile.add("评价");
        Bundle bundle = new Bundle();
        bundle.putString("str", pid);
        feilei1.setArguments(bundle);
        tb.setTabMode(TabLayout.MODE_FIXED);
        tb.setupWithViewPager(vp);
        vp.setAdapter(new Myadapter(getSupportFragmentManager()));
    }

    @Override
    protected void btclick() {

    }

    class Myadapter extends FragmentPagerAdapter {

        public Myadapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return listtile.get(position);
        }
    }
}
