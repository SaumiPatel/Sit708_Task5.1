package com.example.sit708_task5_1_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button loginBtn, signUpBtn;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        signUpBtn = findViewById(R.id.signUpBtn);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "itube-db").allowMainThreadQueries().build();

        loginBtn.setOnClickListener(v -> {
            User user = db.userDao().login(username.getText().toString(), password.getText().toString());
            if (user != null) {
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("USER_ID", user.id);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });

        signUpBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, SignUpActivity.class));
        });
    }
} 