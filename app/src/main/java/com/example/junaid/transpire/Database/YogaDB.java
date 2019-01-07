package com.example.junaid.transpire.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.view.Display;
import android.widget.Toast;

import com.example.junaid.transpire.ViewExercise;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class YogaDB extends SQLiteOpenHelper
{
    public static final String DB_NAME="trans.db";
    public static final String SETT="Setting";
    private static final int DB_VER=1;
    public static final String ID="id";
    public static final String COL="MODE";


    public static final String STEP="steps";
    public static final String COUNT="count";


   /* private static final String CON="COUNT";
    private static final String CONCOL="concol";*/


    public YogaDB(Context context) {
        super(context, DB_NAME, null,DB_VER);
}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+SETT+ "(id INTEGER,Mode INTEGER UNIQUE)");
        db.execSQL("insert into "+SETT+"("+ID+","+COL+")values(1,0)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+DB_NAME);

        onCreate(db);

    }
    public int getSettingMode(){
        SQLiteDatabase db=getReadableDatabase();
        SQLiteQueryBuilder qb= new SQLiteQueryBuilder();

        String[] sqlSelect={COL};
        String sqlTable=SETT;

        qb.setTables(sqlTable);
        Cursor c=qb.query(db,sqlSelect,null,null,null,null,null);
        c.moveToFirst();
        return c.getInt(c.getColumnIndex("Mode"));



    }



    public void saveSettingMode(Integer value)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("UPDATE "+SETT+" SET "+COL+" ="+value+" WHERE "+ID+" = 1");
    }



}
