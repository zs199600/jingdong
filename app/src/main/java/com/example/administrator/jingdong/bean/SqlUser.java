package com.example.administrator.jingdong.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/2/25.
 */

@Entity
public class SqlUser {

    private String name;

    @Generated(hash = 738339592)
    public SqlUser(String name) {
        this.name = name;
    }

    @Generated(hash = 1968607127)
    public SqlUser() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
