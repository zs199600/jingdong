package com.example.wodegai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wodegai.R;
import com.example.wodegai.bean.Tuijianbean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2018/2/26.
 */

public class Tuijianadapter extends RecyclerView.Adapter<com.example.wodegai.adapter.Tuijianadapter.Myhodler> {
   private Context context;
   private List<Tuijianbean.TuijianBean.ListBean> list;
    private Itemclick item;

    public Tuijianadapter(Context context, List<Tuijianbean.TuijianBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Myhodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.shouye_tuijian_zi, null);
        Myhodler myhodler=new Myhodler(view);
        return myhodler;
    }

    @Override
    public void onBindViewHolder(final Myhodler holder, final int position) {
        String images = list.get(position).getImages();
        String[] split = images.split(".jpg");
        holder.sd.setImageURI(split[0]+".jpg");
        holder.jiugongge_tv.setText(list.get(position).getTitle());
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
        return list.size();
    }

    class Myhodler extends RecyclerView.ViewHolder{
       public SimpleDraweeView sd;
       public TextView jiugongge_tv;
        public Myhodler(View itemView) {
            super(itemView);
            this.sd=itemView.findViewById(R.id.sd);
            this.jiugongge_tv=itemView.findViewById(R.id.jiugongge_tv);
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
