package com.example.therapydiaries;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ListView listView;
    SQLiteDatabase mDb;
    String str1;
    String str2;
    ArrayAdapter<String> dataAdapter1;
    ArrayAdapter<String> dataAdapter2;
    String[] diaries = { "Дневник желаний", "Дневник благодарности","Дневник страхов", "Дневник эмоций" , "Дневник медитаций", "Дневник панических атак"};

    @Override
    public void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDb = ((MyApplication) this.getApplication()).getmDBHelper().getWritableDatabase();

        listView=(ListView)findViewById(R.id.lv);


//        Intent intent1 = new Intent(this, LoginActivity.class);
//        startActivity(intent1);

            dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, diaries);
            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            listView.setAdapter(dataAdapter1);
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "onStart: started");
        super.onStart();
    }
}