package com.example.darshaun.bunkmate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Daydisplay extends AppCompatActivity {

    DatabaseHelper myDb;
    SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
    Date d = new Date();
    String day = (sdf.format(d)).toUpperCase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daydisplay);

        myDb = new DatabaseHelper(this);
        getSupportActionBar().setTitle(day);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent intent = new Intent(Daydisplay.this,display.class);
                startActivity(intent);
            }
        });
        shows();
    }

    public void shows(){
        int i=0;
        final String[] buffer = new String[20];
        Cursor result = myDb.gettimetable(day);
        int n=result.getCount();
        if(n==0){
            showMessage("HAPPY DAY","You don't have any class today");
            return;
        }
        //Toast.makeText(this,String.valueOf(n),Toast.LENGTH_SHORT).show();
        while (result.moveToNext()) {
            buffer[i++]=result.getString(0);
        }
        //showMessage("Data",buffer.toString());

        LinearLayout ll = (LinearLayout)findViewById(R.id.layout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for(int j=0;j<n;j++){
            Button btn = new Button(this);
            btn.setId(j);
            btn.setTextSize(20);
            btn.setHeight(200);
            int id_ = btn.getId();
            btn.setText(buffer[j]);
            ll.addView(btn, lp);
            Button btn1 = ((Button) findViewById(id_));
            final int finalJ = j;
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    //Toast.makeText(view.getContext(),buffer[finalJ], Toast.LENGTH_SHORT).show();
                    Intent bi = new Intent(Daydisplay.this,subject.class);
                    bi.putExtra("message",buffer[finalJ]);
                    startActivity(bi);
                }
            });
        }
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void timetabledisplay(View view){

        Intent intent = new Intent(Daydisplay.this,TimeTable.class);
        startActivity(intent);
    }

    public void attendance(View view){

        Intent intent = new Intent(Daydisplay.this, Attendance.class);
        startActivity(intent);
    }
}
