package com.example.therapydiaries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateRowActivity extends AppCompatActivity {

    String diaryType;
    int userId;
    SQLiteDatabase mDb;
    Button btAdd;
    EditText edText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_row);
        diaryType = getIntent().getStringExtra("diary_type");
        mDb = ((MyApplication) this.getApplication()).getmDBHelper().getWritableDatabase();
        userId = ((MyApplication) this.getApplication()).getUserId();
        edText = findViewById(R.id.edit_text_create_row);
        btAdd = findViewById(R.id.button_add_create_row);
        btAdd.setOnClickListener(v -> addClicked());
    }

    private void addClicked() {
        String text = edText.getText().toString();
        if (text.equals("")){
            makeToast("Введите текст");
            return;
        }
        ContentValues newValues = new ContentValues();
        newValues.put("Text", text);
        newValues.put("user_id", userId);
        mDb.insert(diaryType, null, newValues);
//        mDb.close();
        finish();
    }

    private void makeToast(String s) {
        Toast toast = Toast.makeText(CreateRowActivity.this, s, Toast.LENGTH_SHORT);
        toast.show();
    }
}
