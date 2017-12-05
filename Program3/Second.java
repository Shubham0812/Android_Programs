package com.wolf.application3;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import static com.wolf.application3.R.id.usn;

public class Second extends AppCompatActivity {

    ListView lv;
    DBHelper dbh;
    ArrayList<HashMap<String,String>> Data;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lv = (ListView)findViewById(R.id.lv);
        Data = new ArrayList<>();
        dbh = new DBHelper(this);
        HashMap<String,String> das;
        Cursor fetchedData = dbh.fetchData();
        while(fetchedData.moveToNext()){
            das = new HashMap<>();
            String nm = fetchedData.getString(0);
            String us = fetchedData.getString(1);
            das.put("name",nm);
            das.put("usn",us);
            Data.add(das);
        }
        ListAdapter adapter = new SimpleAdapter(this,Data,R.layout.list_item,new String[] {"name","usn"},new int[]{R.id.tvName,R.id.tvUsn});
        lv.setAdapter(adapter);
        }
    }

