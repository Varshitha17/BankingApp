package com.internshala.bankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import static android.content.ContentValues.TAG;


public class TranscationsOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // has to be 1 first time or app will crash
    private static final String TABLE_NAME = "Transactions_Table";
    private static final String DATABASE_NAME = "Banking_Database";

    // Column names...
    public static final String KEY_ID = "_id";
    public static final String TransfererNAME = "T_Name";
    public static final String ReceiverNAME = "R_Name";
    public static final String AMOUNT = "Amount";

    // ... and a string array of columns.
    private static final String[] COLUMNS = {KEY_ID,TransfererNAME,ReceiverNAME,AMOUNT};

    private static final String TRANSACTION_LIST_TABLE_CREATE  =  "CREATE TABLE " + TABLE_NAME + " ("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TransfererNAME + " TEXT, "
            + ReceiverNAME + " TEXT, "
            + AMOUNT + " INTEGER);";

    public TranscationsOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "Construct TransactionsOpenHelper");
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TRANSACTION_LIST_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertData(String t_name,String r_name,int amt){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMNS[1],t_name);
        contentValues.put(COLUMNS[2],r_name);
        contentValues.put(COLUMNS[3],amt);
        db.insert(TABLE_NAME,null,contentValues);
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    public Cursor readRow(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + "=" + id;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

    public int getItemCount()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String count = "SELECT count(*) FROM " + TABLE_NAME;
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        return icount;
    }

}
