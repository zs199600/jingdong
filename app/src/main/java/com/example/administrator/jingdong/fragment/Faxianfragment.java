package com.example.administrator.jingdong.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.administrator.jingdong.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by Administrator on 2018/2/24.
 */

public class Faxianfragment extends Basefragment {

    @BindView(R.id.tab)
    TabLayout tb;

    @BindView(R.id.vp)
    ViewPager vp;
    List<Fragment> list=new ArrayList<>();
    List<String> listtile=new ArrayList<>();
    @Override
    protected int getCintentview() {
        return R.layout.faxian;
    }

    @Override
    void initData() {
        list.add(new Feilei2());
        list.add(new Feilei3());
        listtile.add("相亲");
        listtile.add("评价");

        tb.setTabMode(TabLayout.MODE_FIXED);
        tb.setupWithViewPager(vp);

        vp.setAdapter(new Myadapter(getChildFragmentManager()));
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
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return listtile.get(position);
        }
    }

    @Override
    void onclick() {

    }

    @Override
    public void onClick(View v) {

    }
}
