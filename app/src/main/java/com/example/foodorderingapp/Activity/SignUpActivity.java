package com.example.foodorderingapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodorderingapp.Data.UserRepository;
import com.example.foodorderingapp.Model.User;
import com.example.foodorderingapp.R;


public class SignUpActivity extends AppCompatActivity {
    TextView loginPage, tv_signUp;
    EditText et_email, et_name, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        loginPage = findViewById(R.id.loginPage);
        et_email = findViewById(R.id.et_Email);
        et_name = findViewById(R.id.et_userName);
        et_password = findViewById(R.id.et_password);
        tv_signUp = findViewById(R.id.tv_signUp);

        UserRepository userRepository = new UserRepository(this.getApplication());
        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        tv_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = et_email.getText().toString();
                String Name = et_name.getText().toString();
                String Password = et_password.getText().toString();
                User user = new User(Email, Name, Password);
                userRepository.addUser(user);
                Toast.makeText(SignUpActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this, HomePage.class);
                MainActivity.currentUser = Name;
                intent.putExtra("Name", Name);
                startActivity(intent);
            }
        });
    }
}