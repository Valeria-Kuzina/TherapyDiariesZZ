package com.example.therapydiaries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DiaryActivity extends AppCompatActivity {

    private static final String TAG = "DiaryActivity";
    ArrayList<String> rows;
    ListView list;
    SQLiteDatabase mDb;
    String diaryType;
    int userId;
    FloatingActionButton createRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        diaryType = getIntent().getStringExtra("diary_type");
        list = findViewById(R.id.list_diary);
        createRow = findViewById(R.id.button_new_row);
        mDb = ((MyApplication) this.getApplication()).getmDBHelper().getWritableDatabase();
        userId = ((MyApplication) this.getApplication()).getUserId();
        createRow.setOnClickListener(v -> newRowClicked());
    }

    private void newRowClicked() {
        Intent intent = new Intent(DiaryActivity.this, CreateRowActivity.class);
        intent.putExtra("diary_type", diaryType);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        initRows();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rows);
        list.setAdapter(adapter);
        super.onResume();
    }

    private void initRows() {
        Cursor cursor = mDb.rawQuery("select * from " + diaryType + " where user_id = ?;",
                new String[]{"" + userId} );
        rows = new ArrayList<>();
        int indexText = cursor.getColumnIndex("Text");
        while (cursor.moveToNext()) {
            rows.add(cursor.getString(indexText));
        }
        cursor.close();
    }
}
