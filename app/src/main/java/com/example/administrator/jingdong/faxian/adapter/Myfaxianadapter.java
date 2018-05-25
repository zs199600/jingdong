package com.example.administrator.jingdong.faxian.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.jingdong.R;

import com.example.administrator.jingdong.faxian.bean.User;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2018/2/9.
 */

public class Myfaxianadapter extends RecyclerView.Adapter<Myfaxianadapter.Myfaxianhodler> {
    private Context context;
    private List<User.DataBean> list;

    public Myfaxianadapter(Context context, List<User.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public List<User.DataBean> getList() {
        return list;
    }

    public void setList(List<User.DataBean> list) {
        this.list = list;
    }

    @Override
    public Myfaxianhodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.fragment2, null);
        Myfaxianhodler myfaxianhodler=new Myfaxianhodler(view);
        return myfaxianhodler;
    }

    @Override
    public void onBindViewHolder(Myfaxianhodler holder, int position) {
          holder.sd.setImageURI(Uri.parse(list.get(position).getUserImg()));
          holder.tv.setText(list.get(position).getTitle());
          holder.tv1.setText(list.get(position).getUserName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Myfaxianhodler extends RecyclerView.ViewHolder{
        public SimpleDraweeView sd;
        private TextView tv;
        private TextView tv1;
        public Myfaxianhodler(View itemView) {
            super(itemView);
            this.sd=itemView.findViewById(R.id.sd);
            this.tv=itemView.findViewById(R.id.tv);
            this.tv1=itemView.findViewById(R.id.tv1);
        }
    }
}
