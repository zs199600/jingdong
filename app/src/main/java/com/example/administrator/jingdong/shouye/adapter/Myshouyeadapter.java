package com.example.administrator.jingdong.shouye.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jingdong.R;
import com.example.administrator.jingdong.kuangjia.Xiangqing;
import com.example.administrator.jingdong.shouye.bean.Feileileftbean;
import com.example.administrator.jingdong.shouye.bean.Shouyebean;
import com.example.administrator.jingdong.shouye.myview.NoticeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/24.
 */

public class Myshouyeadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //定义常量  确定多条目加载类型
    final static int TYPE_ONE=1;
    final static int TYPE_TWO=2;
    final static int TYPE_THREE=3;
    private Context context;
    private Shouyebean shouyebean;
    private List<Feileileftbean.DataBean> data;
    private ArrayList<String> notices;
    private long mHour = 02;
    private long mMin = 15;
    private long mSecond = 36;
    private boolean isRun = true;
    private Handler timeHandler;
    public Myshouyeadapter(Context context, Shouyebean shouyebean, List<Feileileftbean.DataBean> data) {
        this.context = context;
        this.shouyebean = shouyebean;
        this.data = data;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ONE:
                View inflate = View.inflate(context, R.layout.shouyerc, null);
                Feileihodler feileihodler=new Feileihodler(inflate);
                return feileihodler;
            case TYPE_TWO:
                View inflate1 = View.inflate(context, R.layout.shouye_tuijian, null);
                Shouyetuijianhodler shouyetuijianhodler=new Shouyetuijianhodler(inflate1);
                return shouyetuijianhodler;
          case TYPE_THREE:
                View inflate2 = View.inflate(context, R.layout.shouyemiaosha_item, null);
                Maoshahodler maoshahodler=new Maoshahodler(inflate2);
                return maoshahodler;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Feileihodler){
            Shouyefeileiadapter shouyefeileiadapter=new Shouyefeileiadapter(data,context);
            ((Feileihodler) holder).xrc.setAdapter(shouyefeileiadapter);
            ((Feileihodler) holder).xrc.setLayoutManager(new GridLayoutManager(context,2,GridLayoutManager.HORIZONTAL,false));
            final ArrayList<String> notices = new ArrayList<>();
            notices.add("大促销下单拆福袋，亿万新年红包随便拿");
            notices.add("家电五折团，抢十亿无门槛现金红包");
            notices.add("星球大战剃须刀首发送200元代金券");
           ((Feileihodler) holder).noticeView.addNotice(notices);
            ((Feileihodler) holder).noticeView.startFlipping();
            ((Feileihodler) holder).noticeView.setOnNoticeClickListener(new NoticeView.OnNoticeClickListener() {
                @Override
                public void onNotieClick(int position, String notice) {
                    Toast.makeText(context, notices.get(position),Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (holder instanceof Shouyetuijianhodler){
            ((Shouyetuijianhodler) holder).shouye_tuijian_tv.setText("京东推荐");
            Shouyebean.TuijianBean tuijian = shouyebean.getTuijian();
            final List<Shouyebean.TuijianBean.ListBean> list = tuijian.getList();
            Shouyetuijianadapter shouyetuijianadapter=new Shouyetuijianadapter(context,list);
            ((Shouyetuijianhodler) holder).shouye_tuijian_rc.setAdapter(shouyetuijianadapter);
            ((Shouyetuijianhodler) holder).shouye_tuijian_rc.setLayoutManager(new GridLayoutManager(context,1,GridLayoutManager.HORIZONTAL,false));
            shouyetuijianadapter.huidiao(new Shouyetuijianadapter.Itemclick() {
                @Override
                public void itemclick(View view, int position) {
                    int pid = (int) list.get(position).getPid();
                   Intent intent = new Intent(context, Xiangqing.class);
                    intent.putExtra("pid",pid+"");
                    context.startActivity(intent);
                }
            });
        }


        if (holder instanceof Maoshahodler){
            Shouyebean.MiaoshaBean miaosha = shouyebean.getMiaosha();
            final List<Shouyebean.MiaoshaBean.ListBeanX> miaoshaList = miaosha.getList();
            ((Maoshahodler) holder).shouue_miaosha_tv.setText("京东秒杀");
            ((Maoshahodler) holder).shouue_miaosha_tv1.setText("16点场");
            Shouyemiaoshaadapter shouyemiaoshaadapter=new Shouyemiaoshaadapter(context,miaoshaList);
            ((Maoshahodler) holder).miaosha_rv.setAdapter(shouyemiaoshaadapter);
            ((Maoshahodler) holder).miaosha_rv.setLayoutManager(new GridLayoutManager(context,2));
            shouyemiaoshaadapter.huidiao(new Shouyemiaoshaadapter.Itemclick() {
                @Override
                public void itemclick(View view, int position) {
                    int pid = (int) miaoshaList.get(position).getPid();
                    Intent intent = new Intent(context, Xiangqing.class);
                    intent.putExtra("pid",pid+"");
                    context.startActivity(intent);
                }
            });
            startRun();
            timeHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if (msg.what==1) {
                        computeTime();
                        if (mHour<10){
                            ((Maoshahodler) holder).tv_hour.setText("0"+mHour+"");
                        }else {
                            ((Maoshahodler) holder).tv_hour.setText("0"+mHour+"");
                        }
                        if (mMin<10){
                            ((Maoshahodler) holder).tv_minute.setText("0"+mMin+"");
                        }else {
                            ((Maoshahodler) holder).tv_minute.setText(mMin+"");
                        }
                        if (mSecond<10){
                            ((Maoshahodler) holder).tv_second.setText("0"+mSecond+"");
                        }else {
                            ((Maoshahodler) holder).tv_second.setText(mSecond+"");
                        }
                    }
                }
            };

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return TYPE_ONE;
        }
        if (position==1){
            return TYPE_TWO;
        }
        if (position==2) {
            return TYPE_THREE;
        }
        return 0;
    }
    class Maoshahodler extends RecyclerView.ViewHolder{
        public TextView shouue_miaosha_tv;
        public TextView shouue_miaosha_tv1;
        public TextView tv_hour;
        public TextView tv_minute;
        public TextView tv_second;
        public RecyclerView miaosha_rv;
        public Maoshahodler(View itemView) {
            super(itemView);
            this.shouue_miaosha_tv=itemView.findViewById(R.id.shouue_miaosha_tv);
            this.shouue_miaosha_tv1=itemView.findViewById(R.id.shouue_miaosha_tv1);
            this.tv_hour=itemView.findViewById(R.id.tv_hour);
            this.tv_minute=itemView.findViewById(R.id.tv_minute);
            this.tv_second=itemView.findViewById(R.id.tv_second);
            this.miaosha_rv=itemView.findViewById(R.id.miaosha_rv);
        }
    }
    class Shouyetuijianhodler extends RecyclerView.ViewHolder{
        public TextView shouye_tuijian_tv;
        public RecyclerView shouye_tuijian_rc;
        public Shouyetuijianhodler(View itemView) {
            super(itemView);
            this.shouye_tuijian_rc=itemView.findViewById(R.id.shouye_tuijian_rc);
            this.shouye_tuijian_tv=itemView.findViewById(R.id.shouye_tuijian_tv);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
    class Feileihodler extends RecyclerView.ViewHolder{
        public RecyclerView xrc;
        public NoticeView noticeView;
        public Feileihodler(View itemView) {
            super(itemView);
            this.xrc=itemView.findViewById(R.id.shouye_rc);
            this.noticeView=itemView.findViewById(R.id.notice_view);
        }
    }


    private void startRun() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (isRun) {
                    try {
                        Thread.sleep(1000); // sleep 1000ms
                        Message message = Message.obtain();
                        message.what = 1;
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    /**
     * 倒计时计算
     */
    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
            }
        }
    }
}
