package com.example.darshaun.bunkmate;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Day extends AppCompatActivity {

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        Bundle bundle = getIntent().getExtras();
        String day = bundle.getString("message");

        getSupportActionBar().setTitle(day);

        myDb = new DatabaseHelper(this);

        int i=0;
        int percentage[] = new int[20];
        final String[] buffer = new String[20];
        Cursor result = myDb.gettimetable(day);
        int n=result.getCount();
        if(n==0){
            showMessage("HAPPY DAY","You don't have any class today");
            return;
        }
        //Toast.makeText(this,String.valueOf(n),Toast.LENGTH_SHORT).show();
        while (result.moveToNext()) {
            String value = result.getString(0);
            buffer[i++]= value;
            Cursor values = myDb.getnos(value);
            while(values.moveToNext()){
                String a=values.getString(0);
                String b = values.getString(1);
                int ia = Integer.parseInt(a);
                int ib = Integer.parseInt(b);
                if(ia==0)
                    percentage[i-1]=5;
                else
                    percentage[i-1]=(ib*100)/ia;
            }
        }

        LinearLayout ll = (LinearLayout)findViewById(R.id.layout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for(int j=0;j<n;j++){
            TextView textView = new TextView(this);
            textView.setId(j);
            textView.setTextSize(20);
            textView.setHeight(200);
            if(percentage[j]<75)
                textView.setTextColor(Color.RED);
            else
                textView.setTextColor(Color.BLACK);
            textView.setText(buffer[j] + " - " + percentage[j] + "%");
            ll.addView(textView, lp);
        }
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
