package com.example.administrator.jingdong.wode.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.jingdong.R;
import com.example.administrator.jingdong.wode.presenter.Mypresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class User_info extends AppCompatActivity implements View.OnClickListener,Iupuser {
  @BindView(R.id.sd)
    SimpleDraweeView sd;
  @BindView(R.id.wode_tv)
  TextView wode_tv;
  @BindView(R.id.userInfo_btn)
    Button userInfo_btn;
  @BindView(R.id.uptou)
    LinearLayout uptou;
  @BindView(R.id.upname)
    LinearLayout upname;
    private PopupWindow popupWindow;
    private static String path = "/sdcard/myHead/";// sd路径

    private SharedPreferences ji;
    private String uid;
    private String icon;
    private String nickname;
    private Uri uri;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        initdata();
        uptou.setOnClickListener(this);
        userInfo_btn.setOnClickListener(this);
        upname.setOnClickListener(this);

    }

    private void initdata() {
        ji = getSharedPreferences("ji", Context.MODE_PRIVATE);

        Intent intent = getIntent();
        icon = intent.getStringExtra("touxiang");
        nickname = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        if (icon !=null|| nickname !=null){
            wode_tv.setText(nickname);
            if (icon ==null){
                sd.setImageURI(Uri.parse("res://com.example.wode/" + R.drawable.user));
            }else {
                sd.setImageURI(icon);
            }
        }
        else {
            sd.setImageURI(Uri.parse("res://com.example.wode/" + R.drawable.user));
            wode_tv.setText(username);

        }

    }

    @Override
    public void onClick(View v) {
        	switch (v.getId()) {
        			case R.id.uptou:
                        View view = View.inflate(User_info.this, R.layout.popupwindow, null);
                //view为xml布局文件
                popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                //必须设置
                popupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
                popupWindow.setOutsideTouchable(true);
                //显示在底部  main为activity_main布局控件中 最大的LinearLayout 的id
                popupWindow.showAtLocation(findViewById(R.id.main), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                Button bt = view.findViewById(R.id.bt);
                Button bt1 = view.findViewById(R.id.bt1);
                Button bt2 = view.findViewById(R.id.bt2);

                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent2.putExtra(MediaStore.EXTRA_OUTPUT,
                                Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                        startActivityForResult(intent2, 2);// 采用ForResult打开
                        popupWindow.dismiss();
                    }
                });

                bt1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent, 1);
                        popupWindow.dismiss();
                    }
                });

                bt2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                        break;
                case R.id.userInfo_btn:
                    Intent intent = getIntent();
               setResult(8,intent);
                 finish();
                 break;
                case R.id.upname:
                  Intent  intent1=new Intent(User_info.this,Upname_info.class);
                    startActivityForResult(intent1,1);

        			}
    }


    @Override
    public void uptouxiang() {
        sd.setImageURI(uri);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK) {
            //图库照片的路径
            if (requestCode == 1) {
                //裁剪
                cropPhoto(data.getData());
            }

            //相机的路径为f File f=new File(Environment.getExternalStorageDirectory(),"ni.jpg");
            if (requestCode == 2) {
                File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                cropPhoto(Uri.fromFile(temp));// 裁剪图片

            }

            if (requestCode == 3) {
                if (data != null) {
                    Bundle extras = data.getExtras();
                    Bitmap head = extras.getParcelable("data");
                    if (head != null) {
                        setPicToView(head);// 保存在SD卡中
                        uri = Uri.parse(MediaStore.Images.Media.insertImage(this.getContentResolver(), head, null, null));
                        Mypresenter mypresenter = new Mypresenter(User_info.this);
                        File f = new File(fileName);
                        mypresenter.uptouxiang(ji.getString("uid", ""), f);
                    }
                }
            }
        }

        if (resultCode==6){
            String upname = data.getStringExtra("upname");
            wode_tv.setText(upname);
        }
    }

    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        fileName = path + "head.jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
