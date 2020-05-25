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

        dWishes.setOnClickListener(this);
        dGratitude.setOnClickListener(this);
        dFears.setOnClickListener(this);
        dEmotions.setOnClickListener(this);
        dMeditation.setOnClickListener(this);
        dPanic.setOnClickListener(this);

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
        Intent intent = new Intent(MainActivity.this, DiaryActivity.class);
        switch (v.getId()){
            case (R.id.diary_wishes):
                intent.putExtra("diary_type", "Wish");
                startActivity(intent);
                break;
            case (R.id.diary_gratitude):
                intent.putExtra("diary_type", "Gratitude");
                startActivity(intent);
                break;
            case  (R.id.diary_fears):
                if (mode.equals("Обычный")) {
                    makeToast("Этот дневник доступен только Про или СуперПро");
                    return;
                }
                intent.putExtra("diary_type", "Fear");
                startActivity(intent);
                break;
            case  (R.id.diary_emotions):
                if (mode.equals("Обычный")) {
                    makeToast("Этот дневник доступен только Про или СуперПро");
                    return;
                }
                intent.putExtra("diary_type", "Emotion");
                startActivity(intent);
                break;
            case  (R.id.diary_meditations):
                if (!mode.equals("СуперПро")) {
                    makeToast("Этот дневник доступен только СуперПро");
                    return;
                }
                intent.putExtra("diary_type", "Meditation");
                startActivity(intent);
                break;
            case  (R.id.diary_panic):
                if (!mode.equals("СуперПро")) {
                    makeToast("Этот дневник доступен только СуперПро");
                    return;
                }
                intent.putExtra("diary_type", "Panic");
                startActivity(intent);
                break;
        }
    }

    private void makeToast(String s) {
        Toast toast = Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT);
        toast.show();
    }
}