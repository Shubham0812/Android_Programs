package com.wolf.application2;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    String contacts[]={"Ajay","Sachin","Sumit","Tarun","Yogesh"};
    Button b,bx;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        b = (Button)findViewById(R.id.button2);

        ListView listView1 = (ListView)findViewById(R.id.listView);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,contacts);
        listView1.setAdapter(adapter);
        registerForContextMenu(listView1);
        bx = (Button) findViewById(R.id.button3);
        bx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickShowAlert(bx);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd = new ProgressDialog(Main2Activity.this);
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pd.setMessage("Please Wait");
                pd.setTitle("Stupid");
                pd.setIndeterminate(false);
                pd.show();
                pd.setMax(100);
                pd.setProgress(00);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0;i<12;i++){
                        try {
                            if(pd.getProgress()== pd.getMax()){
                                Toast.makeText(Main2Activity.this, "Done", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            Thread.sleep(1000);
                            pd.incrementProgressBy(10);
                        } catch (Exception e) {
                        }
                    }
                    }
                }).start();
            }
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
       getMenuInflater().inflate(R.menu.context,menu);
        menu.setHeaderTitle("Select The Action");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getItemId()==R.id.i1){
            Toast.makeText(this, "Option1 babes", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId()==R.id.i2){
            Toast.makeText(this, "Options2 bitch", Toast.LENGTH_SHORT).show();
        }
        return  super.onContextItemSelected(item);
    }
    public void onClickShowAlert(View view){
        AlertDialog.Builder myalert = new AlertDialog.Builder(this);
        myalert.setTitle("Idiot Checker");
        myalert.setMessage("Are you an idiot ?");
        myalert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        myalert.setNegativeButton("NO!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog al = myalert.create();
        al.show();
    }
}
