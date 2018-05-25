package com.example.administrator.jingdong.kuangjia;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.jingdong.bean.SqlUser;

import java.util.List;

/**
 * Created by Administrator on 2018/2/9.
 */

public class Mylistadapter extends BaseAdapter {
    private Context context;
    private List<SqlUser> list;

    public Mylistadapter(Context context, List<SqlUser> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView=new TextView(context);
        textView.setTextSize(40);
        textView.setText(list.get(position).getName());
        return textView;
    }
}
