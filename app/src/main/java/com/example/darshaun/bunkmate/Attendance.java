package com.example.darshaun.bunkmate;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Attendance extends AppCompatActivity {

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        int i=0;
        int percentage[] = new int[20];
        final String[] buffer = new String[20];
        myDb = new DatabaseHelper(this);
        Cursor result = myDb.getids();
        int n=result.getCount();
        if(n==0){
            //showMessage("ERROR","Nothing Found");
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
}
