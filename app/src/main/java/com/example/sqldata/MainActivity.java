package com.example.sqldata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    Button viewData;
    Button add;
    EditText data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewData = (Button) findViewById(R.id.viewData);
        add = (Button) findViewById(R.id.add);
        data = (EditText) findViewById(R.id.data);
        mDatabaseHelper = new DatabaseHelper(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newEntry = data.getText().toString();
                if(newEntry.length()!=0){
                    AddData(newEntry);
                    data.setText("");
                }
                else{
                    Toast.makeText(getApplicationContext(),"Enter Something",Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ListDataActivity.class);
                startActivity(intent);
            }
        });
    }

    public void AddData(String newEntry){
        boolean insertData = mDatabaseHelper.addData(newEntry);
        if(insertData){
            Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Fail",Toast.LENGTH_SHORT).show();
        }
    }
}