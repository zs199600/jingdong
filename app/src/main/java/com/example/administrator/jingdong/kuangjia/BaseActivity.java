package com.example.administrator.jingdong.kuangjia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/2/1.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentview());
        ButterKnife.bind(this);
        initData();
        btclick();
    }
    abstract int getContentview();
    //初始化数据
    abstract void initData();
    //设置点击事件的方法
    protected abstract void btclick();

    //跳转的方法
    public void startActivity(Class<?> cla){
        startActivity(new Intent(this,cla));
    }

    //吐死的方法
    public void Tost(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}
