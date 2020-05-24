package com.example.therapydiaries;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    @BindView(R.id.input_name)
    EditText _nameText;
    @BindView(R.id.input_login)
    EditText _loginText;
    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.input_password)
    EditText _passwordText;
    @BindView(R.id.btn_signup)
    Button _signupButton;
    @BindView(R.id.link_login)
    TextView _loginLink;
    SQLiteDatabase mDb;
    public static final String EXTRA_MESSAGE = "user_login";
    public static final String EXTRA_MESSAGE1 = "password";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        mDb = ((MyApplication) this.getApplication()).getmDBHelper().getWritableDatabase();

        _signupButton.setOnClickListener(v -> signup());

        _loginLink.setOnClickListener(v -> {
            // Finish the registration screen and return to the Login activity
            finish();
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Создание аккаунта...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String login = _loginText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own signup logic here.
        ContentValues newValues = new ContentValues();
        newValues.put("Login", login);
        newValues.put("Email", email);
        newValues.put("Password", password);
        newValues.put("Name", name);
        int newId = (int) mDb.insert("Users", null, newValues);
        Log.e(TAG, "signup: " + newId);
        if (newId == -1) {
            onSignupFailed();
            return;
        }
        new android.os.Handler().postDelayed(
                () -> {
//                    mDb.insert("Users", null, newValues);

                    // On complete call either onSignupSuccess or onSignupFailed
                    // depending on success
                    onSignupSuccess();
                    // onSignupFailed();
                    progressDialog.dismiss();
                }, 3000);
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra(EXTRA_MESSAGE,login);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        Intent intent1 = new Intent(SignupActivity.this, MainActivity.class);
        startActivity(intent1);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Sign up failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String login = _loginText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("как минимум 3 символа");
            valid = false;
        } else {
            _nameText.setError(null);
        }
        if (login.isEmpty() || login.length() < 5) {
            _loginText.setError("как минимум 5 символов");
            valid = false;
        } else {
            _loginText.setError(null);
        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("введите исправный email адресс");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("от 4 до 10 символов");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}