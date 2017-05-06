package com.example.darshaun.bunkmate;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class subject extends AppCompatActivity {

    DatabaseHelper myDb = new DatabaseHelper(this);;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        Bundle bundle = getIntent().getExtras();
        String course = bundle.getString("message");

        getSupportActionBar().setTitle(course);

        //Toast.makeText(this,course, Toast.LENGTH_SHORT).show();

        TextView tclass = (TextView)findViewById(R.id.textView11);
        TextView aclass = (TextView)findViewById(R.id.textView13);
        TextView percen = (TextView)findViewById(R.id.textView15);
        TextView bunkpos = (TextView)findViewById(R.id.textView4);

        int perc = myDb.getpercen();

        Cursor result = myDb.getnos(course);
        int n=result.getCount();
        if(n==0){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("ERROR");
            builder.setMessage("No Value Found");
            builder.show();
            return;
        }
        while (result.moveToNext()) {
            String[] temp = new String[2];
            temp[0] = result.getString(0);
            temp[1] = result.getString(1);
            tclass.setText(temp[0]);
            aclass.setText(temp[1]);
            int tc = Integer.parseInt(temp[0]);
            int ac = Integer.parseInt(temp[1]);
            int percentage;
            if (tc == 0)
                percentage = 0;
            else
                percentage = (ac * 100) / tc;
            percen.setText(percentage + "%");

            int future = (ac*100)/(tc+1);
            if(future<=perc) {
                bunkpos.setText("NOT bunk this class");
                bunkpos.setTextColor(Color.rgb(200, 0, 0));
            }
            else {
                bunkpos.setText("can bunk this class");
                bunkpos.setTextColor(Color.rgb(0,200,0));
            }
        }
    }


    public void attends(View v){

        Bundle bundle = getIntent().getExtras();
        String course = bundle.getString("message");

        //Toast.makeText(this,course, Toast.LENGTH_SHORT).show();

        TextView tclass = (TextView)findViewById(R.id.textView11);
        TextView aclass = (TextView)findViewById(R.id.textView13);
        TextView bunkpos = (TextView)findViewById(R.id.textView4);
        Button abutton = (Button)findViewById(R.id.button7);
        Button bbutton = (Button)findViewById(R.id.button8);

        int tc = Integer.parseInt(tclass.getText().toString());
        int ac = Integer.parseInt(aclass.getText().toString());

        myDb.updatenos(course,tc+1,ac+1);
        updatescreen();
        abutton.setEnabled(false);
        bbutton.setEnabled(false);

        int perc = myDb.getpercen();

        int future = ((ac+1)*100)/(tc+2);
        if(future<=perc) {
            bunkpos.setText("NOT bunk next class");
            bunkpos.setTextColor(Color.rgb(200, 0, 0));
        }
        else {
            bunkpos.setText("can bunk next class");
            bunkpos.setTextColor(Color.rgb(0, 200, 0));
        }
    }

    public void bunks(View v){

        Bundle bundle = getIntent().getExtras();
        String course = bundle.getString("message");

        //Toast.makeText(this,course, Toast.LENGTH_SHORT).show();

        TextView tclass = (TextView)findViewById(R.id.textView11);
        TextView aclass = (TextView)findViewById(R.id.textView13);
        TextView bunkpos = (TextView)findViewById(R.id.textView4);
        Button abutton = (Button)findViewById(R.id.button7);
        Button bbutton = (Button)findViewById(R.id.button8);

        int tc = Integer.parseInt(tclass.getText().toString());
        int ac = Integer.parseInt(aclass.getText().toString());

        myDb.updatenos(course,tc+1,ac);
        updatescreen();
        abutton.setEnabled(false);
        bbutton.setEnabled(false);

        int perc = myDb.getpercen();

        int future = (ac*100)/(tc+2);
        if(future<=perc) {
            bunkpos.setText("NOT bunk next class");
            bunkpos.setTextColor(Color.rgb(200, 0, 0));
        }
        else {
            bunkpos.setText("can bunk next class");
            bunkpos.setTextColor(Color.rgb(0, 200, 0));
        }
    }

    public void updatescreen(){

        TextView tclass = (TextView)findViewById(R.id.textView11);
        TextView aclass = (TextView)findViewById(R.id.textView13);
        TextView percen = (TextView)findViewById(R.id.textView15);
        TextView bunkpos = (TextView)findViewById(R.id.textView4);

        Bundle bundle = getIntent().getExtras();
        String course = bundle.getString("message");

        int perc = myDb.getpercen();

        Cursor result = myDb.getnos(course);
        int n=result.getCount();
        if(n==0){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("ERROR");
            builder.setMessage("No Value Found");
            builder.show();
            return;
        }
        while (result.moveToNext()) {
            String[] temp = new String[2];
            temp[0]=result.getString(0);
            temp[1]=result.getString(1);
            tclass.setText(temp[0]);
            aclass.setText(temp[1]);
            int tc = Integer.parseInt(temp[0]);
            int ac = Integer.parseInt(temp[1]);
            int percentage;
            if(tc==0)
                percentage=0;
            else
                percentage = (ac*100)/tc;
            percen.setText(percentage+"%");

            int future = (ac*100)/(tc+1);
            if(future<=perc) {
                bunkpos.setText("NOT bunk this class");
                bunkpos.setTextColor(Color.rgb(200, 0, 0));
            }
            else {
                bunkpos.setText("can bunk this class");
                bunkpos.setTextColor(Color.rgb(0, 200, 0));
            }
        }

    }

}