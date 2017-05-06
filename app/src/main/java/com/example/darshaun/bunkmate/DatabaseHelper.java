package com.example.darshaun.bunkmate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.id;

/**
 * Created by Darshaun on 30-Apr-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME="subject.db";
    public static final String TABLE_NAME="subject_table";
    public static final String ID="cid";
    public static final String classes="classes";
    public static final String attended="attended";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"("+ID+" varchar(10) primary key, "+classes+" integer, "+attended+" integer)");
        db.execSQL("create table req(percen integer)");
        db.execSQL("create table timetable(day varchar(10), cid varchar(10) references subject_table)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        db.execSQL("drop table if exists req");
        db.execSQL("drop table if exists timetable");
        onCreate(db);
    }

    public void dropall(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("drop table "+TABLE_NAME);
        db.execSQL("drop table req");
        db.execSQL("drop table timetable");
        onCreate(db);
    }

    public boolean insertPercen(int p){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("percen",p);
        long result = db.insert("req",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean insertData(String cid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,cid);
        contentValues.put(classes,0);
        contentValues.put(attended,0);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public int getpercen(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select percen from req",null);
        res.moveToFirst();
        int p = res.getInt(0);
        return p;
    }

    public Cursor getids(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select cid from "+TABLE_NAME,null);
        return res;
    }

    public Cursor getnos(String cid){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select classes, attended from "+TABLE_NAME+" where cid='"+cid+"'",null);
        return res;
    }

    public boolean updatenos(String cid,int tc,int ac){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,cid);
        contentValues.put(classes,tc);
        contentValues.put(attended,ac);
        db.update(TABLE_NAME,contentValues,"CID = ?",new String[] { cid });
        return true;
    }

    public boolean inserttimetable(String day, String cid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("day",day);
        contentValues.put("cid",cid);
        long result = db.insert("timetable",null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor gettimetable(String day){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select cid from timetable where day='"+day+"'",null);
        return res;
    }

}