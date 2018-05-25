package com.example.administrator.jingdong.wode.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.jingdong.R;
import com.example.administrator.jingdong.wode.bean.User;
import com.example.administrator.jingdong.wode.presenter.Mypresenter;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class Zhuce extends AppCompatActivity implements View.OnClickListener,Izhuce{
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    @BindView(R.id.zhuce_tel)
    EditText zhuce_tel;
    @BindView(R.id.zhuce_pwd)
    EditText zhuce_pwd;
    @BindView(R.id.zhuce_ed1)
    EditText zhuce_ed1;
    @BindView(R.id.zhuce_bt1)
    Button zhuce_bt1;
    @BindView(R.id.zhuce_bt)
    Button zhuce_bt;
    private String tel;
    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            switch (what) {
                case 1:
                 String zhuce_tel1 = zhuce_tel.getText().toString();
                    String zhuce_pwd1 = zhuce_pwd.getText().toString();
                    user = new User(zhuce_tel1,zhuce_pwd1);
                    Mypresenter mypresenter=new Mypresenter(Zhuce.this);
                    mypresenter.zhuce();
                    break;
                case 2:
                    Toast.makeText(Zhuce.this,"验证码输入错误",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        zhuce_bt.setOnClickListener(this);
        zhuce_bt1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zhuce_bt:
                tel = zhuce_tel.getText().toString();
                boolean mobile = isMobile(tel);
                if (!mobile){
                    Toast.makeText(this, "手机号不正确", Toast.LENGTH_SHORT).show();
                }else {
                    sendCode("86", tel);
                }
                break;
            case R.id.zhuce_bt1:

                submitCode("86", tel,zhuce_ed1.getText().toString());
                break;
        }
    }

    // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
    public void sendCode(String country, String phone) {
        // 注册一个事件回调，用于处理发送验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // TODO 处理成功得到验证码的结果
                    // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达

                } else{
                    // TODO 处理错误的结果

                }

            }
        });
        // 触发操作
        SMSSDK.getVerificationCode(country, phone);
    }
    // 提交验证码，其中的code表示验证码，如“1357”
    public void submitCode(String country, String phone, String code) {
        // 注册一个事件回调，用于处理提交验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    handler.sendEmptyMessage(1);
                } else{
                    handler.sendEmptyMessage(2);
                }

            }
        });
        // 触发操作
        SMSSDK.submitVerificationCode(country, phone, code);
    }

    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    @Override
    public User getuser() {
        User user=new User(zhuce_tel.getText().toString(),zhuce_pwd.getText().toString());
        return user;
    }

    @Override
    public void chenggong() {
        Toast.makeText(Zhuce.this,"成功",Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        intent.putExtra("zhuceuser", user);
        setResult(3,intent);
        finish();
    }

    @Override
    public void shibai(String s) {
        Toast.makeText(Zhuce.this,s,Toast.LENGTH_SHORT).show();
    }
}
