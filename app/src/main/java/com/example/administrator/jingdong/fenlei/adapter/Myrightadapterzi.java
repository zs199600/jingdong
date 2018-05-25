package com.example.administrator.jingdong.fenlei.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.jingdong.R;
import com.example.administrator.jingdong.fenlei.bean.Feileirightbean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2018/2/5.
 */

public class Myrightadapterzi extends RecyclerView.Adapter<Myrightadapterzi.Myhodlerzi> {
    private Context context;
    private List<Feileirightbean.DataBean.ListBean> list1;
    private Itemclick item;

    public Myrightadapterzi(Context context, List<Feileirightbean.DataBean.ListBean> list1) {
        this.context = context;
        this.list1 = list1;
    }

    @Override
    public Myhodlerzi onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.feilei_right_zi, null);
        Myhodlerzi myhodlerzi=new Myhodlerzi(view);
        return myhodlerzi;
    }

    @Override
    public void onBindViewHolder(final Myhodlerzi holder, final int position) {
       holder.feilei_right_zi_tv.setText(list1.get(position).getName());
       holder.sd.setImageURI(Uri.parse(list1.get(position).getIcon()));
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
        return list1.size();
    }

    class Myhodlerzi extends RecyclerView.ViewHolder{
       public TextView feilei_right_zi_tv;
       public SimpleDraweeView sd;
        public Myhodlerzi(View itemView) {
            super(itemView);
            this.feilei_right_zi_tv=itemView.findViewById(R.id.feilei_right_zi_tv);
            this.sd=itemView.findViewById(R.id.sd);
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
