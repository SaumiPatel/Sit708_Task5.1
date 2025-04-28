package com.example.sit708_task5_1_2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class SignUpActivity extends AppCompatActivity {
    EditText fullName, username, password, confirmPassword;
    Button createAccountBtn;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fullName = findViewById(R.id.fullName);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        createAccountBtn = findViewById(R.id.createAccountBtn);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "itube-db").allowMainThreadQueries().build();

        createAccountBtn.setOnClickListener(v -> {
            if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }
            User user = new User();
            user.fullName = fullName.getText().toString();
            user.username = username.getText().toString();
            user.password = password.getText().toString();
            db.userDao().insert(user);
            Toast.makeText(this, "Account created!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
} 