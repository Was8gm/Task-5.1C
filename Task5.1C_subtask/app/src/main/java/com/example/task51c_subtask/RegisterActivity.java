package com.example.task51c_subtask;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText fullNameEditText, usernameEditText, passwordEditText, confirmPasswordEditText;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullNameEditText = findViewById(R.id.fullNameEditText);
        usernameEditText = findViewById(R.id.newUsernameEditText);
        passwordEditText = findViewById(R.id.newPasswordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String fullName = fullNameEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

        if (password.equals(confirmPassword)) {
            Database db = new Database(RegisterActivity.this);
            boolean registrationSuccessful = db.addUser(fullName, username, password);

            if (registrationSuccessful) {
                // The registration is successful, a success message is displayed and the registration page is closed
                Toast.makeText(RegisterActivity.this, "Registration is successful", Toast.LENGTH_SHORT).show();
                finish(); // 关闭当前页面
            } else {
                // 注册失败，显示错误消息
                Toast.makeText(RegisterActivity.this, "Registration failed, please try again", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "The password and confirmation password do not match", Toast.LENGTH_SHORT).show();
        }
    }
}

