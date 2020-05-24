package com.example.therapydiaries;

import android.app.Application;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;

public class MyApplication extends Application {

//    SQLiteDatabase mDb;
    DBHelper mDBHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        mDBHelper = new DBHelper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
//
//        try {
//            mDb = mDBHelper.getWritableDatabase();
//        } catch (SQLException mSQLException) {
//            throw mSQLException;
//        }
    }
//
//    public SQLiteDatabase getmDb() {
//        return mDb;
//    }


    public DBHelper getmDBHelper() {
        return mDBHelper;
    }
}