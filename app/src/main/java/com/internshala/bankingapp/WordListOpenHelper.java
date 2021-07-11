package com.internshala.bankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;

public class WordListOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    // has to be 1 first time or app will crash
    private static final String TABLE_NAME = "Banking_Table";
    private static final String DATABASE_NAME = "Banking.db";

    // Column names...
    public static final String KEY_ID = "_id";
    public static final String NAME = "Name";
    public static final String AMOUNT = "Amount";
    public static final String ACCOUNT_NO = "Account_Number";
    public static final String EMAIL = "email";
    public static final String IFSC_CODE = "IFSC_CODE";
    public static final String PHONE_NUM = "Phone_Number";

    // ... and a string array of columns.
    private static final String[] COLUMNS = {KEY_ID,NAME,AMOUNT,ACCOUNT_NO,EMAIL,IFSC_CODE,PHONE_NUM};

   private static final String WORD_LIST_TABLE_CREATE  =  "CREATE TABLE " + TABLE_NAME + " ("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME + " TEXT, "
            + AMOUNT + " INTEGER, "
            + ACCOUNT_NO + " TEXT, "
            + EMAIL + " TEXT, "
            + IFSC_CODE + " TEXT, "
            + PHONE_NUM + " TEXT);";

    public WordListOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "Construct WordListOpenHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {//create database
        sqLiteDatabase.execSQL(WORD_LIST_TABLE_CREATE); // Create the tables
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
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



    public void insertData(String name,int amt,String acc_no,String email,String ifsc_code,String phone_num){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMNS[1],name);
        contentValues.put(COLUMNS[2],amt);
        contentValues.put(COLUMNS[3],acc_no);
        contentValues.put(COLUMNS[4],email);
        contentValues.put(COLUMNS[5],ifsc_code);
        contentValues.put(COLUMNS[6],phone_num);
        db.insert(TABLE_NAME,null,contentValues);
    }

    public Cursor readRow(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + "=" + id;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
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

    public int updateDB(int amount,int id){
        Cursor cursor = readRow(id);
        cursor.moveToNext();
        int bankerAmt1 = cursor.getInt(2);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMNS[2],amount+bankerAmt1);
        db.update(TABLE_NAME, contentValues, KEY_ID + " = ?", new String[]{String.valueOf(id)});
        return amount+bankerAmt1;
    }

}