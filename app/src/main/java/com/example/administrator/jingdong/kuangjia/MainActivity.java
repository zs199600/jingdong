package com.example.administrator.jingdong.kuangjia;

import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.jingdong.R;
import com.example.administrator.jingdong.fragment.Faxianfragment;
import com.example.administrator.jingdong.fragment.Fenleifragment;
import com.example.administrator.jingdong.fragment.Gouwuchefragment;
import com.example.administrator.jingdong.fragment.Shouyefragment;
import com.example.administrator.jingdong.fragment.Wodefragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.jingdong_rg)
    RadioGroup jingdong_rg;
    @BindView(R.id.shouye)
    RadioButton shouye;
    @BindView(R.id.fenlei)
    RadioButton fenlei;
    @BindView(R.id.faxian)
    RadioButton faxian;
    @BindView(R.id.gouwuche)
    RadioButton gouwuche;
    @BindView(R.id.wode)
    RadioButton wode;

    private Shouyefragment shouyefragment;
    private Fenleifragment fenleifragment;
    private Faxianfragment faxianfragment;
    private Gouwuchefragment gouwuchefragment;
    private Wodefragment wodefragment;

    @Override
    int getContentview() {
        return R.layout.activity_main;
    }

    @Override
    void initData() {
        getSupportActionBar().hide();
        shouyefragment = new Shouyefragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fg,shouyefragment).commit();
    }

    @Override
    protected void btclick() {
        jingdong_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                hideFragments();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (checkedId) {
                    case R.id.shouye:
                        fragmentTransaction.show(shouyefragment).commit();
                        break;
                    case R.id.fenlei:
                        if (fenleifragment==null){
                            fenleifragment = new Fenleifragment();
                            fragmentTransaction.add(R.id.fg, fenleifragment).commit();
                        }else {
                            fragmentTransaction.show(fenleifragment).commit();
                        }
                        break;

                    case R.id.faxian:
                        if (faxianfragment==null){
                            faxianfragment = new Faxianfragment();
                            fragmentTransaction.add(R.id.fg, faxianfragment).commit();
                        }else {
                            fragmentTransaction.show(faxianfragment).commit();
                        }
                        break;
                    case R.id.gouwuche:
                        if (gouwuchefragment==null){
                            gouwuchefragment = new Gouwuchefragment();
                            fragmentTransaction.add(R.id.fg, gouwuchefragment).commit();
                        }else {

                            fragmentTransaction.show(gouwuchefragment).commit();
                        }
                        break;
                    case R.id.wode:
                        if (wodefragment==null){
                            wodefragment = new Wodefragment();
                            fragmentTransaction.add(R.id.fg, wodefragment).commit();
                        }else {
                            fragmentTransaction.show(wodefragment).commit();
                        }
                        break;

                }
            }
        });
    }

    private void hideFragments() {
        if (shouyefragment!=null&&shouyefragment.isAdded()){
            getSupportFragmentManager().beginTransaction().hide(shouyefragment).commit();
        }
        if (fenleifragment!=null&&fenleifragment.isAdded()){
            getSupportFragmentManager().beginTransaction().hide(fenleifragment).commit();
        }
        if (faxianfragment!=null&&faxianfragment.isAdded()){
            getSupportFragmentManager().beginTransaction().hide(faxianfragment).commit();
        }
        if (gouwuchefragment!=null&&gouwuchefragment.isAdded()){
            getSupportFragmentManager().beginTransaction().hide(gouwuchefragment).commit();
        }
        if (wodefragment!=null&&wodefragment.isAdded()){
            getSupportFragmentManager().beginTransaction().hide(wodefragment).commit();
        }
    }
}
