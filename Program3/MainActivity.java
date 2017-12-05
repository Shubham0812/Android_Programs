package com.wolf.application3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName,etUsn;
    Button bsave,bview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName= (EditText) findViewById(R.id.name);
        etUsn= (EditText) findViewById(R.id.usn);
        bsave= (Button) findViewById(R.id.save);
        bview= (Button) findViewById(R.id.view);

        bview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it1=new Intent(MainActivity.this,Second.class);
                startActivity(it1);
            }
        });

        bsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String usn = etUsn.getText().toString();
                DBHelper dbh = new DBHelper(MainActivity.this);
                if(dbh.addData(name,usn)){
                    etName.setText("");
                    etUsn.setText("");
                    Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }}