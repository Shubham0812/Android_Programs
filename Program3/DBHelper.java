package com.wolf.application3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.ContextMenu;

/**
 * Created by Wolf on 04-12-2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context){
        super(context,"StudDb",null,3);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table student (name text,usn text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("drop table student");
        onCreate(db);
    }

    public boolean addData(String item1,String item2){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",item1);
        cv.put("usn",item2);
        Long l = db.insert("student",null,cv);
        if(l==-1)
            return false;
        else
            return true;
    }

    public Cursor fetchData(){
        Cursor curData = null;
        SQLiteDatabase db = getWritableDatabase();
        curData=db.rawQuery("select * from student",null);
        return curData;
    }
}
