package com.example.junaid.transpire.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

public class StepDB extends SQLiteOpenHelper {
    public static final String STEP="steps";
    public static final String COUNT="count";
    public static final String ID="id";
    public static final String STEP_DB_NAME="Step_count.db";




    public StepDB(Context context) {
        super(context, STEP_DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table steps (id INTEGER UNIQUE,count INTEGER)");
        db.execSQL("insert into steps ("+ID+","+COUNT+")values(1,1)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+STEP_DB_NAME);


        onCreate(db);

    }
    public int getSteps(){
        SQLiteDatabase db=getReadableDatabase();
        SQLiteQueryBuilder qb= new SQLiteQueryBuilder();

        String[] sqlSelect={COUNT};
        String sqlTable=STEP;

        qb.setTables(sqlTable);
        Cursor c=qb.query(db,sqlSelect,null,null,null,null,null);
        c.moveToFirst();
        return c.getInt(c.getColumnIndex("count"));
    }

    public void insertSteps(int s)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("UPDATE "+STEP+" SET "+COUNT+" ="+s+" WHERE "+ID+" = 1");
    }

}
