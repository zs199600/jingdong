package com.example.administrator.jingdong.shouye.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.jingdong.R;
import com.example.administrator.jingdong.shouye.bean.Shouyebean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2018/2/11.
 */

public class Shouyetuijianadapter extends RecyclerView.Adapter <Shouyetuijianadapter.Myshouyetuijianhodler>{
    private Context context;
    List<Shouyebean.TuijianBean.ListBean> list;
    private Itemclick item;

    public Shouyetuijianadapter(Context context, List<Shouyebean.TuijianBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Myshouyetuijianhodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.shouye_tuijian_zi, null);
        Myshouyetuijianhodler myshouyetuijianhodler=new Myshouyetuijianhodler(inflate);
        return myshouyetuijianhodler;
    }

    @Override
    public void onBindViewHolder(final Myshouyetuijianhodler holder, final int position) {
        String images = list.get(position).getImages();
        String[] split = images.split(".jpg");
        holder.sd.setImageURI(Uri.parse(split[0]+".jpg"));
          holder.jiugongge_tv.setText(list.get(position).getPrice()+"");
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
        return list.size();
    }

    class Myshouyetuijianhodler extends RecyclerView.ViewHolder{
       public SimpleDraweeView sd;
       public TextView jiugongge_tv;
        public Myshouyetuijianhodler(View itemView) {
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
