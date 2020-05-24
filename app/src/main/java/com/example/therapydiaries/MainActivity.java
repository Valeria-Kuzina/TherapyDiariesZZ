package com.example.therapydiaries;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    //    ListView listView;
    SQLiteDatabase mDb;
    //    String str1;
//    String str2;
//    ArrayAdapter<String> dataAdapter1;
//    ArrayAdapter<String> dataAdapter2;
    String[] diaries = {
            "Дневник желаний",
            "Дневник благодарности",
            "Дневник страхов",
            "Дневник эмоций",
            "Дневник медитаций",
            "Дневник панических атак"
    };
    TextView dWishes, dGratitude, dFears, dEmotions, dMeditation, dPanic;
    int userId;
    String mode;

    @Override
    public void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDb = ((MyApplication) this.getApplication()).getmDBHelper().getWritableDatabase();
        userId = ((MyApplication) this.getApplication()).getUserId();
        mode = ((MyApplication) this.getApplication()).getMode();

        dWishes = findViewById(R.id.diary_wishes);
        dGratitude = findViewById(R.id.diary_gratitude);
        dFears = findViewById(R.id.diary_fears);
        dEmotions = findViewById(R.id.diary_emotions);
        dMeditation = findViewById(R.id.diary_meditations);
        dPanic = findViewById(R.id.diary_panic);



//        listView=(ListView)findViewById(R.id.lv);
//        Intent intent1 = new Intent(this, LoginActivity.class);
//        startActivity(intent1);
//            dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, diaries);
//            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            listView.setAdapter(dataAdapter1);
    }

    @Override
    protected void onStart() {
        Log.e(TAG, "onStart: started, userId " + userId + " mode " + mode);
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.diary_wishes):

                break;
            case (R.id.diary_gratitude):

                break;
            case  (R.id.diary_fears):
                if (mode.equals("Обычный"))
                    makeToast("Этот дневник доступен только Про или СуперПро");
                break;
            case  (R.id.diary_emotions):
                if (mode.equals("Обычный"))
                    makeToast("Этот дневник доступен только Про или СуперПро");

                break;
            case  (R.id.diary_meditations):
                if (!mode.equals("СуперПро"))
                    makeToast("Этот дневник доступен только СуперПро");
                break;
            case  (R.id.diary_panic):
                if (!mode.equals("СуперПро"))
                    makeToast("Этот дневник доступен только СуперПро");

                break;
        }
    }

    private void makeToast(String s) {
        Toast toast = Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT);
        toast.show();
    }
}