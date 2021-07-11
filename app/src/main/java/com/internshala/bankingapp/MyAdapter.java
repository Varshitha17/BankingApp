package com.internshala.bankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.WordViewHolder> {

    LayoutInflater mInflator;
    List<String> mWordList;
    List<Integer> curBalanceList;
    private Context mContext;
    public int key;
    public int entered_amt;
    public WordListOpenHelper wordListOpenHelper;
    public TranscationsOpenHelper transcationsOpenHelper;
    public Context context;
    MainActivity mainActivity;
    int position1;

    public MyAdapter(Context context, List<String> mWordList, List<Integer> curBalanceList,int key,int entered_amt,int position1){
        this.context = context;
        wordListOpenHelper= new WordListOpenHelper(context);
        transcationsOpenHelper = new TranscationsOpenHelper(context);
        mInflator = LayoutInflater.from(context);
        this.mWordList = mWordList;
        this.curBalanceList = curBalanceList;
        this.mContext = context;
        this.key =key;
        this.entered_amt = entered_amt;
        this.mainActivity = new MainActivity();
        this.position1 = position1;
    }

    @NonNull
    @Override
    public MyAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflator.inflate(R.layout.recycler,parent,false);
        return new WordViewHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.WordViewHolder holder, int position) {
       String mname = mWordList.get(position);
       int mBalance = curBalanceList.get(position);
       holder.text1.setText(mname);
       holder.text2.setText(Integer.toString(mBalance));

       //UserData class calls the mainActivity
       if(key == 1) {
           holder.parentLayout.setOnClickListener(new View.OnClickListener(){
               @Override
               public void onClick(View view) {

                   Cursor cursor1 = wordListOpenHelper.readRow(position+1);
                   Cursor cursor2 = wordListOpenHelper.readRow(position1+1);
                   cursor1.moveToNext();
                   String r_name = cursor1.getString(1);
                   cursor2.moveToNext();
                   String t_name = cursor2.getString(1);
                   transcationsOpenHelper.insertData(t_name,r_name,entered_amt);

                   wordListOpenHelper.updateDB(entered_amt,position+1);
                   wordListOpenHelper.updateDB(-entered_amt,position1+1);
                   Cursor cursor = wordListOpenHelper.readAllData();
                   mainActivity.updateRecyclerView(cursor);


                   Intent intent = new Intent(mContext, MainActivity.class);
                   context.startActivity(intent);
               }
           });
       }
       //splash activity or any other activity calls the MainActivity
       else{
           holder.parentLayout.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(mContext, UserData.class);
                   intent.putExtra("position", position);
                   context.startActivity(intent);
               }
           });
       }
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    class WordViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout parentLayout;
        TextView text1,text2;
        MyAdapter mAdapter;
        public WordViewHolder(View itemView,MyAdapter adapter) {
            super(itemView);
            text1 = (TextView) itemView.findViewById(R.id.username);
            text2 = (TextView) itemView.findViewById(R.id.amount);
            mAdapter = adapter;
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }

    }
}
