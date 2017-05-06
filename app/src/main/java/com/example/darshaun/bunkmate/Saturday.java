package com.example.darshaun.bunkmate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Saturday extends AppCompatActivity {

    DatabaseHelper myDb;
    int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saturday);
        getSupportActionBar().setTitle("SATURDAY");

        myDb = new DatabaseHelper(this);
        int i=0;
        final String[] buffer = new String[20];
        Cursor result = myDb.getids();
        n=result.getCount();
        if(n==0){
            //showMessage("ERROR","Nothing Found");
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
            CheckBox btn = new CheckBox(this);
            btn.setId(j);
            btn.setTextSize(20);
            btn.setHeight(200);
            int id_ = btn.getId();
            btn.setText(buffer[j]);
            ll.addView(btn, lp);
            CheckBox btn1 = ((CheckBox) findViewById(id_));
            final int finalJ = j;
        }
    }

    public void next(View v){

        for(int i=0;i<n;i++){
            CheckBox checkBox = (CheckBox) findViewById(i);
            if(checkBox.isChecked()) {
                //Toast.makeText(this,checkBox.getText(),Toast.LENGTH_SHORT).show();
                boolean check = myDb.inserttimetable("SATURDAY", (String) checkBox.getText());
                if (!check)
                    Toast.makeText(this, "ERROR!!!", Toast.LENGTH_SHORT).show();
            }
        }

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("stopped","saturday");
        editor.putBoolean("finish",true);
        editor.commit();

        Intent intent = new Intent(this, Loading.class);
        startActivity(intent);
        finish();
    }
}
