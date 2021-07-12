package com.internshala.bankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    WordListOpenHelper wordListOpenHelper;
    TranscationsOpenHelper transcationsOpenHelper;
    MyAdapter mAdapter;

    //initial  data for banking table
    String [] namesArr = {"Thanu","Nella","Reddy","Anu","Varshitha","Shushritha","Chaithanya","Sipra","Vismitha","Harika","Amrina","Nisha","Suhana","Ragina","Deepa"};
    String [] accountNoArr = {"XXXXXX3412","XXXXXX3413","XXXXXX3414","XXXXXX3415","XXXXXX3416","XXXXXX3417","XXXXXX3418","XXXXXX3419","XXXXXX3420","XXXXXX3421","XXXXXX3422","XXXXXX3423","XXXXXX3424","XXXXXX3425","XXXXXX3426"};
    String [] emailArr = {"thanu@gmail.com","nella@gmail.com","reddy@gmail.com","anu@gmail.com","yashu@gmail.com","yashas@gmail.com","megana@gmail.com","vinayak@gmail.com","vachan@gmail.com","vaish@gmail.com","nisha@gmail.com","suhana@gmail.com","ragina@gmail.com","deepa@gmail.com"};
    String [] ifscArr = {"C001234","C001934","C001235","C001238","C001239","C001237","C001231","C001233","C001234","C001234","C001234","C001234","C001234","C001234","C001234"};
    String [] phoneNoArr = {"3452562345","7452562345","9452562345","8452562345","6452562345","9752562345","8652562345","6952562345","3452562346","5452562345","9492562345","8152562345","8972562345","4452562345","3452562745"};
    Integer curBalanceArr[] = new Integer[]{2000,20000,3000,4000,50000,6000,6999,50000,9000,4555,6000,6999,50000,9000,4555} ;
    List<String> mNameslist = Arrays.asList(namesArr);
    List<Integer> mAmtlist = Arrays.asList(curBalanceArr);
    List<String> macc_no_list = Arrays.asList(accountNoArr);
    List<String> memail_list = Arrays.asList(emailArr);
    List<String> mifsc_code_list = Arrays.asList(ifscArr);
    List<String> mphone_num_list = Arrays.asList(phoneNoArr);

    //lists for storing data that is retrieved from banking table
    List<String> nameslist = new ArrayList<>();
    List<Integer> amtlist = new ArrayList<>();
    List<String> acc_no_list = new ArrayList<>();
    List<String> email_list = new ArrayList<>();
    List<String> ifsc_code_list = new ArrayList<>();
    List<String> phone_num_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordListOpenHelper=new WordListOpenHelper(this);
        transcationsOpenHelper = new TranscationsOpenHelper(this);

        //insert initial data into banking table only if banking table is empty
        if(wordListOpenHelper.getItemCount() <= 0) {
            insetDataIntoDB();
        }

        //retrieving data from banking table
        displaydata();

        //Get a handle to the RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //Create an adapter and supply the data to be displayed.
        mAdapter = new MyAdapter(this,nameslist,amtlist,checkCallingActivity(),getIntent().getIntExtra("enteredAmt",-1),getIntent().getIntExtra("position",-1));
        //Connect the adapter with the recycler view.
        mRecyclerView.setAdapter(mAdapter);
        //Give the recycler view a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onTransactionsClick(MenuItem item) {
        Intent intent = new Intent(MainActivity.this,TranscationsActivity.class);
        startActivity(intent);
    }

    public void insetDataIntoDB(){
        for(int i = 0; i < mAmtlist.size(); i++){
            wordListOpenHelper.insertData(mNameslist.get(i),mAmtlist.get(i),macc_no_list.get(i),memail_list.get(i),mifsc_code_list.get(i),mphone_num_list.get(i));
        }
    }

    public void displaydata(){
        Cursor cursor = wordListOpenHelper.readAllData();
            while (cursor.moveToNext()){
                nameslist.add(cursor.getString(1));
                amtlist.add(cursor.getInt(2));
                acc_no_list.add(cursor.getString(3));
                email_list.add(cursor.getString(4));
                ifsc_code_list.add(cursor.getString(5));
                phone_num_list.add(cursor.getString(6));
            }
            cursor.close();
    }

    public int checkCallingActivity() {
        int callingActivity;
        callingActivity = getIntent().getIntExtra("callingActivity", 7);
        return callingActivity;
    }
    public void updateRecyclerView(Cursor cursor){
        amtlist.clear();
        System.out.println(amtlist);
        List<Integer> newAmtList = new ArrayList<>();
        while (cursor.moveToNext()){
            newAmtList.add(cursor.getInt(2));
        }
        cursor.close();
        amtlist.addAll(newAmtList);
        System.out.println(amtlist);
    }

}