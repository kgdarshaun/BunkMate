package com.example.darshaun.bunkmate;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class display extends AppCompatActivity {

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        myDb = new DatabaseHelper(this);
        getSupportActionBar().setTitle("SUBJECTS");
        displays();
    }

    public void displays() {
        int i=0;
        final String[] buffer = new String[20];
        Cursor result = myDb.getids();
        int n=result.getCount();
        if(n==0){
            showMessage("ERROR","Nothing Found");
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
                    Intent bi = new Intent(display.this,subject.class);
                    bi.putExtra("message",buffer[finalJ]);
                    startActivity(bi);
                    finish();
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
}
