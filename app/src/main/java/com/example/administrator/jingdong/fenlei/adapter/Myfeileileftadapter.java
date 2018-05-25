package com.example.administrator.jingdong.fenlei.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.jingdong.R;
import com.example.administrator.jingdong.shouye.bean.Feileileftbean;

import java.util.List;

/**
 * Created by Administrator on 2018/2/5.
 */

public class Myfeileileftadapter extends RecyclerView.Adapter<Myfeileileftadapter.Myhodler> {
    private Context context;
    private List<Feileileftbean.DataBean> data;
    private Itemclick item;


    public Myfeileileftadapter(Context context, List<Feileileftbean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public Myhodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.feilei_left_zi, null);
        Myhodler myhodler=new Myhodler(view);
        return myhodler;
    }

    @Override
    public void onBindViewHolder(final Myhodler holder, final int position) {
        holder.feilei_zuo_tv.setText(data.get(position).getName());
        //item为接口的变量名
        if (item!=null){
            //holder为Myhodler holder的参数
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //调用接口的方法
                    item.itemclick(holder.itemView,position);
                }
            });
        }


    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    class Myhodler extends RecyclerView.ViewHolder{
          public TextView feilei_zuo_tv;
        public Myhodler(View itemView) {
            super(itemView);
            this.feilei_zuo_tv=itemView.findViewById(R.id.feilei_zuo_tv);
        }
    }

    //定义接口
    public interface Itemclick{
        void itemclick(View view, int position);
    }

    //定义方法   参数1为接口  2为接口的变量名
    public void huidiao(Itemclick item){
        this.item=item;
    }

}
