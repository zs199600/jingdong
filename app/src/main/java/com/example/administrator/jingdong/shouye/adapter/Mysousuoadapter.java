package com.example.administrator.jingdong.shouye.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.jingdong.R;
import com.example.administrator.jingdong.shouye.bean.Sousuoxiangqingbean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2018/2/9.
 */

public class Mysousuoadapter extends RecyclerView.Adapter<Mysousuoadapter.Mysousuohodler>{
    private Context context;
    List<Sousuoxiangqingbean.DataBean> list;
    private int i;
    private Itemclick item;


    public Mysousuoadapter(Context context, List<Sousuoxiangqingbean.DataBean> list, int i) {
        this.context = context;
        this.list = list;
        this.i = i;


    }

    public List<Sousuoxiangqingbean.DataBean> getList() {
        return list;
    }

    public void setList(List<Sousuoxiangqingbean.DataBean> list) {
        this.list = list;
    }

    @Override
    public Mysousuohodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context,i,null);
        Mysousuohodler mysousuohodler=new Mysousuohodler(inflate);

        return mysousuohodler;
    }

    @Override
    public void onBindViewHolder(final Mysousuohodler holder, final int position) {
        String url = list.get(position).getImages();
        String[] split = url.split(".jpg");
        holder.sd.setImageURI(Uri.parse(split[0]+".jpg"));
        holder.xiangqing_tv.setText(list.get(position).getTitle());
        holder.xiangqing_tv1.setText(list.get(position).getPrice()+"");
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

    class Mysousuohodler extends RecyclerView.ViewHolder{
        public SimpleDraweeView sd;
        public TextView xiangqing_tv;
        public TextView xiangqing_tv1;
        public Mysousuohodler(View itemView) {
            super(itemView);
            this.sd=itemView.findViewById(R.id.sd);
            this.xiangqing_tv=itemView.findViewById(R.id.xiangqing_tv);
            this.xiangqing_tv1=itemView.findViewById(R.id.xiangqing_tv1);
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
