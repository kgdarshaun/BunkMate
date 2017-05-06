package com.example.darshaun.bunkmate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Minimum extends AppCompatActivity {

    DatabaseHelper myDb = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minimum);
        Button addsr = (Button)findViewById(R.id.button4);
    }


    public void addsminper(View v){
        EditText editText = (EditText)findViewById(R.id.editText3);
        Integer minp=Integer.parseInt(editText.getText().toString());
        boolean isInserted = myDb.insertPercen(minp);
        if (isInserted) {
            Toast.makeText(Minimum.this, "Done", Toast.LENGTH_SHORT).show();

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("stopped","minimum");
            editor.commit();

            Intent intent = new Intent(Minimum.this,Monday.class);
            startActivity(intent);
            finish();
        }
        else
            Toast.makeText(Minimum.this, "Please Enter again", Toast.LENGTH_SHORT).show();
    }
}
