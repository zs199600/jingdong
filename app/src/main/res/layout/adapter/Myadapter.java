package com.example.wodegai.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/2/25.
 */

public class Myadapter extends RecyclerView.Adapter<com.example.wodegai.adapter.Myadapter.Myhodler> {

    @Override
    public Myhodler onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(Myhodler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class Myhodler extends RecyclerView.ViewHolder{
        public Myhodler(View itemView) {
            super(itemView);
        }
    }
}
