package com.example.foodorderingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodorderingapp.Data.UserDao;
import com.example.foodorderingapp.Data.UserRepository;
import com.example.foodorderingapp.Model.User;
import com.example.foodorderingapp.R;
import com.example.foodorderingapp.Utilites.ApplicationClass;
import com.example.foodorderingapp.Utilites.DishDatabase;

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
                if (Email.isEmpty() || Password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please Fill Out All Fields!", Toast.LENGTH_SHORT).show();
                } else {
                    DishDatabase databases = DishDatabase.getInstance(getApplicationContext());
                    UserDao dao = DishDatabase.instance.userDao();
                    new Thread(() -> {
                        User user = dao.getUser(Email, Password);
                        if (user == null) {
                            runOnUiThread(() ->
                                    Toast.makeText(LoginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show());
                        } else
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    ApplicationClass.currentUser = user;
                                    startActivity(new Intent(LoginActivity.this, HomePage.class));
                                }
                            }).start();
                    }).start();
                }
            }
        });
    }
}