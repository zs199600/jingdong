package com.example.administrator.jingdong.kuangjia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.jingdong.R;
import com.example.administrator.jingdong.myview.ZoomImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Lunbotuxiangqing extends AppCompatActivity {
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tv)
    TextView tv;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunbotu);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        int i = intent.getIntExtra("name", 0);
        list = intent.getStringArrayListExtra("list");
        vp.setAdapter(new Myadapter());
        vp.setCurrentItem(i);
        tv.setText((i+1)+"/"+list.size());
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
              tv.setText((position+1)+"/"+list.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
    class Myadapter extends PagerAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ZoomImageView zoomImageView=new ZoomImageView(Lunbotuxiangqing.this);
            Glide.with(Lunbotuxiangqing.this).load(list.get(position)).into(zoomImageView);
            container.addView(zoomImageView);
            return zoomImageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
               container.removeView((View) object);
        }
    }
}
