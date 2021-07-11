package com.internshala.bankingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TranscationsAdapter extends RecyclerView.Adapter<TranscationsAdapter.RecyclerViewHolder>{
    LayoutInflater mInflator;
    List<String> t_namelist;
    List<String> r_namelist;
    List<Integer> r_amtlist;

    public TranscationsAdapter(Context context,List<String> t_namelist,List<String> r_namelist,List<Integer> r_amtlist) {
        mInflator = LayoutInflater.from(context);
        this.t_namelist = t_namelist;
        this.r_namelist = r_namelist;
        this.r_amtlist = r_amtlist;
    }

    @Override
    public TranscationsAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflator.inflate(R.layout.tranctions_recycler, parent, false);
        return new RecyclerViewHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(TranscationsAdapter.RecyclerViewHolder holder, int position) {
            String t_name = t_namelist.get(position);
            String r_name = r_namelist.get(position);
            int r_amt = r_amtlist.get(position);
            holder.text1.setText(t_name);
            holder.text2.setText(r_name);
            holder.text3.setText(Integer.toString(r_amt));
    }

    @Override
    public int getItemCount() {
        return t_namelist.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TranscationsAdapter mAdapter;
        TextView text1,text2,text3;
        public RecyclerViewHolder(View itemView,TranscationsAdapter adapter) {
            super(itemView);
            text1 = (TextView) itemView.findViewById(R.id.t_from_name);
            text2 = (TextView) itemView.findViewById(R.id.t_to_name);
            text3 = (TextView) itemView.findViewById(R.id.t_amount);
            this.mAdapter = adapter;
        }
    }
}
