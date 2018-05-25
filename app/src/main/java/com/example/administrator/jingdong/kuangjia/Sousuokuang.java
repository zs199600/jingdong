package com.example.administrator.jingdong.kuangjia;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jingdong.R;
import com.example.administrator.jingdong.bean.SqlUser;
import com.example.administrator.jingdong.gen.DaoSession;
import com.example.administrator.jingdong.gen.SqlUserDao;
import com.example.administrator.jingdong.myview.Myview;
import com.example.administrator.jingdong.util.GreenDaoUtils;

import org.greenrobot.greendao.query.Query;

import java.util.List;

import butterknife.BindView;

public class Sousuokuang extends BaseActivity {
    private String mNames[] = { "完美世界", "大主宰", "斗破苍穹", "绝世唐门", "你好啊", "遮天", "微微一笑很倾城", "何以笙箫默", "三生三世十里桃花", "琅琊榜",
            "天下", "刀剑神皇", "大漠谣", "回到明朝当王爷", "兵临天下", "谁的青春不迷茫" };
    @BindView(R.id.sou)
    EditText sou;
    @BindView(R.id.sousuo)
    TextView sousuo;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.qingkong)
    TextView qingkong;
    private SqlUserDao sqlUserDao;
    private List<SqlUser> list;
    private Mylistadapter mylistadapter;
    @Override
    int getContentview() {
        return R.layout.activity_sousuokuang;
    }

    @Override
    void initData() {
        DaoSession daoSession = GreenDaoUtils.getmInstance().getDaoSession();
        //增删改查都用  userDao来操作
        sqlUserDao = daoSession.getSqlUserDao();

        Query<SqlUser> query = sqlUserDao.queryBuilder().build();
        list = query.list();
        if (list.size() != 0) {
            mylistadapter = new Mylistadapter(Sousuokuang.this, list);
            lv.setAdapter(mylistadapter);
        }
    }

    @Override
    protected void btclick() {
        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sou1 = sou.getText().toString();
                SqlUser sqluser=new SqlUser(sou1);
                sqlUserDao.insert(sqluser);
                if (!sou1.equals("手机")&&(!sou1.equals("笔记本"))){
                    Toast.makeText(Sousuokuang.this,"搜索的商品不存在，请输入笔记本或手机",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent=new Intent(Sousuokuang.this,Sousuoxiangqing.class);
                    String sou2 = sou.getText().toString();
                    intent.putExtra("name",sou2);
                    startActivity(intent);
                    finish();
                }
            }
        });

        qingkong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlUserDao.deleteAll();
                Query<SqlUser> query = sqlUserDao.queryBuilder().build();
                list = query.list();
                mylistadapter = new Mylistadapter(Sousuokuang.this, list);
                lv.setAdapter(mylistadapter);
            }
        });
    }


    private void initChildViews() {
        Myview mv=findViewById(R.id.mv);
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

        //设置两个控件之间的距离
        lp.leftMargin = 10;
        lp.rightMargin = 10;
        lp.topMargin = 10;
        lp.bottomMargin = 10;
        for (int i = 0; i < mNames.length; i++) {
            TextView view = new TextView(this);
            view.setText(mNames[i]);
            view.setTextColor(Color.WHITE);
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.textshap));
            mv.addView(view, lp);
            final int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Sousuokuang.this,mNames[finalI],Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
}
