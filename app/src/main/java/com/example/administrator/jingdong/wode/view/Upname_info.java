package com.example.administrator.jingdong.wode.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jingdong.R;
import com.example.administrator.jingdong.wode.presenter.Mypresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Upname_info extends AppCompatActivity implements View.OnClickListener,Iupnameinfo{
    @BindView(R.id.upbt)
    TextView upbt;
    @BindView(R.id.et_name)
    EditText et_name;
    private String uid;
    private SharedPreferences ji;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upname_info);
        ButterKnife.bind(this);
        upbt.setOnClickListener(this);
        ji = getSharedPreferences("ji", Context.MODE_PRIVATE);
        uid = ji.getString("uid", "");
    }

    @Override
    public void onClick(View v) {
        name = et_name.getText().toString();
        if (!name.trim().equals("")) {
            Mypresenter mypresenter = new Mypresenter(Upname_info.this);
            mypresenter.upname(uid, name);
        }else {
            Toast.makeText(this,"请输入要修改的昵称",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void upnamechenggong() {
        Intent intent = getIntent();
        intent.putExtra("upname",name);
        setResult(6,intent);
        finish();
    }
}
