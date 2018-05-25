package com.example.administrator.jingdong.fenlei.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.jingdong.R;
import com.example.administrator.jingdong.fenlei.bean.Feileirightbean;
import com.example.administrator.jingdong.kuangjia.Sousuo;

import java.util.List;

/**
 * Created by Administrator on 2018/2/5.
 */

public class Myadapterright extends RecyclerView.Adapter<Myadapterright.Myhodlerright> {
    private Context context;
    private List<Feileirightbean.DataBean> list;

    public Myadapterright(Context context, List<Feileirightbean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Myhodlerright onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.feilei_right, null);
        Myhodlerright myhodlerright = new Myhodlerright(view);
        return myhodlerright;
    }

    @Override
    public void onBindViewHolder(Myhodlerright holder, int position) {
         holder.right_tv.setText(list.get(position).getName());
        final List<Feileirightbean.DataBean.ListBean> list1 = this.list.get(position).getList();
        Myrightadapterzi myrightadapterzi=new Myrightadapterzi(context,list1);
        holder.right_rc.setAdapter(myrightadapterzi);
        holder.right_rc.setLayoutManager(new GridLayoutManager(context,3));
        myrightadapterzi.huidiao(new Myrightadapterzi.Itemclick() {
            @Override
            public void itemclick(View view, int position) {
              Intent intent = new Intent(context, Sousuo.class);
                int pscid = list1.get(position).getPscid();
                intent.putExtra("id",pscid+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Myhodlerright extends RecyclerView.ViewHolder{
        public TextView right_tv;
        public RecyclerView right_rc;
        public Myhodlerright(View itemView) {
            super(itemView);
           this.right_tv= itemView.findViewById(R.id.right_tv);
           this.right_rc= itemView.findViewById(R.id.right_rc);
        }
    }
}
