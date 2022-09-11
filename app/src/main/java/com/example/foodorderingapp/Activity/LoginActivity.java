package com.example.foodorderingapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.foodorderingapp.Data.UserRepository;
import com.example.foodorderingapp.Model.User;
import com.example.foodorderingapp.R;

public class LoginActivity extends AppCompatActivity {
    TextView signIn;
    EditText et_email, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signIn = findViewById(R.id.tv_signIn);
        et_email = findViewById(R.id.et_emailLogin);
        et_password = findViewById(R.id.et_passwordLogin);
        UserRepository userRepository = new UserRepository(this.getApplication());

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = et_email.getText().toString();
                String Password = et_password.getText().toString();
                User user = userRepository.getUser(Email, Password);
                String Name = user.getName();
                MainActivity.currentUser = Name;
                Intent intent = new Intent(LoginActivity.this, HomePage.class);
                intent.putExtra("userName", Name);
                System.out.println("This User Name is " + Name);
                startActivity(intent);
            }
        });
    }
}