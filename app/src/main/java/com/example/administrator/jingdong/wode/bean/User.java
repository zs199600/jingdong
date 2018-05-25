package com.example.administrator.jingdong.wode.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/25.
 */

public class User implements Serializable{
    private String tel;
    private String pwd;

    public User(String tel, String pwd) {
        this.tel = tel;
        this.pwd = pwd;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
