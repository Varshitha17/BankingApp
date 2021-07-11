package com.internshala.bankingapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TranscationsActivity extends AppCompatActivity {

    TranscationsOpenHelper transcationsOpenHelper;
    RecyclerView mRecyclerView;
    TranscationsAdapter mAdapter;

    List<String> list1 = new ArrayList<>();
    List<String> list2 = new ArrayList<>();
    List<Integer> list3 = new ArrayList<>();
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transactions);
        transcationsOpenHelper = new TranscationsOpenHelper(this);
        textView = (TextView)findViewById(R.id.empty_text);
        if(transcationsOpenHelper.getItemCount() <= 0) {
            textView.setVisibility(View.VISIBLE);
        }
        else{
           displaydata();
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mAdapter = new TranscationsAdapter(this,list1,list2,list3);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void displaydata(){
        Cursor cursor = transcationsOpenHelper.readAllData();
        while (cursor.moveToNext()){
            list1.add(cursor.getString(1));
            list2.add(cursor.getString(2));
            list3.add(cursor.getInt(3));
        }
        cursor.close();
    }

}
