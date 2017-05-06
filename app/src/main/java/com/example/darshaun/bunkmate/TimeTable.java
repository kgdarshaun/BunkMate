package com.example.darshaun.bunkmate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;

public class TimeTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
    }

    public void showmon(View view){
        Intent intent = new Intent(TimeTable.this,Day.class);
        intent.putExtra("message","MONDAY");
        startActivity(intent);
    }

    public void showtues(View view){
        Intent intent = new Intent(TimeTable.this,Day.class);
        intent.putExtra("message","TUESDAY");
        startActivity(intent);
    }

    public void showwed(View view){
        Intent intent = new Intent(TimeTable.this,Day.class);
        intent.putExtra("message","WEDNESDAY");
        startActivity(intent);
    }

    public void showthurs(View view){
        Intent intent = new Intent(TimeTable.this,Day.class);
        intent.putExtra("message","THURSDAY");
        startActivity(intent);
    }

    public void showfri(View view){
        Intent intent = new Intent(TimeTable.this,Day.class);
        intent.putExtra("message","FRIDAY");
        startActivity(intent);
    }

    public void showsat(View view){
        Intent intent = new Intent(TimeTable.this,Day.class);
        intent.putExtra("message","SATURDAY");
        startActivity(intent);
    }

}
