package com.example.junaid.transpire.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class DaysDB extends SQLiteOpenHelper {
    public static final String DAYS="day";
    public static final String ID="id";
    public static final String DB_DAYS="days.db";
    public static final String HIST="daily";

    public DaysDB(Context context) {
        super(context, DB_DAYS, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table daily (id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,day TEXT)");
        db.execSQL("insert into "+HIST+" ("+ID+","+DAYS+")values(1,1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+HIST);


        onCreate(db);
    }

    public List<String> getWorkoutDays()
    {

        SQLiteDatabase db=getReadableDatabase();
        SQLiteQueryBuilder qb= new SQLiteQueryBuilder();

        String[] sqlSelect={HIST};
        String sqlTable=DAYS;

        qb.setTables(sqlTable);
        Cursor c=qb.query(db,sqlSelect,null,null,null,null,null);
        List<String> result=new ArrayList<>();
        if(c.moveToFirst())
        {
            do{
                result.add(c.getString(c.getColumnIndex("day")));
            }while(c.moveToFirst());
        }
        return result;
    }

    public void saveDay(String value)
    {
        SQLiteDatabase db=getWritableDatabase();
        String query=String.format("INSERT INTO "+HIST+"("+DAYS+") VALUES( %s);",value);
        db.execSQL(query);

        //db.execSQL("UPDATE "+SETT+" SET "+COL+" ="+value+" WHERE "+ID+" = 1");
    }
}
