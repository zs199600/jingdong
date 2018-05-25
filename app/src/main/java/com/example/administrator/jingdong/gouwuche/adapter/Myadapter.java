package com.example.administrator.jingdong.gouwuche.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jingdong.R;
import com.example.administrator.jingdong.gouwuche.bean.Gouwuche;
import com.example.administrator.jingdong.gouwuche.view.Igouwuche;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2018/2/23.
 */

public class Myadapter extends BaseExpandableListAdapter {
    private Context context;
    private List<Gouwuche.DataBean> data;
    private Igouwuche iview;

    public Myadapter(Context context, List<Gouwuche.DataBean> data, Igouwuche iview) {
        this.context = context;
        this.data = data;
        this.iview = iview;
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Mypresenthodler mypresenthodler=null;
         if (convertView==null){
             convertView = View.inflate(context, R.layout.gouwuche_prenter, null);
             CheckBox prenter_cb = convertView.findViewById(R.id.prenter_cb);
             TextView prenter_tv = convertView.findViewById(R.id.prenter_tv);
             mypresenthodler=new Mypresenthodler(prenter_cb,prenter_tv);
             convertView.setTag(mypresenthodler);
         }else {
             mypresenthodler = (Mypresenthodler) convertView.getTag();
         }
         mypresenthodler.prenter_tv.setText(data.get(groupPosition).getSellerName());
         mypresenthodler.prenter_cb.setChecked(data.get(groupPosition).isPresenterck());

         //父类的点击事件
        mypresenthodler.prenter_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gouwuche.DataBean dataBean = data.get(groupPosition);
                boolean isCheked  = dataBean.isPresenterck();
                isCheked=!isCheked;
                dataBean.setPresenterck(isCheked);
                List<Gouwuche.Mybean> list = Myadapter.this.data.get(groupPosition).getList();
                for (int i = 0; i <list.size() ; i++) {
                   list.get(i).setChildck(isCheked);
                }
                iview.heji();
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
       Mychildhodler mychildhodler=null;
        if (convertView==null){
           convertView = View.inflate(context, R.layout.gouwuche_chiled, null);
           CheckBox child_ck = convertView.findViewById(R.id.child_ck);
           SimpleDraweeView child_sd = convertView.findViewById(R.id.child_sd);
           TextView cjild_name = convertView.findViewById(R.id.cjild_name);
           TextView cjild_price = convertView.findViewById(R.id.cjild_price);
           ImageView jian = convertView.findViewById(R.id.jian);
           EditText ed = convertView.findViewById(R.id.ed);
           ImageView jia = convertView.findViewById(R.id.jia);
           mychildhodler=new Mychildhodler(child_ck,child_sd,cjild_name,cjild_price,jian,ed,jia);
           convertView.setTag(mychildhodler);
       }else {
            mychildhodler= (Mychildhodler) convertView.getTag();
        }
        final Gouwuche.Mybean mybean = data.get(groupPosition).getList().get(childPosition);
        mychildhodler.child_ck.setChecked(mybean.isChildck());
        String images = mybean.getImages();
        String[] split = images.split(".jpg");
        mychildhodler.child_sd.setImageURI(Uri.parse(split[0]+".jpg"));
        mychildhodler.cjild_name.setText(mybean.getTitle());
        mychildhodler.cjild_price.setText(mybean.getPrice()+"");
        mychildhodler.ed.setText(mybean.getCount()+1+"");

        mychildhodler.child_ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCheked  = mybean.isChildck();
                isCheked=!isCheked;
                mybean.setChildck(isCheked);
                boolean flag = true;
                List<Gouwuche.Mybean> list = Myadapter.this.data.get(groupPosition).getList();
                for (int i = 0; i <list.size() ; i++) {
                    if (!list.get(i).isChildck()){
                        flag=false;
                    }

                }
                Myadapter.this.data.get(groupPosition).setPresenterck(flag);
                iview.heji();
                notifyDataSetChanged();
            }
        });

        mychildhodler.jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = mybean.getCount();
                mybean.setCount(++count);
                iview.heji();
                notifyDataSetChanged();
            }
        });

        mychildhodler.jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = mybean.getCount();
                if (count>0){
                    count--;
                    mybean.setCount(count);
                }else {
                    Toast.makeText(context,"不能再减了",Toast.LENGTH_SHORT).show();
                }
                iview.heji();
                notifyDataSetChanged();
            }

        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class Mychildhodler{
    public CheckBox child_ck;
    public SimpleDraweeView child_sd;
    public TextView cjild_name;
    public TextView cjild_price;
    public ImageView jian;
    public EditText ed;
    public ImageView jia;

        public Mychildhodler(CheckBox child_ck, SimpleDraweeView child_sd, TextView cjild_name, TextView cjild_price, ImageView jian, EditText ed, ImageView jia) {
            this.child_ck = child_ck;
            this.child_sd = child_sd;
            this.cjild_name = cjild_name;
            this.cjild_price = cjild_price;
            this.jian = jian;
            this.ed = ed;
            this.jia = jia;
        }
    }

    class Mypresenthodler{
        public CheckBox prenter_cb;
        public TextView prenter_tv;

        public Mypresenthodler(CheckBox prenter_cb, TextView prenter_tv) {
            this.prenter_cb = prenter_cb;
            this.prenter_tv = prenter_tv;
        }
    }
}
