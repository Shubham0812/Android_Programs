package com.wolf.application2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b  = (Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });
    }
    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }
     @Override
    public boolean onOptionsItemSelected(MenuItem item){
         switch (item.getItemId()){
             case R.id.item1:
                 Toast.makeText(this, "Option 1 is selected", Toast.LENGTH_SHORT).show();
                 break;
             case R.id.item2:
                 Toast.makeText(this, "Options 2 is selected", Toast.LENGTH_SHORT).show();
                 break;
         }
         return super.onOptionsItemSelected(item);
     }
}
