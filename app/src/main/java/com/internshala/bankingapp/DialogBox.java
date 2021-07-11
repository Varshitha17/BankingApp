package com.internshala.bankingapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import org.w3c.dom.ls.LSOutput;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogBox extends AppCompatDialogFragment {
    private EditText editText;
    int bankerAmt;
    int position;
    public DialogBox(String amt2,int position){
        this.bankerAmt = Integer.parseInt(amt2);
        this.position = position;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);
        editText = view.findViewById(R.id.enter_money);
        builder.setView(view)
                .setTitle("ENTER AMOUNT")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Send",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String amt = editText.getText().toString();
                        int amt1 = Integer.parseInt(amt);
                       if(amt1 < bankerAmt) {
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            intent.putExtra("callingActivity",1);
                            intent.putExtra("enteredAmt",amt1);
                            intent.putExtra("position",position);
                            startActivity(intent);
                        }
                    }
                    });
         return builder.create();

    }
}
