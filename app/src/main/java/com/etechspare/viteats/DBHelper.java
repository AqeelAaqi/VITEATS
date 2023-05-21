package com.etechspare.viteats;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper  extends SQLiteOpenHelper {

    public static final String DBNAME ="mohsin.db";
    public DBHelper(Context context){

        super(context, "mohsin.db" ,null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key,password TEXT, usertype TEXT, fullname TEXT)");

        MyDB.execSQL("CREATE TABLE review " +
                "(id integer primary key autoincrement," +
                "person_name text,"+
                "review text,"+
                "hotel_id text,"+
                "feedback int)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {

        MyDB.execSQL("drop Table if exists users");
        onCreate(MyDB);

    }

    public Boolean checkusernameById(String user){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("SELECT * FROM users WHERE username = ?", new String[]{user});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }
    public Boolean insertData(String username, String password, String usertype, String fullname) {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("usertype",usertype);
        contentValues.put("fullname",fullname);

        long result=MyDB.insert("users",null,contentValues);
        if(result==-1) {return false;}
        else{
            return true;}
    }
    public Boolean insertReviewData(String person_name, String review, String hotel_id, String feedback) {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("person_name",person_name);
        contentValues.put("review",review);
        contentValues.put("hotel_id",hotel_id);
        contentValues.put("feedback",feedback);

        long result=MyDB.insert("review",null,contentValues);
        if(result==-1) {return false;}
        else{
            return true;}
    }

    public ArrayList<ReviewModel> getReviewsData(String hotel_id){

        SQLiteDatabase MyDb=this.getWritableDatabase();
        Cursor cursor =MyDb.rawQuery("SELECT * FROM review WHERE hotel_id = ?",
                new String[] {hotel_id});
        // on below line we are creating a new array list.
        ArrayList<ReviewModel> courseModalArrayList
                = new ArrayList<>();
        // moving our cursor to first position.
        if (cursor.moveToFirst()) {
            do {
                // on below line we are adding the data from
                // cursor to our array list.
                courseModalArrayList.add(new ReviewModel(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)));
            } while (cursor.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursor.close();
        return courseModalArrayList;
    }
    public Boolean checkusernamepassword(String username,String password, String usertype){

        SQLiteDatabase MyDb=this.getWritableDatabase();
        Cursor cursor =MyDb.rawQuery("SELECT * FROM users WHERE username = ? AND password=? AND usertype=?",
                new String[] {username,password, usertype});
        if(cursor.getCount()>0){
            return true;}
        else {
            return false;
        }
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDb=this.getWritableDatabase();
        Cursor cursor =MyDb.rawQuery("SELECT * FROM users WHERE username = ? ",new String[] {username});
        if(cursor.getCount()>0){
            return true;}
        else {
            return false;
        }
    }

}