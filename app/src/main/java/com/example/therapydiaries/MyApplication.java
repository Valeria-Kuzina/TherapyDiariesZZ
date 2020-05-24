package com.example.therapydiaries;

import android.app.Application;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;

public class MyApplication extends Application {

//    SQLiteDatabase mDb;
    DBHelper mDBHelper;
    int userId;
    String mode;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}