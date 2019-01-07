package com.example.junaid.transpire.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

public class BmiDB extends SQLiteOpenHelper {
    public static final String BMI_COUNT="bmi_count";
    public static final String BMI="bmi";
    public static final String ID="id";
    public static final String BMIDB="bmi.db";
    public BmiDB(Context context) {
        super(context, BMIDB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+BMI+" (id INTEGER UNIQUE,"+BMI_COUNT+" REAL)");
        db.execSQL("insert into "+BMI+" ("+ID+","+BMI_COUNT+")values(1,1)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+BMIDB);


        onCreate(db);

    }
    public double getBmi()
    {
        SQLiteDatabase db=getReadableDatabase();
        SQLiteQueryBuilder qb= new SQLiteQueryBuilder();

        String[] sqlSelect={BMI_COUNT};
        String sqlTable=BMI;

        qb.setTables(sqlTable);
        Cursor c=qb.query(db,sqlSelect,null,null,null,null,null);
        c.moveToFirst();
        return c.getInt(c.getColumnIndex("bmi_count"));
    }
    public void insertBmi(float s)
    {
        /*SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COUNT,s);
        db.insert(STEP, null,contentValues);*/
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("UPDATE "+BMI+" SET "+BMI_COUNT+" ="+s+" WHERE "+ID+" = 1");
    }
}
