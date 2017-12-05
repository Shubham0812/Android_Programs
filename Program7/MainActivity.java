package com.wolf.application7;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button b;
    ListView lv;
    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactList = new ArrayList<>();
        lv= (ListView) findViewById(R.id.list);
        b= (Button) findViewById(R.id.fetch);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUrl = "https://api.androidhive.info/contacts/";
                new UrlHandler().execute(strUrl);
            }
        });
    }

    public class UrlHandler extends AsyncTask<String,Integer,String >{

        @Override
        protected String doInBackground(String... params) {
            String json_response = null;
            HashMap<String ,String> map;
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                InputStream s = new BufferedInputStream(connection.getInputStream());
                json_response = ConvertStream(s);
                if(json_response!=null){
                    JSONObject obj = new JSONObject(json_response);
                    JSONArray array = obj.getJSONArray("contacts");
                    for(int i=0;i<array.length();i++){
                        JSONObject c = array.getJSONObject(i);
                        map = new HashMap<>();
                        map.put("id",c.getString("id"));
                        map.put("name",c.getString("name"));
                        map.put("email",c.getString("email"));
                        contactList.add(map);
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }  catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected  void onPostExecute(String s){
            super.onPostExecute(s);
            ListAdapter adapter = new SimpleAdapter(MainActivity.this, contactList,R.layout.list_item,new String[]{"id","name","email"},new int[]{R.id.cid,R.id.cname,R.id.cemail});
            lv.setAdapter(adapter);
        }
        protected String ConvertStream(InputStream s){
            BufferedReader br = new BufferedReader(new InputStreamReader(s));
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                while((line=br.readLine())!=null){
                    sb.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }
    }

}
