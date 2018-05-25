package com.example.administrator.jingdong.wode.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jingdong.R;
import com.example.administrator.jingdong.wode.bean.User;
import com.example.administrator.jingdong.wode.presenter.Mypresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Denglu extends AppCompatActivity implements Idenglu,View.OnClickListener{
    @BindView(R.id.wode_bt)
    Button wode_bt;
    @BindView(R.id.ed)
    EditText ed;
    @BindView(R.id.ed1)
    EditText ed1;
    @BindView(R.id.wode_zhuce)
    TextView wode_zhuce;
    private SharedPreferences ji;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.denglu);
        ButterKnife.bind(this);
        init();
        ji = getSharedPreferences("ji", MODE_PRIVATE);



    }

    private void init() {
        getSupportActionBar().hide();
        wode_bt.setOnClickListener(this);
        wode_zhuce.setOnClickListener(this);
    }



    @Override
    public void chenggong(String uid) {
        Intent intent = getIntent();
        Intent intent1 = intent.putExtra("uid", uid+"");
        setResult(2,intent1);
        finish();
    }

    @Override
    public void shibai(String s) {
        Toast.makeText(Denglu.this,s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        	switch (v.getId()) {
        			case R.id.wode_bt:
                        User user=new User(ed.getText().toString(),ed1.getText().toString());
                        Mypresenter mypresenter = new Mypresenter(this);
                        mypresenter.getdengmv(user);
        				break;
                case R.id.wode_zhuce:
                    startActivityForResult(new Intent(Denglu.this,Zhuce.class),1);
                    break;
        			}
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==3){
           User zhuceuser = (User) data.getSerializableExtra("zhuceuser");
            Mypresenter mypresenter = new Mypresenter(this);
            mypresenter.getdengmv(zhuceuser);

        }
    }
}
