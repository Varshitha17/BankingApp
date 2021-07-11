package com.internshala.bankingapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserData extends AppCompatActivity {

    WordListOpenHelper wordListOpenHelper=new WordListOpenHelper(this);
    private Button button;
    String bankerAmt;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_data);
        getIncomingIntent();
        button = (Button)findViewById(R.id.transfer_money);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    public void openDialog(){
     DialogBox dialogBox = new DialogBox(bankerAmt,position);
     dialogBox.show(getSupportFragmentManager(),"Dialog Box");
    }

    private void getIncomingIntent(){
        position = getIntent().getIntExtra("position",-1);

        TextView name = findViewById(R.id.name);
        TextView balance = findViewById(R.id.avail_balance);
        TextView acc_no = findViewById(R.id.account_no);
        TextView email = findViewById(R.id.email_id);
        TextView ifsc_code = findViewById(R.id.ifsc_id);
        TextView phone_num = findViewById(R.id.phone_no);

        Cursor cursor = wordListOpenHelper.readRow(position+1);
        cursor.moveToNext();
        String bankerName=cursor.getString(1);
        bankerAmt = Integer.toString(cursor.getInt(2));

        String acc_num=cursor.getString(3);
        String email1=cursor.getString(4);
        String ifsc_code1=cursor.getString(5);
        String num_phone = cursor.getString(6);
        name.setText(bankerName);
        balance.setText(bankerAmt);
        acc_no.setText(acc_num);
        email.setText(email1);
        ifsc_code.setText(ifsc_code1);
        phone_num.setText(num_phone);
    }

}
