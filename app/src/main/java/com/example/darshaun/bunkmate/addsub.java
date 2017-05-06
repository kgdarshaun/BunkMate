package com.example.darshaun.bunkmate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class addsub extends AppCompatActivity {

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsub);
        getSupportActionBar().setTitle("Enter the subject");
        myDb = new DatabaseHelper(this);
    }
    
    public void addonesub(View v){
        EditText editText = (EditText)findViewById(R.id.editText2);
        String name = editText.getText().toString();
        if(name.equals(""))
        {
            Toast.makeText(addsub.this,"No value entered",Toast.LENGTH_SHORT).show();
        }
        else {
            boolean isInserted = myDb.insertData(name);
            if (isInserted)
                Toast.makeText(addsub.this, "Subject Added", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(addsub.this, "Subject already added", Toast.LENGTH_SHORT).show();
            editText.setText("");
            Intent intent = new Intent(addsub.this, display.class);
            finish();
            startActivity(intent);
        }
    }

    public void onBackPressed()
    {
        Intent i = new Intent(addsub.this,display.class);
        finish();
        startActivity(i);
    }

}
