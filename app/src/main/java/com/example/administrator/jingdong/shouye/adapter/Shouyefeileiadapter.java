package com.example.administrator.jingdong.shouye.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.jingdong.R;
import com.example.administrator.jingdong.shouye.bean.Feileileftbean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2018/2/10.
 */

public class Shouyefeileiadapter extends RecyclerView.Adapter<Shouyefeileiadapter.Shouyefeileihodler> {
    private List<Feileileftbean.DataBean> data;
    private Context context;

    public Shouyefeileiadapter(List<Feileileftbean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;

    }

    @Override
    public Shouyefeileihodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.shouye_jiugongge, null);
        Shouyefeileihodler shouyefeileihodler=new Shouyefeileihodler(view);
        return shouyefeileihodler;
    }

    @Override
    public void onBindViewHolder(Shouyefeileihodler holder, int position) {
         holder.sd.setImageURI(Uri.parse(data.get(position).getIcon()));
         holder.jiugongge_tv.setText(data.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Shouyefeileihodler extends RecyclerView.ViewHolder{
        public SimpleDraweeView sd;
         public TextView  jiugongge_tv;
        public Shouyefeileihodler(View itemView) {
            super(itemView);
            this.sd=itemView.findViewById(R.id.sd);
            this.jiugongge_tv=itemView.findViewById(R.id.jiugongge_tv);
        }
    }
}
