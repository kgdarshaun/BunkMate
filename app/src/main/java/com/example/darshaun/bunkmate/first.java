package com.example.darshaun.bunkmate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class first extends AppCompatActivity {

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        getSupportActionBar().setTitle("Enter the subjects");
        myDb = new DatabaseHelper(this);
        Button button = (Button)findViewById(R.id.button2);
        button.setEnabled(false);
        EditText editText = (EditText)findViewById(R.id.editText);
        editText.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

    }

    public void adds(View v){
        EditText editText = (EditText)findViewById(R.id.editText);
        String name = editText.getText().toString();
        if(name.equals(""))
        {
            Toast.makeText(first.this,"No value entered",Toast.LENGTH_SHORT).show();
        }
        else {
            boolean isInserted = myDb.insertData(name);
            if (isInserted)
                Toast.makeText(first.this, "Subject Added", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(first.this, "Subject already Added", Toast.LENGTH_SHORT).show();
            editText.setText("");
            Button button = (Button)findViewById(R.id.button2);
            button.setEnabled(true);
        }
    }

    public void gos(View v) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("stopped","first");
        editor.commit();

        Intent in = new Intent(first.this, Minimum.class);
        startActivity(in);
        finish();
    }
}